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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andres
 */
@Entity
@Table(name = "rentals")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rentals.findAll", query = "SELECT r FROM Rentals r"),
    @NamedQuery(name = "Rentals.findById", query = "SELECT r FROM Rentals r WHERE r.id = :id"),
    @NamedQuery(name = "Rentals.findByIdClient", query = "SELECT r FROM Rentals r WHERE r.idClient = :idClient"),
    @NamedQuery(name = "Rentals.findByIdWorker", query = "SELECT r FROM Rentals r WHERE r.idWorker = :idWorker"),
    @NamedQuery(name = "Rentals.findByMovies", query = "SELECT r FROM Rentals r WHERE r.movies = :movies"),
    @NamedQuery(name = "Rentals.findByAmount", query = "SELECT r FROM Rentals r WHERE r.amount = :amount"),
    @NamedQuery(name = "Rentals.findByDeliveryDate", query = "SELECT r FROM Rentals r WHERE r.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "Rentals.findByCheckoutDate", query = "SELECT r FROM Rentals r WHERE r.checkoutDate = :checkoutDate")})
public class Rentals implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_client")
    private int idClient;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_worker")
    private int idWorker;
    @Basic(optional = false)
    @NotNull
    @Column(name = "movies")
    private int movies;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private float amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "delivery_date")
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "checkout_date")
    @Temporal(TemporalType.DATE)
    private Date checkoutDate;

    public Rentals() {
    }

    public Rentals(Integer id) {
        this.id = id;
    }

    public Rentals(Integer id, int idClient, int idWorker, int movies, float amount, Date deliveryDate, Date checkoutDate) {
        this.id = id;
        this.idClient = idClient;
        this.idWorker = idWorker;
        this.movies = movies;
        this.amount = amount;
        this.deliveryDate = deliveryDate;
        this.checkoutDate = checkoutDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public int getMovies() {
        return movies;
    }

    public void setMovies(int movies) {
        this.movies = movies;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
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
        if (!(object instanceof Rentals)) {
            return false;
        }
        Rentals other = (Rentals) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.videostore.jpa.Rentals[ id=" + id + " ]";
    }
    
}
