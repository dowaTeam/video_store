/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.videostore.jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "clients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT c FROM Clients c"),
    @NamedQuery(name = "Clients.findById", query = "SELECT c FROM Clients c WHERE c.id = :id"),
    @NamedQuery(name = "Clients.findByName", query = "SELECT c FROM Clients c WHERE c.name = :name"),
    @NamedQuery(name = "Clients.findByFLastName", query = "SELECT c FROM Clients c WHERE c.fLastName = :fLastName"),
    @NamedQuery(name = "Clients.findBySLastName", query = "SELECT c FROM Clients c WHERE c.sLastName = :sLastName"),
    @NamedQuery(name = "Clients.findByRegistrationDate", query = "SELECT c FROM Clients c WHERE c.registrationDate = :registrationDate"),
    @NamedQuery(name = "Clients.findByActive", query = "SELECT c FROM Clients c WHERE c.active = :active"),
    @NamedQuery(name = "Clients.findByDebts", query = "SELECT c FROM Clients c WHERE c.debts = :debts"),
    @NamedQuery(name = "Clients.findByAddress", query = "SELECT c FROM Clients c WHERE c.address = :address"),
    @NamedQuery(name = "Clients.findByPhone", query = "SELECT c FROM Clients c WHERE c.phone = :phone"),
    @NamedQuery(name = "Clients.findByEmail", query = "SELECT c FROM Clients c WHERE c.email = :email")})
public class Clients implements Serializable {
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
    @Column(name = "f_last_name")
    private String fLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "s_last_name")
    private String sLastName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active")
    private boolean active;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debts")
    private Float debts;
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
    @Size(max = 45)
    @Column(name = "email")
    private String email;

    public Clients() {
    }

    public Clients(Integer id) {
        this.id = id;
    }

    public Clients(Integer id, String name, String fLastName, String sLastName, Date registrationDate, boolean active, String address, String phone) {
        this.id = id;
        this.name = name;
        this.fLastName = fLastName;
        this.sLastName = sLastName;
        this.registrationDate = registrationDate;
        this.active = active;
        this.address = address;
        this.phone = phone;
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

    public String getFLastName() {
        return fLastName;
    }

    public void setFLastName(String fLastName) {
        this.fLastName = fLastName;
    }

    public String getSLastName() {
        return sLastName;
    }

    public void setSLastName(String sLastName) {
        this.sLastName = sLastName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Float getDebts() {
        return debts;
    }

    public void setDebts(Float debts) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clients)) {
            return false;
        }
        Clients other = (Clients) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.videostore.jpa.Clients[ id=" + id + " ]";
    }
    
}
