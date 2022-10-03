package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TM.CustomerTM;
import model.TM.TodaySellingsTM;
import util.CrudUtil;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FruitTableFormController implements Initializable {
    public TableView tblFruits;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPrice;
    public TableColumn colQty;
    public TableColumn colTotal;

    public void clearBtnOnAction(ActionEvent actionEvent) {
        for ( int i = 0; i<tblFruits.getItems().size(); i++) {
            tblFruits.getItems().clear();
        }

        try {
            CrudUtil.execute("DELETE FROM todaysellings WHERE Item_code LIKE 'F%'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setTableDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("Item_code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void setTableDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM todaysellings WHERE Item_code LIKE 'F%'");
        ObservableList<TodaySellingsTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new TodaySellingsTM(
                            result.getString("Item_Code"),
                            result.getString("description"),
                            result.getDouble("Unit_price"),
                            result.getInt("Qty"),
                            result.getDouble("total")
                    )
            );
        }
        tblFruits.setItems(obList);
    }
}
