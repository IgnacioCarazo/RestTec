package com.example.restecmobile.models;
/**
 * @class Cliente
 * Clase constructura de Cliente
 * @author JosephJ
 */
public class Cliente {
    private int ID;
    private String name;
    private String primaryLastName;
    private String secondLastName;
    private String province;
    private String canton;
    private String distrito;
    private String birthday;
    private int celNum;
    private String email;
    private String password;
    public Cliente() {
        this.ID = 0;
        this.name = null;
        this.primaryLastName = null;
        this.secondLastName = null;
        this.province = null;
        this.canton = null;
        this.distrito = null;
        this.birthday = null;
        this.celNum = 0;
        this.email = null;
        this.password = null;
    }
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPrimaryLastName() { return primaryLastName; }
    public void setPrimaryLastName(String primaryLastName) { this.primaryLastName = primaryLastName; }

    public String getSecondLastName() { return secondLastName; }
    public void setSecondLastName(String secondLastName) { this.secondLastName = secondLastName; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCanton() { return canton; }
    public void setCanton(String canton) { this.canton = canton; }

    public String getDistrito() { return distrito; }
    public void setDistrito(String distrito) { this.distrito = distrito; }

    public String getBirthday() { return birthday; }
    public void setBirthday(String birthday) { this.birthday = birthday; }

    public int getCelNum() { return celNum; }
    public void setCelNum(int celNum) { this.celNum = celNum; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
