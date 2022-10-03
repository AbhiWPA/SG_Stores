package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.protocol.Resultset;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Clients;
import model.User;
import util.CrudUtil;
import util.ValidationUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SecurutyFormController implements Initializable {
    public JFXTextField txtOwnerName;
    public JFXPasswordField txtOwnerPassword;
    public JFXTextField txtCashierName;
    public JFXPasswordField txtCashierPswd;
    public JFXButton btnUpdateOwner;
    public JFXButton btnUpdateCashier;
    public JFXTextField txtCurrentUserName;
    public JFXPasswordField txtCurrentPassword;
    public JFXTextField txtCurrentCashierName;
    public JFXPasswordField txtCurrentCashierPassword;

    public JFXPasswordField txtConfirmOwnerPassword;
    public JFXPasswordField txtConfirmCashierPassword;

    public CheckBox checkBox1;
    public CheckBox checkBox2;
    public JFXTextField txtTextOwnerPassword;
    public JFXTextField txtTextOwnerConfirmPswd;
    public JFXTextField txtTextCashierPassword;
    public JFXTextField txtTextConfirmCashPswd;

    LinkedHashMap<TextField, Pattern> ownerMap = new LinkedHashMap<>();
    LinkedHashMap<TextField, Pattern> CashierMap = new LinkedHashMap<>();

    public void btnUpdateOwnerOnAction(ActionEvent actionEvent) {
        User u1=new User(txtOwnerName.getText(), txtOwnerPassword.getText());

        try{
            if(CrudUtil.execute("UPDATE owner_loging_detail SET userName=?,userPassword=?  WHERE ownerId='O001'",u1.getUserName(),u1.getUserPassword())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void updateCashierOnAction(ActionEvent actionEvent) {
        User u2=new User(txtCashierName.getText(), txtCashierPswd.getText());

        try{
            if(CrudUtil.execute("UPDATE cashier_loging_detail SET userName=?,userPassword=?  WHERE CashierId='C001'",u2.getUserName(),u2.getUserPassword())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void Owner_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(ownerMap,btnUpdateOwner);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(ownerMap,btnUpdateOwner);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }

    public void Cashier_key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(CashierMap,btnUpdateCashier);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(CashierMap,btnUpdateCashier);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtOwnerName.setEditable(false);
        txtOwnerName.setDisable(true);
        txtOwnerPassword.setDisable(true);
        txtOwnerPassword.setEditable(false);

        txtCashierName.setDisable(true);
        txtCashierPswd.setDisable(true);
        txtCashierName.setEditable(false);
        txtCashierPswd.setEditable(false);

        txtTextOwnerPassword.setVisible(false);
        txtTextOwnerConfirmPswd.setVisible(false);
        txtTextCashierPassword.setVisible(false);
        txtTextConfirmCashPswd.setVisible(false);

        Pattern OwnerNm = Pattern.compile("^[A-z ]{3,15}$");
        Pattern OwnerPswd = Pattern.compile("^[A-z0-9]{8,}$");
        Pattern CashierNm = Pattern.compile("^[A-z ]{3,15}");
        Pattern CashierPswd = Pattern.compile("^[A-z0-9]{8,}$");

        ownerMap.put(txtOwnerName,OwnerNm);
        ownerMap.put(txtOwnerPassword,OwnerPswd);
        CashierMap.put(txtCashierName,CashierNm);
        CashierMap.put(txtCashierPswd,CashierPswd);
    }

    public void ownerSearchBtnOnAction(ActionEvent actionEvent) {
        String OwnerName=txtCurrentUserName.getText();
        String OwnerPswd=txtCurrentPassword.getText();
        try {
            ResultSet OName =  CrudUtil.execute("SELECT ownerId FROM owner_loging_detail WHERE userName=?",OwnerName);
            ResultSet OPswd =  CrudUtil.execute("SELECT ownerId FROM owner_loging_detail WHERE userPassword=?",OwnerPswd);

            if (OName.next() && OPswd.next()){
                String name = OName.getString(1);
                String pswd = OPswd.getString(1);

                if(!name.equals(pswd)){
                    txtOwnerName.setDisable(true);
                    txtOwnerPassword.setDisable(true);
                    txtOwnerName.setEditable(false);
                    txtOwnerPassword.setEditable(false);
                }else{
                    txtOwnerName.setDisable(false);
                    txtOwnerPassword.setDisable(false);
                    txtOwnerName.setEditable(true);
                    txtOwnerPassword.setEditable(true);
                }
            }else{
                txtOwnerName.setDisable(true);
                txtOwnerPassword.setDisable(true);
                txtOwnerName.setEditable(false);
                txtOwnerPassword.setEditable(false);
                new Alert(Alert.AlertType.WARNING, "No User...!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cashierSearchBtnOnAction(ActionEvent actionEvent) {
        String CashierName=txtCurrentCashierName.getText();
        String CashierPswd=txtCurrentCashierPassword.getText();

        try {
            ResultSet CName =  CrudUtil.execute("SELECT cashierId FROM cashier_loging_detail WHERE userName=?",CashierName);
            ResultSet CPassword =  CrudUtil.execute("SELECT cashierId FROM cashier_loging_detail WHERE userPassword=?",CashierPswd);

            if (CName.next() && CPassword.next()){
                String name = CName.getString(1);
                String pswd = CPassword.getString(1);

                if(!name.equals(pswd)){
                    txtCashierName.setDisable(true);
                    txtCashierPswd.setDisable(true);
                    txtCashierName.setEditable(false);
                    txtCashierPswd.setEditable(false);
                }else{
                    txtCashierName.setDisable(false);
                    txtCashierPswd.setDisable(false);
                    txtCashierName.setEditable(true);
                    txtCashierPswd.setEditable(true);
                }
            }else{
                txtCashierName.setDisable(true);
                txtCashierPswd.setDisable(true);
                txtCashierName.setEditable(false);
                txtCashierPswd.setEditable(false);
                new Alert(Alert.AlertType.WARNING, "No User...!").show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showOwnerPswdOnAction(ActionEvent actionEvent) {
        if (checkBox1.isSelected()){
            txtOwnerPassword.setVisible(false);
            txtTextOwnerPassword.setVisible(true);
            txtTextOwnerPassword.setText(txtOwnerPassword.getText());

            txtConfirmOwnerPassword.setVisible(false);
            txtTextOwnerConfirmPswd.setVisible(true);
            txtTextOwnerConfirmPswd.setText(txtConfirmOwnerPassword.getText());
        }else {
            txtTextOwnerPassword.setVisible(false);
            txtTextOwnerConfirmPswd.setVisible(false);
            txtOwnerPassword.setVisible(true);
            txtConfirmOwnerPassword.setVisible(true);
        }
    }

    public void showCashierPasswordOnAction(ActionEvent actionEvent) {
        if (checkBox2.isSelected()){
            txtCashierPswd.setVisible(false);
            txtTextCashierPassword.setVisible(true);
            txtTextCashierPassword.setText(txtCashierPswd.getText());

            txtConfirmCashierPassword.setVisible(false);
            txtTextConfirmCashPswd.setVisible(true);
            txtTextConfirmCashPswd.setText(txtConfirmCashierPassword.getText());
        }else {
            txtCashierPswd.setVisible(true);
            txtConfirmCashierPassword.setVisible(true);
            txtTextCashierPassword.setVisible(false);
            txtTextConfirmCashPswd.setVisible(false);
        }
    }

    public void Owner_Password_Confirm(KeyEvent keyEvent) {
        String pswd = txtOwnerPassword.getText();
        String Cpswd = txtConfirmOwnerPassword.getText();

        if (!pswd.equals(Cpswd)){
            btnUpdateOwner.setDisable(true);
            txtConfirmOwnerPassword.setStyle("-fx-border-color: RED");
            txtOwnerPassword.setStyle("-fx-border-color: RED");
        }else{
            btnUpdateOwner.setDisable(false);
            txtConfirmOwnerPassword.setStyle("-fx-border-color: GREEN");
            txtOwnerPassword.setStyle("-fx-border-color: GREEN");
        }
    }

    public void Cashier_Password_Confirm(KeyEvent keyEvent) {
        String pswd = txtCashierPswd.getText();
        String Cpswd = txtConfirmCashierPassword.getText();

        if (!pswd.equals(Cpswd)){
            btnUpdateOwner.setDisable(true);
            txtConfirmCashierPassword.setStyle("-fx-border-color: RED");
            txtCashierPswd.setStyle("-fx-border-color: RED");
        }else{
            btnUpdateCashier.setDisable(false);
            txtConfirmCashierPassword.setStyle("-fx-border-color: GREEN");
            txtCashierPswd.setStyle("-fx-border-color: GREEN");
        }
    }
}
