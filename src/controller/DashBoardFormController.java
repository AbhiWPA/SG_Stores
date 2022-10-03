package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import extra.NotificationController;
import interfaces.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import util.CrudUtil;

import javax.management.Notification;
import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashBoardFormController implements Loader {
    public AnchorPane loginPane;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane context;
    public JFXButton btnLogin;
    public MediaView mediaView;

    String userName="Abhishek";
    String password="Abhishek";

//    public void setUserNamePswd(){
//        String ownerUName =  CrudUtil.execute("SELECT * FROM Customer WHERE C_ID=? OR C_name=?",txtUpdateID.getText(), txtUpdateName.getText());
//    }

    @Override
    public void loadUi(String location) throws IOException {
        Stage  stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setResizable(false);
        stage2.show();
    }

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        loginPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SignUpForm.fxml"));
        loginPane.getChildren().add(parent);
    }

//    private void search() throws SQLException, ClassNotFoundException {
//        ResultSet result1 = CrudUtil.execute("SELECT userID FROM user WHERE userName=?", txtUserName.getText());
//        ResultSet result2 = CrudUtil.execute("SELECT userID FROM user WHERE userPassword=?", txtPassword.getText());
//        if (result1.equals(result2)) {
//
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
//        }
//    }

    public void loginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        NotificationController nf = new NotificationController();

        String UName = txtUserName.getText();
        String UPassword = txtPassword.getText();

        String CName = txtUserName.getText();
        String CPassword = txtPassword.getText();

        ResultSet resultSet1 = CrudUtil.execute("SELECT ownerId FROM owner_loging_detail WHERE userName=?", UName);
        ResultSet resultSet2 = CrudUtil.execute("SELECT ownerId FROM owner_loging_detail WHERE userPassword=?", UPassword);

        ResultSet resultSet3 = CrudUtil.execute("SELECT cashierId FROM cashier_loging_detail WHERE userName=?", CName);
        ResultSet resultSet4 = CrudUtil.execute("SELECT cashierId FROM cashier_loging_detail WHERE userPassword=?", CPassword);

        if (resultSet1.next() && resultSet2.next()) {
            String userName = resultSet1.getString(1);
            String userPassword = resultSet2.getString(1);

            if (userName.equals(userPassword)) {
                loadUi("MainForm");
                nf.confirmMassage("Login Successful...!","Hello, Welcome to the S.G. Vegetables & Fruits shop...!");
            }
        } else if (resultSet3.next() && resultSet4.next()){
                String userName = resultSet3.getString(1);
                String userPassword = resultSet4.getString(1);

                if (userName.equals(userPassword)) {
                    loadUi("CashierDashBoardForm");
                    nf.confirmMassage("Login Successfull...!", "Hello, Welcome to the S.G. Vegetables & Fruits shop...!");
                }
        }else{
            nf.errorMassage("Login Failed...!","Please enter user name & password correctly...!");
        }
    }
}
