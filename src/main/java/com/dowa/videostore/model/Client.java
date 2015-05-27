package com.dowa.videostore.model;

import java.util.Date;

/**
 * Created by Andres on 26/05/2015.
 */
public class Client {
    private int id;
    private String name;
    private String fLastname;
    private String sLastname;
    private Date registrationDate;
    private boolean active;
    private float debts;
    private String address;
    private String phone;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFLastname() {
        return fLastname;
    }

    public void setFLastname(String fLastname) {
        this.fLastname = fLastname;
    }

    public String getSLastname() {
        return sLastname;
    }

    public void setSLastname(String sLastname) {
        this.sLastname = sLastname;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registration_date) {
        this.registrationDate = registration_date;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public float getDebts() {
        return debts;
    }

    public void setDebts(float debts) {
        this.debts = debts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
