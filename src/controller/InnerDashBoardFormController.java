package controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InnerDashBoardFormController implements Initializable {
    public AnchorPane context;
    public Label txtTotalEmps;
    public Label txtVegetables;
    public Label txtFruits;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            setTotalEmployees();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            setTotalFruits();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            setTotalVegetables();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setTotalVegetables() throws SQLException, ClassNotFoundException {
        int vegetableCount=0;
        ResultSet resultSet = CrudUtil.execute("SELECT item_code FROM items WHERE Item_code LIKE 'V%'");

        while (resultSet.next()){
            vegetableCount++;
        }
        txtVegetables.setText(String.valueOf(vegetableCount));
    }

    private void setTotalFruits() throws SQLException, ClassNotFoundException {
        int fruitCount=0;
        ResultSet resultSet = CrudUtil.execute("SELECT item_code FROM items WHERE Item_code LIKE 'F%'");

        while (resultSet.next()){
            fruitCount++;
        }
        txtFruits.setText(String.valueOf(fruitCount));
    }

    private void setTotalEmployees() throws SQLException, ClassNotFoundException {
        int empCount=0;
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM employee");
        while (resultSet.next()){
            empCount++;
        }
        txtTotalEmps.setText(String.valueOf(empCount));
    }
}
