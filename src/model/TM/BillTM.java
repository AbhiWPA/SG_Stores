package model.TM;

public class BillTM {
    private String description;
    private int qty;
    private double unitPrice;
    private double amount;

    public BillTM() {
    }

    public BillTM(String description, int qty, double unitPrice, double amount) {
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BillTM{" +
                "description='" + description + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                '}';
    }
}
