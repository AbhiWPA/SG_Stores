package model;

import java.sql.Date;

public class Vehicles {
    private String Vnumber;
    private String Vmodel;
    private String Vtype;
    private Date date;

    public Vehicles() {
    }

    public Vehicles(String vnumber, String vmodel, String vtype, Date date) {
        Vnumber = vnumber;
        Vmodel = vmodel;
        Vtype = vtype;
        this.date = date;
    }

    public String getVnumber() {
        return Vnumber;
    }

    public void setVnumber(String vnumber) {
        Vnumber = vnumber;
    }

    public String getVmodel() {
        return Vmodel;
    }

    public void setVmodel(String vmodel) {
        Vmodel = vmodel;
    }

    public String getVtype() {
        return Vtype;
    }

    public void setVtype(String vtype) {
        Vtype = vtype;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vehicles{" +
                "Vnumber='" + Vnumber + '\'' +
                ", Vmodel='" + Vmodel + '\'' +
                ", Vtype='" + Vtype + '\'' +
                ", date=" + date +
                '}';
    }
}
