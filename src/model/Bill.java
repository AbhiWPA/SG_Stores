package model;

public class Bill {
    private String orId;
    private String customer;
    private String description;
    private int qty;
    private double unitPrice;
    private double amount;
    private double total;

    public Bill() {
    }

    public Bill(String orId, String customer, String description, int qty, double unitPrice, double amount, double total) {
        this.orId = orId;
        this.customer = customer;
        this.description = description;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.total = total;
    }

    public String getOrId() {
        return orId;
    }

    public void setOrId(String orId) {
        this.orId = orId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "orId='" + orId + '\'' +
                ", customer='" + customer + '\'' +
                ", description='" + description + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", total=" + total +
                '}';
    }
}
