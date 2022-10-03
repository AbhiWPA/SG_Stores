package controller.crudController;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WholeSaleCrudController {

    public static ArrayList<String> getOrderIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT W_Or_ID FROM wholesaleorders");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
