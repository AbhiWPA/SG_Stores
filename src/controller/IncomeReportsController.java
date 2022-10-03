package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TM.CustomerTM;
import model.TM.ProfitTM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import util.CrudUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class IncomeReportsController implements Initializable {
    public TableView<ProfitTM> tblProfit;
    public TableColumn colItemCode;
    public TableColumn colItemName;
    public TableColumn colUnitPrice;
    public TableColumn colProfit;

    public void clearBtnOnAction(ActionEvent actionEvent) {
        for ( int i = 0; i<tblProfit.getItems().size(); i++) {
            tblProfit.getItems().clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setProfitDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("Item_code"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("Item_name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("Unit_price"));
        colProfit.setCellValueFactory(new PropertyValueFactory<>("profit"));
    }

    private void setProfitDetails() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT * FROM import_Items");
        ObservableList<ProfitTM> obList = FXCollections.observableArrayList();
        while (result.next()){
            obList.add(
                    new ProfitTM(
                            result.getString("Item_code"),
                            result.getString("Item_name"),
                            result.getDouble("Unit_Price"),
                            result.getDouble("profit_from_one_item")
                    )
            );
        }
        tblProfit.setItems(obList);
    }


    public void orderDetailsBtnOnAction(ActionEvent actionEvent) {
        try {
            JasperReport compiledReport = (JasperReport) JRLoader.loadObject(this.getClass().getResource("/view/reports/OrderSummeryReport.jasper"));
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReport, null, connection);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException | JRException e) {
            e.printStackTrace();
        }
    }
}
