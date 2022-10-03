package model;

public class Sells {
    private String Item_code;
    private String C_ID;
    private String Ord_ID;
    private double total;
    private String date;

    public Sells() {
    }

    public Sells(String item_code, String c_ID, String ord_ID, double total, String date) {
        Item_code = item_code;
        C_ID = c_ID;
        Ord_ID = ord_ID;
        this.total = total;
        this.date = date;
    }

    public String getItem_code() {
        return Item_code;
    }

    public void setItem_code(String item_code) {
        Item_code = item_code;
    }

    public String getC_ID() {
        return C_ID;
    }

    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }

    public String getOrd_ID() {
        return Ord_ID;
    }

    public void setOrd_ID(String ord_ID) {
        Ord_ID = ord_ID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Sells{" +
                "Item_code='" + Item_code + '\'' +
                ", C_ID='" + C_ID + '\'' +
                ", Ord_ID='" + Ord_ID + '\'' +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
