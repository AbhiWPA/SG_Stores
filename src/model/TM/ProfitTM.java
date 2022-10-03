package model.TM;

public class ProfitTM {
    private String Item_code;
    private String Item_name;
    private double Unit_price;
    private double profit;

    public ProfitTM() {
    }

    public ProfitTM(String item_code, String item_name, double unit_price, double profit) {
        Item_code = item_code;
        Item_name = item_name;
        Unit_price = unit_price;
        this.profit = profit;
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

    public double getUnit_price() {
        return Unit_price;
    }

    public void setUnit_price(double unit_price) {
        Unit_price = unit_price;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "ProfitTM{" +
                "Item_code='" + Item_code + '\'' +
                ", Item_name='" + Item_name + '\'' +
                ", Unit_price=" + Unit_price +
                ", profit=" + profit +
                '}';
    }
}
