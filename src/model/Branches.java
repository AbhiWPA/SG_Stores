package model;

public class Branches {
    private String Br_code;
    private String address;
    private String mail;
    private int No_Of_Emps;
    private String Br_ManagerID;
    private String Br_ManagerName;
    private int contact;

    public Branches() {
    }

    public Branches(String br_code, String address, String mail, int no_Of_Emps, String br_ManagerID, String br_ManagerName, int contact) {
        Br_code = br_code;
        this.address = address;
        this.mail = mail;
        No_Of_Emps = no_Of_Emps;
        Br_ManagerID = br_ManagerID;
        Br_ManagerName = br_ManagerName;
        this.contact = contact;
    }

    public String getBr_code() {
        return Br_code;
    }

    public void setBr_code(String br_code) {
        Br_code = br_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNo_Of_Emps() {
        return No_Of_Emps;
    }

    public void setNo_Of_Emps(int no_Of_Emps) {
        No_Of_Emps = no_Of_Emps;
    }

    public String getBr_ManagerID() {
        return Br_ManagerID;
    }

    public void setBr_ManagerID(String br_ManagerID) {
        Br_ManagerID = br_ManagerID;
    }

    public String getBr_ManagerName() {
        return Br_ManagerName;
    }

    public void setBr_ManagerName(String br_ManagerName) {
        Br_ManagerName = br_ManagerName;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Branches{" +
                "Br_code='" + Br_code + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", No_Of_Emps=" + No_Of_Emps +
                ", Br_ManagerID='" + Br_ManagerID + '\'' +
                ", Br_ManagerName='" + Br_ManagerName + '\'' +
                ", contact=" + contact +
                '}';
    }
}
