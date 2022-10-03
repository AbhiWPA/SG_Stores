package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.crudController.ClientsCrudController;
import controller.crudController.ItemsCrudController;
import extra.NotificationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TM.WholeSaleTM;
import util.CrudUtil;

import javax.mail.MessagingException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class WholesaleOrdersFormController implements Initializable {

    public JFXTextField txtItemName;
    public JFXTextField txtEmail;
    public JFXTextField txtClientName;
    public JFXTextField txtQty;

    public TableView<WholeSaleTM> tblOrder;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colClienID;
    public TableColumn colClientName;
    public TableColumn colQty;
    public TableColumn colCost;

    public JFXComboBox cmbItemCode;
    public JFXComboBox cmbClientID;
    public Label lblUnitPrice;

    ObservableList<WholeSaleTM> tmList = FXCollections.observableArrayList();
    NotificationController nfc=new NotificationController();

    public void mailBtnOnAction(ActionEvent actionEvent) throws MessagingException {
        String receiver = txtEmail.getText();
        MailSendingController.sendMail(receiver);
        nfc.confirmMassage("Mail Sent...!","Mail Sending Successfully...!!!");
    }

    private WholeSaleTM isExists(String itemCode) {
        for (WholeSaleTM tm : tmList
        ) {
            if (tm.getItem_code().equals(itemCode)) {
                return tm;
            }
        }
        return null;
    }

    private void calculateTotal() {
        double total = 0;
        for (WholeSaleTM tm : tmList
        ) {
            total += tm.getCost();
        }
    }

    public void addOrderOnAction(ActionEvent actionEvent) {
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double totalCost = unitPrice * qty;

        WholeSaleTM isExists = isExists((String) cmbItemCode.getValue());

        if (isExists != null) {
            for (WholeSaleTM temp : tmList
            ) {
                if (temp.equals(isExists)) {
                    temp.setQty((temp.getQty() + qty));
                    temp.setCost(temp.getCost() + totalCost);
                }
            }
        } else {
            WholeSaleTM tm = new WholeSaleTM(
                    (String) cmbItemCode.getValue(),
                    txtItemName.getText(),
                    (String) cmbClientID.getValue(),
                    txtClientName.getText(),
                    Integer.parseInt(txtQty.getText()),
                    totalCost
            );


            tmList.add(tm);
            tblOrder.setItems(tmList);

        }

        tblOrder.refresh();
        calculateTotal();
    }

    public void removeBtnOnAction(ActionEvent actionEvent) {
        int selected = tblOrder.getSelectionModel().getSelectedIndex();
        tblOrder.getItems().remove(selected);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setItemCodes();
        setClientIds();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("Item_code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Item_name"));
        colClienID.setCellValueFactory(new PropertyValueFactory<>("clientID"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    private void setClientIds() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ClientsCrudController.getClientIds()
            );
            cmbClientID.setItems(ObList);


            cmbClientID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String ID= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM clients WHERE Cl_ID=?",ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtClientName.setText(result.getString(2));
                        txtEmail.setText(result.getString(4));
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
                    result = CrudUtil.execute("SELECT * FROM import_items WHERE Item_code=?",itemCode);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        txtItemName.setText(result.getString(2));
                        lblUnitPrice.setText(String.valueOf(result.getDouble(3)));
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
}
