package model;

public class VFItems {
    private String Item_code;
    private String Description;
    private double UnitPrice;
    private int qty;
    private double total;

    public VFItems() {
    }

    public VFItems(String item_code, String description, double unitPrice, int qty, double total) {
        Item_code = item_code;
        Description = description;
        UnitPrice = unitPrice;
        this.qty = qty;
        this.total = total;
    }

    public String getItem_code() {
        return Item_code;
    }

    public void setItem_code(String item_code) {
        Item_code = item_code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String
    toString() {
        return "VFItems{" +
                "Item_code='" + Item_code + '\'' +
                ", Description='" + Description + '\'' +
                ", UnitPrice=" + UnitPrice +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
