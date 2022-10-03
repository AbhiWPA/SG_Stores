package controller;

import com.jfoenix.controls.JFXButton;
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
import model.Customer;
import model.Employee;
import model.Items;
import model.TM.EmployeeTM;
import model.TM.ItemsTM;
import util.CrudUtil;
import util.ValidationUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class EmployeesFormController implements Initializable {
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtNIC;
    public JFXTextField txtContact;
    public JFXTextField txtSalary;

    public TableView<EmployeeTM> tblEmployee;
    public TableColumn colEmpId;
    public TableColumn clName;
    public TableColumn colAddress;
    public TableColumn colNic;
    public TableColumn colContact;
    public JFXButton btnAddEmployee;
    public TableColumn colSalary;

    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    NotificationController nfc=new NotificationController();

    public void addEmployeeOnAction(ActionEvent actionEvent) {
        Employee emp = new Employee(txtID.getText(), txtName.getText(), txtAddress.getText(), txtNIC.getText(), txtContact.getText(), Double.parseDouble(txtSalary.getText()));

        try {
            if (CrudUtil.execute("INSERT INTO Employee VALUES (?,?,?,?,?,?)",emp.getE_ID(),emp.getE_Name(), emp.getE_Address(), emp.getE_NIC(), emp.getE_Contact(), emp.getE_salary())){
                nfc.upperConfirmMessage("Saved...!","New Employee Saved Successfully...!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            nfc.upperErrorMessage("Saving Error...!","New Employee Saved Unsuccessfully...!, Please enter new employee details correctly...");
        }
    }

    public void updateEmployeeOnAction(ActionEvent actionEvent) {
        Employee emp=new Employee(txtID.getText(),txtName.getText(),txtAddress.getText(),txtNIC.getText(), txtContact.getText(), Double.parseDouble(txtSalary.getText()));

        try{
            if(CrudUtil.execute("UPDATE Employee SET name=?,address=?,NIC=?,Contact=?,salery=?  WHERE E_ID=?",emp.getE_Name(),emp.getE_Address(),emp.getE_NIC(),emp.getE_Contact(),emp.getE_salary(),emp.getE_ID())){
                nfc.upperConfirmMessage("Updated...!","Employee Details Updated Successfully...!");
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
            nfc.upperErrorMessage("Updating Error...!","Update Empolyee Details Unsuccessfully...!,"+e.getMessage());        }
    }

    public void txtEmpIdOnAction(ActionEvent actionEvent) {
        try {
            ResultSet result =  CrudUtil.execute("SELECT * FROM Employee WHERE E_ID=?",txtID.getText());
            if (result.next()) {
                txtName.setText(result.getString(2));
                txtAddress.setText(result.getString(3));
                txtNIC.setText(result.getString(4));
                txtContact.setText(result.getString(5));
                txtSalary.setText(String.valueOf(result.getDouble(6)));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setEmployeeDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colEmpId.setCellValueFactory(new PropertyValueFactory<>("E_ID"));
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("E_Salary"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));

        Pattern idPattern = Pattern.compile("^(E00-)[0-9]{3,5}$");
        Pattern name = Pattern.compile("^[A-z ]{3,15}$");
        Pattern address = Pattern.compile("^[A-z ]{3,25}$");
        Pattern nic = Pattern.compile("^[0-9]{1,15}(V)$");
        Pattern salary = Pattern.compile("^[0-9]{2,}.[0-9]{2}");
        Pattern contact = Pattern.compile("^[0-9]{10}");

        map.put(txtID,idPattern);
        map.put(txtName,name);
        map.put(txtAddress,address);
        map.put(txtNIC,nic);
        map.put(txtContact,contact);
        map.put(txtSalary,salary);
    }

    private void setEmployeeDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM employee");
        ObservableList<EmployeeTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new EmployeeTM(
                            result.getString("E_ID"),
                            result.getString("name"),
                            result.getString("address"),
                            result.getString("NIC"),
                            result.getDouble("salery"),
                            result.getString("Contact")
                    )
            );
        }
        tblEmployee.setItems(obList);
    }

    public void Enter_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnAddEmployee);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnAddEmployee);
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
            }
        }
    }
}
