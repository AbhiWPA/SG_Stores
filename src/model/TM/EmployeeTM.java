package model.TM;

import javafx.scene.control.Button;

public class EmployeeTM {
    private String E_ID;
    private String name;
    private String address;
    private String NIC;
    private double E_Salary;
    private String Contact;

    public EmployeeTM() {
    }

    public EmployeeTM(String e_ID, String name, String address, String NIC, double e_Salary, String contact) {
        E_ID = e_ID;
        this.name = name;
        this.address = address;
        this.NIC = NIC;
        E_Salary = e_Salary;
        Contact = contact;
    }

    public String getE_ID() {
        return E_ID;
    }

    public void setE_ID(String e_ID) {
        E_ID = e_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public double getE_Salary() {
        return E_Salary;
    }

    public void setE_Salary(double e_Salary) {
        E_Salary = e_Salary;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "E_ID='" + E_ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", NIC='" + NIC + '\'' +
                ", E_Salary=" + E_Salary +
                ", Contact='" + Contact + '\'' +
                '}';
    }
}
