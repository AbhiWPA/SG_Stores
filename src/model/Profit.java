package model;

public class Profit {
    private int No;
    private String ordId;
    private String CustomerId;
    private String date;
    private double profit;

    public Profit() {
    }

    public Profit(int no, String ordId, String customerId, String date, double profit) {
        No = no;
        this.ordId = ordId;
        CustomerId = customerId;
        this.date = date;
        this.profit = profit;
    }

    public int getNo() {
        return No;
    }

    public void setNo(int no) {
        No = no;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Profit{" +
                "No=" + No +
                ", ordId='" + ordId + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", date='" + date + '\'' +
                ", profit=" + profit +
                '}';
    }
}
