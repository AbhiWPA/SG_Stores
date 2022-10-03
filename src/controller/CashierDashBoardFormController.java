package controller;

import com.jfoenix.controls.JFXButton;
import interfaces.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierDashBoardFormController implements Loader {
    public Label lblDate;
    public Label lblTime;
    public AnchorPane cashierPane;
    public JFXButton btnLogOut;

    public void customerBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("CustomerForm");
    }

    public void orderBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("OrderForm");
    }

    public void fruitsBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("FruitsForm");
    }

    public void vegetableBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("VegetableForm");
    }

    @Override
    public void loadUi(String location) throws IOException {
        cashierPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        cashierPane.getChildren().add(parent);
    }

    public void logoutBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.show();
    }
}
