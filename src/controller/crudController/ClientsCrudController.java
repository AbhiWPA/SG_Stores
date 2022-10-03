package controller.crudController;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientsCrudController {

    public static ArrayList<String> getClientIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT Cl_ID FROM clients");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
