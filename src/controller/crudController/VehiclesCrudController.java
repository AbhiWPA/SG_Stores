package controller.crudController;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehiclesCrudController {

    public static ArrayList<String> getVehicleNumbers() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT VNumber FROM vehicles");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
