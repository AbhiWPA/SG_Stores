package model;

public class Order {
    private String Ord_ID;
    private String Item_code;
    private String description;
    private double UnitPrice;
    private int Qty;
    private double total;
    private String date;

    public Order() {
    }

    public Order(String ord_ID, String item_code, String description, double unitPrice, int qty, double total, String date) {
        Ord_ID = ord_ID;
        Item_code = item_code;
        this.description = description;
        UnitPrice = unitPrice;
        Qty = qty;
        this.total = total;
        this.date = date;
    }

    public String getOrd_ID() {
        return Ord_ID;
    }

    public void setOrd_ID(String ord_ID) {
        Ord_ID = ord_ID;
    }

    public String getItem_code() {
        return Item_code;
    }

    public void setItem_code(String item_code) {
        Item_code = item_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
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
        return "Order{" +
                "Ord_ID='" + Ord_ID + '\'' +
                ", Item_code='" + Item_code + '\'' +
                ", description='" + description + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", Qty=" + Qty +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
