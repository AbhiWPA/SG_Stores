package model.TM;

import javafx.scene.control.Button;

public class CustomerTM {
    private String C_ID;
    private String C_name;
    private String C_address;
    private String C_contact;

    public CustomerTM() {
    }

    public CustomerTM(String c_ID, String c_name, String c_address, String c_contact) {
        C_ID = c_ID;
        C_name = c_name;
        C_address = c_address;
        C_contact = c_contact;
    }

    public String getC_ID() {
        return C_ID;
    }

    public void setC_ID(String c_ID) {
        C_ID = c_ID;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public String getC_address() {
        return C_address;
    }

    public void setC_address(String c_address) {
        C_address = c_address;
    }

    public String getC_contact() {
        return C_contact;
    }

    public void setC_contact(String c_contact) {
        C_contact = c_contact;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "C_ID='" + C_ID + '\'' +
                ", C_name='" + C_name + '\'' +
                ", C_address='" + C_address + '\'' +
                ", C_contact='" + C_contact + '\'' +
                '}';
    }
}
