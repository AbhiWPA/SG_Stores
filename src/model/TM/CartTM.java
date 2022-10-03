package model.TM;

import javafx.scene.control.Button;

public class CartTM {
    private String Item_code;
    private String description;
    private double unitPrice;
    private int Qty;
    private double total;
    private Button btn;

    public CartTM() {
    }

    public CartTM(String item_code, String description, double unitPrice, int qty, double total, Button btn) {
        Item_code = item_code;
        this.description = description;
        this.unitPrice = unitPrice;
        Qty = qty;
        this.total = total;
        this.btn = btn;
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
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
        return "CartTM{" +
                "Item_code='" + Item_code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", Qty=" + Qty +
                ", total=" + total +
                ", btn=" + btn +
                '}';
    }
}
