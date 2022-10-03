package model;

import javafx.scene.control.Button;

public class Orders {
    private String Ord_ID;
    private String Item_code;
    private String description;
    private double UnitPrice;
    private int Qty;
    private double total;
    private Button btn;

    public Orders() {
    }

    public Orders(String ord_ID, String item_code, String description, double unitPrice, int qty, double total, Button btn) {
        Ord_ID = ord_ID;
        Item_code = item_code;
        this.description = description;
        UnitPrice = unitPrice;
        Qty = qty;
        this.total = total;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "Ord_ID='" + Ord_ID + '\'' +
                ", Item_code='" + Item_code + '\'' +
                ", description='" + description + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", Qty=" + Qty +
                ", total=" + total +
                ", btn=" + btn +
                '}';
    }
}
