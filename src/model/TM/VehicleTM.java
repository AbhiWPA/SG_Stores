package model.TM;

import javafx.scene.control.Button;

public class VehicleTM {
    private String Vnumber;
    private String Vtype;
    private String modl;
    private String date;

    public VehicleTM() {
    }

    public VehicleTM(String vnumber, String vtype, String modl, String date) {
        Vnumber = vnumber;
        Vtype = vtype;
        this.modl = modl;
        this.date = date;
    }

    public String getVnumber() {
        return Vnumber;
    }

    public void setVnumber(String vnumber) {
        Vnumber = vnumber;
    }

    public String getVtype() {
        return Vtype;
    }

    public void setVtype(String vtype) {
        Vtype = vtype;
    }

    public String getModl() {
        return modl;
    }

    public void setModl(String modl) {
        this.modl = modl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VehicleTM{" +
                "Vnumber='" + Vnumber + '\'' +
                ", Vtype='" + Vtype + '\'' +
                ", modl='" + modl + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
