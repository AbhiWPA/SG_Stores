package model;

public class Items {
    private String ItemCode;
    private String description;
    private int Qty;
    private double price;

    public Items() {
    }

    public Items(String itemCode, String description, int qty, double price) {
        ItemCode = itemCode;
        this.description = description;
        Qty = qty;
        this.price = price;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Items{" +
                "ItemCode='" + ItemCode + '\'' +
                ", description='" + description + '\'' +
                ", Qty=" + Qty +
                ", price=" + price +
                '}';
    }
}
