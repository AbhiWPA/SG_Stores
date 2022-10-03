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
import model.Clients;
import model.Customer;
import model.Employee;
import model.TM.ClientTM;
import model.TM.EmployeeTM;
import util.CrudUtil;
import util.ValidationUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ClientsFormController implements Initializable {
    public JFXTextField txtClientID;
    public JFXTextField txtClientName;
    public JFXTextField txtClientAddress;
    public JFXTextField txtClientMail;

    public JFXTextField txtUpdateID;
    public JFXTextField txtUpdateName;
    public JFXTextField txtUpdateAddress;
    public JFXTextField txtUpdateMail;

    public TableView<ClientTM> tblClient;
    public TableColumn colClientID;
    public TableColumn colClientName;
    public JFXButton btnAddClient;
    public TextField txtSearch;
    public JFXButton btnDeleteClient;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    NotificationController nfc=new NotificationController();

    public void addClientOnAction(ActionEvent actionEvent) {
        Clients cl = new Clients(txtClientID.getText(), txtClientName.getText(), txtClientAddress.getText(), txtClientMail.getText());

        try {
            if (CrudUtil.execute("INSERT INTO clients VALUES (?,?,?,?)",cl.getCl_ID(),cl.getCl_Name(), cl.getCl_Address(), cl.getCl_Mail())){
                nfc.upperConfirmMessage("Saved...!","New client Saved Successfully...!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            nfc.upperErrorMessage("Saving Error...!","New Client Saved Unsuccessfully...!, Please enter new client details correctly...");
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        Clients cl=new Clients(txtUpdateID.getText(),txtUpdateName.getText(),txtUpdateAddress.getText(),txtUpdateMail.getText());

        try{
            if(CrudUtil.execute("UPDATE clients SET Cl_Name=?,Cl_Address=?,Cl_Mail=?  WHERE Cl_ID=?",cl.getCl_Name(),cl.getCl_Address(),cl.getCl_Mail(),cl.getCl_ID())){
                nfc.upperConfirmMessage("Updated...!","Client Details Updated Successfully...!");
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            nfc.upperErrorMessage("Updating Error...!","Update Client Details Unsuccessfully...!, Please enter client details correctly...");
        }
    }

    public void txtClientOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result =  CrudUtil.execute("SELECT * FROM clients WHERE Cl_ID=?",txtUpdateID.getText());
            if (result.next()) {
                txtUpdateName.setText(result.getString(2));
                txtUpdateAddress.setText(result.getString(3));
                txtUpdateMail.setText(result.getString(4));
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
            setClientDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colClientID.setCellValueFactory(new PropertyValueFactory<>("Cl_ID"));
        colClientName.setCellValueFactory(new PropertyValueFactory<>("name"));

        Pattern clId = Pattern.compile("^(CL00)[0-9]{1,5}$");
        Pattern ClName = Pattern.compile("^[A-z ]{3,25}$");
        Pattern address = Pattern.compile("^[A-z 0-9]{3,30}");
        Pattern mail = Pattern.compile("^[A-z0-9]{4,}@(gmail|yahoo|bing).com$");

        map.put(txtClientID,clId);
        map.put(txtClientName,ClName);
        map.put(txtClientAddress,address);
        map.put(txtClientMail,mail);
    }

    private void setClientDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM clients");
        ObservableList<ClientTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new ClientTM(
                            result.getString("Cl_ID"),
                            result.getString("Cl_Name")
                    )
            );
        }
        tblClient.setItems(obList);
    }

    public void Enter_key_Releaased(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddClient);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddClient);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result =  CrudUtil.execute("SELECT * FROM clients WHERE Cl_Name=?",txtSearch.getText());
            if (result.next()) {
                txtUpdateID.setText(result.getString(1));
                txtUpdateName.setText(result.getString(2));
                txtUpdateAddress.setText(result.getString(3));
                txtUpdateMail.setText(result.getString(4));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void deleteClientBtnOnAction(ActionEvent actionEvent) {
        try{
            if (CrudUtil.execute("DELETE FROM clients WHERE Cl_ID=?",txtUpdateID.getText())){
                nfc.upperConfirmMessage("Deleted...!","Client Deleted Successfully...!");
            }else{
                nfc.upperErrorMessage("Deleting Error...!","Delete Client Details Unsuccessfully...!");
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
