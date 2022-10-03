package model.TM;

public class ClientTM {
    private String Cl_ID;
    private String name;

    public ClientTM() {
    }

    public ClientTM(String cl_ID, String name) {
        Cl_ID = cl_ID;
        this.name = name;
    }

    public String getCl_ID() {
        return Cl_ID;
    }

    public void setCl_ID(String cl_ID) {
        Cl_ID = cl_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClientTM{" +
                "Cl_ID='" + Cl_ID + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
