package model.TM;

import javafx.scene.control.Button;

public class ItemsTM {
    private String Item_code;
    private String descrip;
    private int Qty_On_Hand;
    private double Price;

    public ItemsTM() {
    }

    public ItemsTM(String item_code, String descrip, int qty_On_Hand, double price) {
        Item_code = item_code;
        this.descrip = descrip;
        Qty_On_Hand = qty_On_Hand;
        Price = price;
    }

    public String getItem_code() {
        return Item_code;
    }

    public void setItem_code(String item_code) {
        Item_code = item_code;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getQty_On_Hand() {
        return Qty_On_Hand;
    }

    public void setQty_On_Hand(int qty_On_Hand) {
        Qty_On_Hand = qty_On_Hand;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "ItemsTM{" +
                "Item_code='" + Item_code + '\'' +
                ", descrip='" + descrip + '\'' +
                ", Qty_On_Hand=" + Qty_On_Hand +
                ", Price=" + Price +
                '}';
    }
}
