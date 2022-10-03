package model;

public class OrderDetail {
    private String Ord_ID;
    private String Item_code;
    private int Qty;
    private double unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(String ord_ID, String item_code, int qty, double unitPrice) {
        Ord_ID = ord_ID;
        Item_code = item_code;
        Qty = qty;
        this.unitPrice = unitPrice;
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

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "Ord_ID='" + Ord_ID + '\'' +
                ", Item_code='" + Item_code + '\'' +
                ", Qty=" + Qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
