package controller;

import com.jfoenix.controls.JFXComboBox;
import controller.crudController.*;
import extra.NotificationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Employee;
import util.CrudUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteThingsFormController implements Initializable
{
    public JFXComboBox cmbItemCode;
    public JFXComboBox cmbCustomerID;
    public JFXComboBox cmbEmployeeID;
    public JFXComboBox cmbVehicleNo;
    public JFXComboBox cmbWholeSale;

    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;
    public Label label6;

    String cmb1;
    String cmb2;
    String cmb3;
    String cmb4;
    String cmb5;

    NotificationController nfc=new NotificationController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setItemCodes();
        setCustomerIds();
        setEmployeeIds();
        setVehicleNumbers();
        setWholeSaleOrderIds();
    }

    private void setWholeSaleOrderIds() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    WholeSaleCrudController.getOrderIds()
            );
            cmbWholeSale.setItems(ObList);


            cmbWholeSale.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String Or_ID= (String) newValue;
                cmb5= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM wholesaleorders WHERE W_Or_ID=?",Or_ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        label1.setText(result.getString(1));
                        label2.setText(result.getString(2));
                        label3.setText(result.getString(3));
                        label4.setText(result.getString(4));
                        label5.setText(String.valueOf(result.getInt(5)));
                        label5.setText(String.valueOf(result.getDouble(6)));
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
    }/**/

    private void setVehicleNumbers() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    VehiclesCrudController.getVehicleNumbers()
            );
            cmbVehicleNo.setItems(ObList);


            cmbVehicleNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String V_No= (String) newValue;
                cmb4= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM vehicles WHERE VNumber=?",V_No);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        label1.setText(result.getString(1));
                        label2.setText(result.getString(2));
                        label3.setText(result.getString(3));
                        label4.setText(result.getString(4));
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
    }/**/

    private void setEmployeeIds() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    EmployeeCrudController.getEmployeeIds()
            );
            cmbEmployeeID.setItems(ObList);


            cmbEmployeeID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String E_ID= (String) newValue;
                cmb3= (String) newValue;

                ResultSet result = null;
                try {
                    result = CrudUtil.execute("SELECT * FROM employee WHERE E_ID=?",E_ID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    if (result.next()) {
                        label1.setText(result.getString(1));
                        label2.setText(result.getString(2));
                        label3.setText(result.getString(3));
                        label4.setText(result.getString(4));
                        label5.setText(result.getString(5));
                        label6.setText(String.valueOf(result.getDouble(6)));
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
    }/**/

    private void setCustomerIds() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    CustomerCrudController.getAllIds()
            );
            cmbCustomerID.setItems(ObList);


            cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String C_ID= (String) newValue;
                cmb2= (String) newValue;


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
                        label1.setText(result.getString(1));
                        label2.setText(result.getString(2));
                        label3.setText(result.getString(3));
                        label4.setText(String.valueOf(result.getInt(4)));
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
    }/**/

    private void setItemCodes() {
        try {

            ObservableList<String> ObList = FXCollections.observableArrayList(
                    ItemsCrudController.getAllItems()
            );
            cmbItemCode.setItems(ObList);


            cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String itemCode= (String) newValue;
                cmb1= (String) newValue;

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
                        label1.setText(result.getString(1));
                        label2.setText(result.getString(2));
                        label3.setText(result.getString(3));
                        label4.setText(String.valueOf(result.getDouble(4)));
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
    }/**/

    public void deleteRecordOnAction(ActionEvent actionEvent) {
        if (cmb1!=null){
            deleteAnItem();
        }else if (cmb2!=null){
            deleteCustomer();
        }else if (cmb3!=null){
            deleteEmployees();
        }else if(cmb4!=null){
            deleteVehicle();
        }else if (cmb5!=null){
            deleteWholeSaleOrder();
        }
    }

    private void deleteWholeSaleOrder() {
        try{
            if (CrudUtil.execute("DELETE FROM wholesaleorders WHERE W_Or_ID=?",label1.getText())){
                nfc.upperConfirmMessage("Deleted...!"," Deleted Successfully...!");
            }else{
                nfc.upperErrorMessage("Deleting Error...!","Whole Sale Order Deleted Unsuccessfully...!");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            nfc.upperErrorMessage("-------",e.getMessage());
        }
    }

    private void deleteVehicle() {
        try{
            if (CrudUtil.execute("DELETE FROM vehicles WHERE Vnumber=?",label1.getText())){
                nfc.upperConfirmMessage("Deleted...!","Vehicle Deleted Successfully...!");
            }else{
                nfc.upperErrorMessage("Deleting Error...!","Vehicle Deleted Unsuccessfully...!");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            nfc.upperErrorMessage("-------",e.getMessage());
        }
    }

    private void deleteEmployees() {
        try{
            if (CrudUtil.execute("DELETE FROM employee WHERE E_ID=?",label1.getText())){
                nfc.upperConfirmMessage("Deleted...!","Employee Deleted Successfully...!");
            }else{
                nfc.upperErrorMessage("Deleting Error...!","Employee Deleted Unsuccessfully...!");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            nfc.upperErrorMessage("-------",e.getMessage());
        }
    }

    private void deleteCustomer() {
        try{
            if (CrudUtil.execute("DELETE FROM customer WHERE C_ID=?",label1.getText())){
                nfc.upperConfirmMessage("Deleted...!","Customer Deleted Successfully...!");
            }else{
                nfc.upperErrorMessage("Deleting Error...!","Customer Deleted Unsuccessfully...!");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            nfc.upperErrorMessage("-------",e.getMessage());
        }
    }

    private void deleteAnItem() {
        try{
            if (CrudUtil.execute("DELETE FROM items WHERE Item_Code=?",label1.getText())){
                nfc.upperConfirmMessage("Deleted...!","Item Deleted Successfully...!");
            }else{
                nfc.upperErrorMessage("Deleting Error...!","Item Deleted Unsuccessfully...!");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            nfc.upperErrorMessage("-------",e.getMessage());
        }
    }


}
