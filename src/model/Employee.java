package model;

public class Employee {
    private String E_ID;
    private String E_Name;
    private String E_Address;
    private String E_NIC;
    private String E_Contact;
    private double E_salary;

    public Employee() {
    }

    public Employee(String e_ID, String e_Name, String e_Address, String e_NIC, String e_Contact, double e_salary) {
        E_ID = e_ID;
        E_Name = e_Name;
        E_Address = e_Address;
        E_NIC = e_NIC;
        E_Contact = e_Contact;
        E_salary = e_salary;
    }

    public String getE_ID() {
        return E_ID;
    }

    public void setE_ID(String e_ID) {
        E_ID = e_ID;
    }

    public String getE_Name() {
        return E_Name;
    }

    public void setE_Name(String e_Name) {
        E_Name = e_Name;
    }

    public String getE_Address() {
        return E_Address;
    }

    public void setE_Address(String e_Address) {
        E_Address = e_Address;
    }

    public String getE_NIC() {
        return E_NIC;
    }

    public void setE_NIC(String e_NIC) {
        E_NIC = e_NIC;
    }

    public String getE_Contact() {
        return E_Contact;
    }

    public void setE_Contact(String e_Contact) {
        E_Contact = e_Contact;
    }

    public double getE_salary() {
        return E_salary;
    }

    public void setE_salary(double e_salary) {
        E_salary = e_salary;
    }

    @Override
    public String
    toString() {
        return "Employee{" +
                "E_ID='" + E_ID + '\'' +
                ", E_Name='" + E_Name + '\'' +
                ", E_Address='" + E_Address + '\'' +
                ", E_NIC='" + E_NIC + '\'' +
                ", E_Contact='" + E_Contact + '\'' +
                ", E_salary=" + E_salary +
                '}';
    }
}
