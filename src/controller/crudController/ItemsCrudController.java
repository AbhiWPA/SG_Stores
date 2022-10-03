package controller.crudController;

import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsCrudController {
    public static ArrayList<String> getVegetableIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT item_code FROM items WHERE Item_code LIKE 'V%'");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }

    public static ArrayList<String> getFruitIds() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT item_code FROM items WHERE Item_code LIKE 'F%'");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }

    public static ArrayList<String> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT item_code FROM items");
        ArrayList<String> ids= new ArrayList<>();
        while (result.next()){
            ids.add(result.getString(1));
        }
        return ids;
    }
}
