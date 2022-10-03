package controller;

import com.jfoenix.controls.JFXComboBox;
import interfaces.Loader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SellingFormController implements Loader {
    public JFXComboBox cmbTables;
    public AnchorPane vfTablePane;
    public Label lblDate;
    public Label lblTotalFruitSelling;
    public Label lblTotalVegetabeSelling;
    public Label lblTotalItemSelling;

    int fruits;
    int vegetables;
    int total;

    String[] tb = {"Vegetable Items", "Fruit Items"};

    public void initialize(){
        loadDate();
        setData();

        try {
            setFruitsSellings();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            setVegetableSellings();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            setTotalSelliongs();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTotalSelliongs() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM todaysellings");

        while (resultSet.next()){
            total++;
        }
        lblTotalItemSelling.setText(String.valueOf(total));
    }

    private void setVegetableSellings() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT item_code FROM todaysellings WHERE Item_code LIKE 'V%'");

        while (resultSet.next()){
            vegetables++;
        }
        lblTotalVegetabeSelling.setText(String.valueOf(vegetables));
    }

    private void setFruitsSellings() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT item_code FROM todaysellings WHERE Item_code LIKE 'F%'");

        while (resultSet.next()){
            fruits++;
        }
        lblTotalFruitSelling.setText(String.valueOf(fruits));
    }

    private void loadDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }

    private void setData() {
        cmbTables.getItems().addAll(tb);

        cmbTables.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue ) -> {

            if (newValue == "Vegetable Items"){
                try {
                    loadUi("VegetableTableForm");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (newValue == "Fruit Items"){
                try {
                    loadUi("FruitTableForm");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void loadUi(String location) throws IOException {
        vfTablePane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        vfTablePane.getChildren().add(parent);
    }
}
