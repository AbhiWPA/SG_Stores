package model.TM;

public class WholeSaleTM {
    private String Item_code;
    private String Item_name;
    private String clientID;
    private String clientName;
    private int Qty;
    private double cost;

    public WholeSaleTM() {
    }

    public WholeSaleTM(String item_code, String item_name, String clientID, String clientName, int qty, double cost) {
        Item_code = item_code;
        Item_name = item_name;
        this.clientID = clientID;
        this.clientName = clientName;
        Qty = qty;
        this.cost = cost;
    }

    public String getItem_code() {
        return Item_code;
    }

    public void setItem_code(String item_code) {
        Item_code = item_code;
    }

    public String getItem_name() {
        return Item_name;
    }

    public void setItem_name(String item_name) {
        Item_name = item_name;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "WholeSaleTM{" +
                "Item_code='" + Item_code + '\'' +
                ", Item_name='" + Item_name + '\'' +
                ", clientID='" + clientID + '\'' +
                ", clientName='" + clientName + '\'' +
                ", Qty=" + Qty +
                ", cost=" + cost +
                '}';
    }
}
