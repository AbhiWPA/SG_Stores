package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.TM.BillTM;
import model.TM.CustomerTM;
import util.CrudUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class BillFormController implements Initializable {
    public Label lblOrderId;
    public Label lblDate;
    public Label lblCustomerName;
    public Label lblTime;
    public Label lblTotal;

    public TableView tblBill;
    public TableColumn colDesc;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDateAndTime();

        try {
            setTableDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    private void setTableDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT description, qty, unitPrice, amount FROM bill");
        ObservableList<BillTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new BillTM(
                            result.getString("description"),
                            result.getInt("qty"),
                            result.getDouble("unitPrice"),
                            result.getDouble("amount")
                    )
            );
        }
        tblBill.setItems(obList);
    }

    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("MMMM/dd/yyyy ").format(new Date()));

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm");
            String time = currentTime.format(dateTimeFormatter);
            lblTime.setText(time);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }



}
