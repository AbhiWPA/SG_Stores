package controller;

import com.jfoenix.controls.JFXButton;
import interfaces.Loader;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainFormController implements Loader {
    public AnchorPane context;
    public JFXButton btnLogOut;
    public JFXButton btnDashBoard;
    public JFXButton btnSellings;
    public JFXButton btnVegetables;
    public JFXButton btnFruits;
    public JFXButton btnCustomers;
    public JFXButton btnEmployees;
    public JFXButton btnOrders;
    public JFXButton btnVehicles;
    public JFXButton btnBranches;
    public JFXButton btnSettings;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnClients;
    public Label lblTotalEmps;
    public Label lblTotalVegetables;
    public Label lblTotalFruits;

    public void initialize(){
        loadDateAndTime();

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

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("EEEE, MMMM dd, yyyy ").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            String time = currentTime.format(dateTimeFormatter);
            lblTime.setText(time);
        }),
            new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void tittleBarOnAction(MouseEvent mouseEvent) {
    }

    @Override
    public void loadUi(String location) throws IOException {
        context.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        context.getChildren().add(parent);
    }

    public void logoutBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.close();

        Parent parent = FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.show();
    }

    public void dashboardOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("InnerDashBoardForm");
    }

    public void sellingsBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SellingForm");
    }

    public void vegetableBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("VegetableForm");
    }

    public void fruitsBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("FruitsForm");
    }

    public void customersBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("CustomerForm");
    }

    public void employeesBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("EmployeesForm");
    }

    public void orderBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("OrderForm");
    }

    public void vehiclesBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("VehiclesForm");
    }

    public void branchesBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("BranchesForm");
    }

    public void settingsBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("SettingsForm");
    }

    public void clientsBtnOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("ClientsForm");
    }

    public void wholesaleOrdersOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/WholesaleOrdersForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setTitle("Wholesale Form (Our Clients)");
        stage2.show();
    }

    public void calculatorOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CalculatorForm.fxml"));
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(parent));
        stage2.setTitle("Calculator");
        stage2.show();
    }

    private void setTotalVegetables() throws SQLException, ClassNotFoundException {
        int vegetableCount=0;
        ResultSet resultSet = CrudUtil.execute("SELECT item_code FROM items WHERE Item_code LIKE 'V%';");

        while (resultSet.next()){
            vegetableCount++;
        }
        lblTotalVegetables.setText(String.valueOf(vegetableCount));
    }

    private void setTotalFruits() throws SQLException, ClassNotFoundException {
        int fruitCount=0;
        ResultSet resultSet = CrudUtil.execute("SELECT item_code FROM items WHERE Item_code LIKE 'F%';");

        while (resultSet.next()){
            fruitCount++;
        }
        lblTotalFruits.setText(String.valueOf(fruitCount));
    }

    private void setTotalEmployees() throws SQLException, ClassNotFoundException {
        int empCount=0;
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM employee");
        while (resultSet.next()){
            empCount++;
        }
        lblTotalEmps.setText(String.valueOf(empCount));
    }


}
