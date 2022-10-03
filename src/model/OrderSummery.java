package model;

public class OrderSummery {
    private String orId;
    private String customerId;
    private double total;
    private String date;

    public OrderSummery() {
    }

    public OrderSummery(String orId, String customerId, double total, String date) {
        this.orId = orId;
        this.customerId = customerId;
        this.total = total;
        this.date = date;
    }

    public String getOrId() {
        return orId;
    }

    public void setOrId(String orId) {
        this.orId = orId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
        return "OrderSummery{" +
                "orId='" + orId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
