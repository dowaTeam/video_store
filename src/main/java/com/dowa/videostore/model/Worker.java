package com.dowa.videostore.model;

/**
 * Created by Andres on 26/05/2015.
 */
public class Worker {
    private int id;
    private String name;
    private String fLastname;
    private String sLastname;
    private boolean isAdmin;
    private String address;
    private String phone;
    private String email;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
