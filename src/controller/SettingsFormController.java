package controller;

import interfaces.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SettingsFormController implements Loader{
    public AnchorPane securityContext;

    public void securityBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SecurityForm");
    }

    public void reportsBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("IncomeReports");
    }

    public void aboutBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("AboutForm");
    }

    public void deleteThingsBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeleteThingsForm");
    }

    @Override
    public void loadUi(String location) throws IOException {
        securityContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        securityContext.getChildren().add(parent);
    }


}
