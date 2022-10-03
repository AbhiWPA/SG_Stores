package model;

public class Clients {
    private String Cl_ID;
    private String Cl_Name;
    private String Cl_Address;
    private String Cl_Mail;

    public Clients() {
    }

    public Clients(String cl_ID, String cl_Name, String cl_Address, String cl_Mail) {
        Cl_ID = cl_ID;
        Cl_Name = cl_Name;
        Cl_Address = cl_Address;
        Cl_Mail = cl_Mail;
    }

    public String getCl_ID() {
        return Cl_ID;
    }

    public void setCl_ID(String cl_ID) {
        Cl_ID = cl_ID;
    }

    public String getCl_Name() {
        return Cl_Name;
    }

    public void setCl_Name(String cl_Name) {
        Cl_Name = cl_Name;
    }

    public String getCl_Address() {
        return Cl_Address;
    }

    public void setCl_Address(String cl_Address) {
        Cl_Address = cl_Address;
    }

    public String getCl_Mail() {
        return Cl_Mail;
    }

    public void setCl_Mail(String cl_Mail) {
        Cl_Mail = cl_Mail;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "Cl_ID='" + Cl_ID + '\'' +
                ", Cl_Name='" + Cl_Name + '\'' +
                ", Cl_Address='" + Cl_Address + '\'' +
                ", Cl_Mail='" + Cl_Mail + '\'' +
                '}';
    }
}
