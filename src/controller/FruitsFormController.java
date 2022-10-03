package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.ItemsCrudController;
import extra.NotificationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Items;
import model.TM.ItemsTM;
import util.CrudUtil;
import util.ValidationUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class FruitsFormController implements Initializable {
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQty;
    public JFXComboBox cbxCode;
    public JFXTextField txtPrice;

    public TableView<ItemsTM> tblFruits;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public JFXButton btnAddStore;
    public Label lblAvailableFruits;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    NotificationController nfc=new NotificationController();

    public void btnAddOnAction(ActionEvent actionEvent) {
        Items items = new Items(txtItemCode.getText(), txtDescription.getText(), Integer.parseInt(txtQty.getText()), Double.parseDouble(txtPrice.getText()));

        try{
            if (CrudUtil.execute("INSERT INTO items VALUES (?,?,?,?)",items.getItemCode(), items.getDescription(), items.getQty(), items.getPrice())){
                nfc.upperConfirmMessage("Saved...!","New Fruit Item Saved Successfully...!");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            nfc.upperErrorMessage("Saving Error...!","New Fruit Item Saved Unsuccessfully...!"+e.getMessage());
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Items i=new Items(txtItemCode.getText(),txtDescription.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtPrice.getText()));

        try{
            if(CrudUtil.execute("UPDATE Items SET descrip=?,Qty_On_Hand=?,Price=?  WHERE Item_Code=?",i.getDescription(),i.getQty(),i.getPrice(),i.getItemCode())){
                nfc.upperConfirmMessage("Updated...!","Fruit Item Updated Successfully...!");;
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            nfc.upperErrorMessage("Updating Error...!","Update Client Details Unsuccessfully...!"+e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setItemCodes();
        try {
            setTableDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("Item_code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("descrip"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty_On_Hand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

        Pattern codePattern = Pattern.compile("^(F00-)[0-9]{3,5}$");
        Pattern descPattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern qtyPattern = Pattern.compile("^[0-9]{1,100}$");
        Pattern pricePattern = Pattern.compile("^[0-9]{2,}.[0-9]{2}");

        map.put(txtItemCode,codePattern);
        map.put(txtDescription,descPattern);
        map.put(txtQty,qtyPattern);
        map.put(txtPrice,pricePattern);

        try {
            setTotalFruits();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTotalFruits() throws SQLException, ClassNotFoundException {
        int fruitCount=0;
        ResultSet resultSet = CrudUtil.execute("SELECT item_code FROM items WHERE Item_code LIKE 'F%'");

        while (resultSet.next()){
            fruitCount++;
        }
        lblAvailableFruits.setText(String.valueOf(fruitCount));
    }

    private void setTableDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM items WHERE Item_code LIKE 'F%'");
        ObservableList<ItemsTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new ItemsTM(
                            result.getString("Item_code"),
                            result.getString("descrip"),
                            result.getInt("Qty_On_Hand"),
                            result.getDouble("Price")
                    )
            );
        }
        tblFruits.setItems(obList);
    }

    private void setItemCodes() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemsCrudController.getFruitIds()
            );
            cbxCode.setItems(ObList);


            cbxCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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
                        txtItemCode.setText(result.getString(1));
                        txtDescription.setText(result.getString(2));
                        txtQty.setText(result.getString(3));
                        txtPrice.setText(String.valueOf(result.getDouble(4)));
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

    public void Enter_Key_Pressed(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddStore);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddStore);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }
}
