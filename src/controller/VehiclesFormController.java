package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import extra.NotificationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Items;
import model.TM.EmployeeTM;
import model.TM.VehicleTM;
import model.Vehicles;
import util.CrudUtil;
import util.ValidationUtil;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class VehiclesFormController implements Initializable {
    public JFXTextField txtVnumber;
    public JFXTextField txtVmodel;
    public JFXTextField txtDate;
    public JFXComboBox cbxVtype;

    public TableView<VehicleTM> tblVehicle;
    public TableColumn colVehicleNo;
    public TableColumn colVehicleType;
    public TableColumn colVehicleModel;
    public TableColumn colDate;
    public JFXButton btnAddVehicle;
    String Value;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
    NotificationController nfc=new NotificationController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] VType = {"Car", "Van", "Cargo Lorry", "Three Wheel", "Bike"};

        cbxVtype.getItems().addAll(VType);

        cbxVtype.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Value= (String) newValue;
        });

        try {
            setVehicleDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colVehicleNo.setCellValueFactory(new PropertyValueFactory<>("Vnumber"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory<>("Vtype"));
        colVehicleModel.setCellValueFactory(new PropertyValueFactory<>("modl"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        Pattern VehicleNumber = Pattern.compile("^[A-Z]{2,3}-[0-9]{4}$");
        Pattern VehicleModel = Pattern.compile("^[A-z]{3,}$");

        map.put(txtVnumber,VehicleNumber);
        map.put(txtVmodel,VehicleModel);
    }

    private void setVehicleDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM vehicles");
        ObservableList<VehicleTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new VehicleTM(
                            result.getString("Vnumber"),
                            result.getString("Vtype"),
                            result.getString("model"),
                            result.getString("date")
                    )
            );
        }
        tblVehicle.setItems(obList);
    }

    public void addVehicleOnAction(ActionEvent actionEvent) {
        Vehicles v = new Vehicles(txtVnumber.getText(), Value, txtVmodel.getText(), Date.valueOf(txtDate.getText()));

        try {
            if (CrudUtil.execute("INSERT INTO vehicles VALUES (?,?,?,?)", v.getVnumber(), v.getVtype(), v.getVmodel(), v.getDate())){
                nfc.upperConfirmMessage("Saved...!","New Vehicle Saved Successfully...!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            nfc.upperErrorMessage("Saving Error...!","New Vehicle Saved Unsuccessfully...!"+e.getMessage());
        }
    }


    public void Enter_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddVehicle);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddVehicle);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }
}
