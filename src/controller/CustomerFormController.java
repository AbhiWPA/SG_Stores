package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import extra.NotificationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import model.TM.CustomerTM;
import model.TM.ItemsTM;
import util.CrudUtil;
import util.ValidationUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CustomerFormController implements Initializable {

    public JFXTextField txtCustomerID;
    public JFXTextField txtCustomerAddress;
    public JFXTextField txtCustomerName;
    public JFXTextField txtContact;
    public JFXTextField txtUpdateID;
    public JFXTextField txtUpdateName;
    public JFXTextField txtUpdateAddress;
    public JFXTextField txtUpdateNumber;

    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCusId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public JFXButton btnSave;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    NotificationController nfc=new NotificationController();

    public void addCustomerOnAction(ActionEvent actionEvent) {
        Customer c = new Customer(txtCustomerID.getText(), txtCustomerName.getText(), txtCustomerAddress.getText(), Integer.parseInt(txtContact.getText()));

        try {
            if (CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?)",c.getC_ID(),c.getC_name(),c.getC_address(),c.getC_contact())){
                nfc.upperConfirmMessage("Saved...!","New Customer Saved Successfully...!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            nfc.upperErrorMessage("Saving Error...!","New Customer Saved Unsuccessfully...!, Please enter new Customer details correctly..."+e.getMessage());
        }

        try {
            setCustomerDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        Customer cus = new Customer(txtUpdateID.getText(), txtUpdateName.getText(), txtUpdateAddress.getText(), Integer.parseInt(txtUpdateNumber.getText()));

        try{
            if(CrudUtil.execute("UPDATE Customer SET C_name=?, C_address=?, C_contact=?  WHERE C_ID=?",cus.getC_name(),cus.getC_address(),cus.getC_contact(),cus.getC_ID())){
                nfc.upperConfirmMessage("Updated...!","Customer Details Updated Successfully...!");
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            nfc.upperErrorMessage("Updating Error...!","Customer Details Updated Unsuccessfully...!, Please enter Customer details correctly..."+e.getMessage());
        }
        tblCustomer.refresh();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result =  CrudUtil.execute("SELECT * FROM Customer WHERE C_ID=? OR C_name=?",txtUpdateID.getText(), txtUpdateName.getText());
            if (result.next()) {
                txtUpdateName.setText(result.getString(2));
                txtUpdateAddress.setText(result.getString(3));
                txtUpdateNumber.setText(String.valueOf(result.getInt(4)));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setCustomerDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colCusId.setCellValueFactory(new PropertyValueFactory<>("C_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("C_name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("C_address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("C_contact"));

        Pattern idPattern = Pattern.compile("^(C00-)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern contactPattern = Pattern.compile("^(070|071|072|075|076|077|078|027)[0-9]{7}$");

        map.put(txtCustomerID,idPattern);
        map.put(txtCustomerName,namePattern);
        map.put(txtCustomerAddress,addressPattern);
        map.put(txtContact,contactPattern);
    }

    private void setCustomerDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM customer");
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new CustomerTM(
                            result.getString("C_ID"),
                            result.getString("C_name"),
                            result.getString("C_address"),
                            result.getString("C_contact")
                    )
            );
        }
        tblCustomer.setItems(obList);
    }

    public void key_Entered_Event(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnSave);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnSave);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }
}
