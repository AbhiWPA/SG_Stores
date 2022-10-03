package controller.crudController;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerCrudController {

    public static ArrayList<String> getAllIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT C_ID FROM customer");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
