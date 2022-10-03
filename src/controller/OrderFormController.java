package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.CustomerCrudController;
import controller.crudController.ItemsCrudController;
import controller.crudController.OrderCrudController;
import db.DBConnection;
import extra.NotificationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;
import model.TM.BillTM;
import model.TM.CartTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;
import util.ValidationUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class OrderFormController implements Initializable {

    public JFXComboBox cmbCustomer;
    public JFXTextField txtCustomerName;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;

    public JFXComboBox cmbItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;

    public TableView tblCart;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colRemove;
    public JFXButton btnAddCart;

    public TableView<Cart> tblSecondCart;
    public TableColumn colItemCodeTwo;
    public TableColumn colDescriptionTwo;
    public TableColumn colUnitPriceTwo;
    public TableColumn colQTYTwo;
    public TableColumn colTotalCostTwo;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    int i=1;
    int j=0;
    public Label lblTotal;
    int newQty;
    double profit;

    NotificationController nfc = new NotificationController();

    ObservableList<CartTM> tmList = FXCollections.observableArrayList();
    ObservableList<Cart> tmBill = FXCollections.observableArrayList();

    private CartTM isExists(String itemCode) {
        for (CartTM tm : tmList
        ) {
            if (tm.getItem_code().equals(itemCode)) {
                return tm;
            }
        }
        return null;
    }

    private void calculateTotal() {
        double total = 0;
        for (CartTM tm : tmList
        ) {
            total += tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    public void printBillOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int j=0;
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orderssummery;");

        while (resultSet.next()){
            j++;
        }
        String orderID = "Or00"+j;
        String customerName = txtCustomerName.getText();
        double tot = Double.parseDouble(lblTotal.getText());

        HashMap map = new HashMap();
        map.put("orderID",orderID);
        map.put("customerName",customerName);
        map.put("Total",tot);

        //ObservableList<Cart> tableRecords = tblSecondCart.getItems();

        try {
            JasperReport compiledReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/NewBillReport.jasper"));
            ObservableList<Cart> tableRecords = tblSecondCart.getItems();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport,map,new JRBeanCollectionDataSource(tableRecords));
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void addCustomerBtnOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();
    }

    public void addToCartBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        int j = 0;
        ResultSet result = CrudUtil.execute("SELECT * FROM orderssummery;");

        while (result.next()){
            j++;
        }
        String order="Or00";
        String id=order+j;

        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double totalCost = unitPrice * qty;

        CartTM isExists = isExists((String) cmbItemCode.getValue());

        if (isExists != null) {
            for (CartTM temp : tmList
            ) {
                if (temp.equals(isExists)) {
                    newQty = temp.getQty() + qty;
                    temp.setQty((temp.getQty() + qty));
                    temp.setTotal(temp.getTotal() + totalCost);
                }
            }
        } else {
            Button btn = new Button("Delete");
            btn.setStyle("-fx-background-color: RED");

            CartTM tm = new CartTM(
                    (String) cmbItemCode.getValue(),
                    txtDescription.getText(),
                    unitPrice,
                    qty,
                    totalCost,
                    btn
            );

            Cart c = new Cart(
                    (String) cmbItemCode.getValue(),
                    txtDescription.getText(),
                    unitPrice,
                    qty,
                    totalCost
            );

            btn.setOnAction(e -> {
                tmList.remove(tm);
                calculateTotal();
            });

            tmList.add(tm);
            tblCart.setItems(tmList);

            tmBill.add(c);
            tblSecondCart.setItems(tmBill);
        }
        tblCart.refresh();
        calculateTotal();

        try {
            ResultSet resultSet=CrudUtil.execute("SELECT profit_from_one_item FROM import_items WHERE Item_code=?",String.valueOf(cmbItemCode.getValue()));
            CartTM isExists2 = isExists((String) cmbItemCode.getValue());
            while (resultSet.next()){
                double x=resultSet.getDouble(1);
                if (isExists2 != null) {
                    for (CartTM temp : tmList
                    ) {
                        if (temp.equals(isExists2)) {
                            newQty = temp.getQty() + qty;
                            profit=x*newQty;
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        double total=Double.parseDouble(txtUnitPrice.getText()) * Double.parseDouble(txtQty.getText());

        VFItems i=new VFItems((String) cmbItemCode.getValue(),txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()),total);

        try {
            CrudUtil.execute("INSERT INTO todaysellings VALUES (?,?,?,?,?)",i.getItem_code(),i.getDescription(),i.getUnitPrice(),i.getQty(),i.getTotal());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Bill b=new Bill(id,txtCustomerName.getText(),txtDescription.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitPrice.getText()),totalCost,Double.parseDouble(lblTotal.getText()));

        try {
            CrudUtil.execute("INSERT INTO bill VALUES (?,?,?,?,?,?,?)",b.getOrId(),b.getCustomer(),b.getDescription(),b.getQty(),b.getUnitPrice(),b.getAmount(),b.getTotal());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  void placeOrderBtnOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orId = "Or00";

        ResultSet resultSet = CrudUtil.execute("SELECT * FROM orderssummery;");

        while (resultSet.next()){
            i++;
        }


        try {
            updateQty();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Order order=new Order(orId+i, (String) cmbItemCode.getValue(),txtDescription.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.parseInt(txtQty.getText()),Double.parseDouble(lblTotal.getText()),date);

        ArrayList<OrderDetail> details = new ArrayList<>();
        for (CartTM tm : tmList
        ) {
            details.add(
                    new OrderDetail(
                            "D024",
                            tm.getItem_code(),
                            tm.getQty(),
                            tm.getUnitPrice()
                    )
            );

        }

        Connection connection= null;

        try {
            connection=DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isOrderSaved = new OrderCrudController().saveOrder(order);
            if (isOrderSaved) {
                boolean isDetailsSaved=new OrderCrudController().saveOrderDetails(details);
                if (isDetailsSaved){
                    connection.commit();
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved!").show();
                }else{
                    connection.rollback();
                    new Alert(Alert.AlertType.ERROR,"Error!").show();
                }
            }else{
                connection.rollback();
                new Alert(Alert.AlertType.ERROR,"Error!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }finally {
            connection.setAutoCommit(true);
        }


        OrderSummery s = new OrderSummery(orId+i, String.valueOf(cmbCustomer.getValue()), Double.parseDouble(lblTotal.getText()), date);
        try {
            if (CrudUtil.execute("INSERT INTO orderssummery VALUES (?,?,?,?)",s.getOrId(),s.getCustomerId(),s.getTotal(),s.getDate())){
                nfc.upperConfirmMessage("Order Saved...!","New Order Details Saved to the database...!!!");
            }else{
                nfc.upperErrorMessage("Order Not Saved...!","Something Wrong...!!!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            nfc.upperErrorMessage("Order Not Saved...!","Something Wrong...!!!"+e.getMessage());
        }



    }


    private void updateQty() throws SQLException, ClassNotFoundException {
        String itemCode = (String) cmbItemCode.getValue();
        int onHand = Integer.parseInt(txtQtyOnHand.getText());
        int qty = Integer.parseInt(txtQty.getText());
        int newQty = onHand - qty;

        CrudUtil.execute("UPDATE items SET Qty_On_Hand=? WHERE Item_code=?",newQty,itemCode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("Item_code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setCustomerIds();
        setItemCodes();

        Pattern qtyPattern = Pattern.compile("^[0-9]{1,2}$");

        map.put(txtQty,qtyPattern);
    }

    private void setItemCodes() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemsCrudController.getAllItems()
            );
            cmbItemCode.setItems(ObList);


            cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM items WHERE Item_code=?",itemCode);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtDescription.setText(result.getString(2));
                        txtQtyOnHand.setText(result.getString(3));
                        txtUnitPrice.setText(String.valueOf(result.getString(4)));
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Empty Result").show();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerIds() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CustomerCrudController.getAllIds()
            );
            cmbCustomer.setItems(ObList);


            cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String C_ID= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM customer WHERE C_ID=?",C_ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtCustomerName.setText(result.getString(2));
                        txtAddress.setText(result.getString(3));
                        txtContact.setText(result.getString(4));
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Empty Result").show();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void Number_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddCart);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddCart);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }


}
