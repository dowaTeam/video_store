/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.videostore.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "workers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workers.findAll", query = "SELECT w FROM Workers w"),
    @NamedQuery(name = "Workers.findById", query = "SELECT w FROM Workers w WHERE w.id = :id"),
    @NamedQuery(name = "Workers.findByName", query = "SELECT w FROM Workers w WHERE w.name = :name"),
    @NamedQuery(name = "Workers.findByFLastname", query = "SELECT w FROM Workers w WHERE w.fLastname = :fLastname"),
    @NamedQuery(name = "Workers.findBySLastname", query = "SELECT w FROM Workers w WHERE w.sLastname = :sLastname"),
    @NamedQuery(name = "Workers.findByIsAdmin", query = "SELECT w FROM Workers w WHERE w.isAdmin = :isAdmin"),
    @NamedQuery(name = "Workers.findByAddress", query = "SELECT w FROM Workers w WHERE w.address = :address"),
    @NamedQuery(name = "Workers.findByPhone", query = "SELECT w FROM Workers w WHERE w.phone = :phone"),
    @NamedQuery(name = "Workers.findByEmail", query = "SELECT w FROM Workers w WHERE w.email = :email"),
    @NamedQuery(name = "Workers.findByPassword", query = "SELECT w FROM Workers w WHERE w.password = :password")})
public class Workers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "f_lastname")
    private String fLastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "s_lastname")
    private String sLastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password")
    private String password;

    public Workers() {
    }

    public Workers(Integer id) {
        this.id = id;
    }

    public Workers(Integer id, String name, String fLastname, String sLastname, boolean isAdmin, String address, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.fLastname = fLastname;
        this.sLastname = sLastname;
        this.isAdmin = isAdmin;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workers)) {
            return false;
        }
        Workers other = (Workers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.videostore.jpa.Workers[ id=" + id + " ]";
    }
    
}
