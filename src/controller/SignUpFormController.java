package controller;

import interfaces.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SignUpFormController{
    public AnchorPane signUpPane;

    public void backButtonOnAction(ActionEvent actionEvent) throws IOException {
        signUpPane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"));
        signUpPane.getChildren().add(parent);
    }


}
