package model;

public class WholeSales {
    private String W_Or_ID;
    private String item_code;
    private String item_name;
    private String Cl_ID;
    private String Qty;
    private String cost;

    public WholeSales() {
    }

    public WholeSales(String w_Or_ID, String item_code, String item_name, String cl_ID, String qty, String cost) {
        W_Or_ID = w_Or_ID;
        this.item_code = item_code;
        this.item_name = item_name;
        Cl_ID = cl_ID;
        Qty = qty;
        this.cost = cost;
    }

    public String getW_Or_ID() {
        return W_Or_ID;
    }

    public void setW_Or_ID(String w_Or_ID) {
        W_Or_ID = w_Or_ID;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCl_ID() {
        return Cl_ID;
    }

    public void setCl_ID(String cl_ID) {
        Cl_ID = cl_ID;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "WholeSales{" +
                "W_Or_ID='" + W_Or_ID + '\'' +
                ", item_code='" + item_code + '\'' +
                ", item_name='" + item_name + '\'' +
                ", Cl_ID='" + Cl_ID + '\'' +
                ", Qty='" + Qty + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
