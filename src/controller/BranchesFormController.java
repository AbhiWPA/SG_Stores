package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import extra.NotificationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Branches;
import model.Employee;
import model.TM.BranchesTM;
import model.TM.EmployeeTM;
import util.CrudUtil;
import util.ValidationUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class BranchesFormController implements Initializable {
    public JFXTextField txtBrCode;
    public JFXTextField txtBrAddress;
    public JFXTextField txtBrMail;
    public JFXTextField txtBrEmployees;
    public JFXTextField txtBrManID;
    public JFXTextField txtBrManName;
    public JFXTextField txtContact;

    public TableView<BranchesTM> tblBranches;
    public TableColumn colCode;
    public TableColumn colAddress;
    public TableColumn colMail;
    public TableColumn colEmployee;
    public TableColumn colManagerID;
    public TableColumn colManagerName;
    public TableColumn colContact;
    public JFXButton btnAddNew;
    public TextArea txtContent;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    NotificationController nfc=new NotificationController();

    public void newBranchOnAction(ActionEvent actionEvent) {
        Branches br = new Branches(txtBrCode.getText(), txtBrAddress.getText(), txtBrMail.getText(), Integer.parseInt(txtBrEmployees.getText()), txtBrManID.getText(),
                txtBrManName.getText(), Integer.parseInt(txtContact.getText()));

        try {
            if (CrudUtil.execute("INSERT INTO branches VALUES (?,?,?,?,?,?,?)",br.getBr_code(), br.getAddress(), br.getMail(), br.getNo_Of_Emps(), br.getBr_ManagerID(),
                    br.getBr_ManagerName(), br.getContact())){
                nfc.upperConfirmMessage("Saved...!", "New branch Saved Successfully...!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            nfc.upperConfirmMessage("Saving Error...!", "New branch Saved Unsuccessfully...!, Please enter new branch details correctly...");
        }

        tblBranches.refresh();
    }

    public void updateBranchBtnOnAction(ActionEvent actionEvent) {
        Branches br=new Branches(txtBrCode.getText(),txtBrAddress.getText(),txtBrMail.getText(),Integer.parseInt(txtBrEmployees.getText()), txtBrManID.getText(), txtBrManName.getText(), Integer.parseInt(txtContact.getText()));

        try{
            if(CrudUtil.execute("UPDATE Branches SET address=?,mail=?,No_Of_Emps=?,Br_ManagerID=?,Br_ManagerName=?,contact=?  WHERE Br_code=?",br.getAddress(),br.getMail(),br.getNo_Of_Emps(),br.getBr_ManagerID(),br.getBr_ManagerName(),br.getContact(),br.getBr_code())){
                nfc.upperConfirmMessage("Updated...!", "Branch Details Updated Successfully...!");
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            nfc.upperConfirmMessage("Updating Error...!", "Branch Details Updated Unsuccessfully...!, Please enter branch details correctly...");
        }

        tblBranches.refresh();
    }

    public void txtBranchCodeOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result =  CrudUtil.execute("SELECT * FROM branches WHERE Br_code=?",txtBrCode.getText());
            if (result.next()) {
                txtBrAddress.setText(result.getString(2));
                txtBrMail.setText(result.getString(3));
                txtBrEmployees.setText(String.valueOf(result.getInt(4)));
                txtBrManID.setText(result.getString(5));
                txtBrManName.setText(result.getString(6));
                txtContact.setText(String.valueOf(result.getInt(7)));
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
            setBranchDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        colEmployee.setCellValueFactory(new PropertyValueFactory<>("employees"));
        colManagerID.setCellValueFactory(new PropertyValueFactory<>("managerID"));
        colManagerName.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        Pattern brCode = Pattern.compile("^(B00)[0-9]{1,5}$");
        Pattern address = Pattern.compile("^[A-z ]{3,25}$");
        Pattern mail = Pattern.compile("^[A-z0-9]{4,}@(gmail|yahoo|bing).com$");
        Pattern employees = Pattern.compile("^[0-9]{1,2}");
        Pattern brManagerId = Pattern.compile("^(M00)[0-9]{1,5}");
        Pattern brManagerName = Pattern.compile("^(Mr.|Mrs.)[A-z ]{3,}");
        Pattern brContact = Pattern.compile("^(070|071|072|075|076|077|078|027)[0-9]{7}");

        map.put(txtBrCode,brCode);
        map.put(txtBrAddress,address);
        map.put(txtBrMail,mail);
        map.put(txtBrEmployees,employees);
        map.put(txtBrManID,brManagerId);
        map.put(txtBrManName,brManagerName);
        map.put(txtContact,brContact);
    }

    private void setBranchDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM branches");
        ObservableList<BranchesTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new BranchesTM(
                            result.getString("Br_code"),
                            result.getString("address"),
                            result.getString("mail"),
                            result.getInt("No_Of_Emps"),
                            result.getString("Br_ManagerID"),
                            result.getString("Br_ManagerName"),
                            result.getInt("contact")
                    )
            );
        }
        tblBranches.setItems(obList);
    }

    public void Enter_key_Releaased(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddNew);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddNew);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }

    public void sendMailBtnOnAction(ActionEvent actionEvent) throws MessagingException {
        String receiver=txtBrMail.getText();
        MailSendingController.sendMail(receiver);
        nfc.confirmMassage("Mail Sent...!","Mail Sending Successfully...!!!");
    }

}
