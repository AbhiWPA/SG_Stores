package model.TM;

public class BranchesTM {
    private String code;
    private String address;
    private String mail;
    private int employees;
    private String managerID;
    private String managerName;
    private int contact;

    public BranchesTM() {
    }

    public BranchesTM(String code, String address, String mail, int employees, String managerID, String managerName, int contact) {
        this.code = code;
        this.address = address;
        this.mail = mail;
        this.employees = employees;
        this.managerID = managerID;
        this.managerName = managerName;
        this.contact = contact;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "BranchesTM{" +
                "code='" + code + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", employees=" + employees +
                ", managerID='" + managerID + '\'' +
                ", managerName='" + managerName + '\'' +
                ", contact=" + contact +
                '}';
    }
}
