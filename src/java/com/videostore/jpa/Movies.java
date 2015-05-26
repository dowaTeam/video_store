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
import javax.persistence.Lob;
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
@Table(name = "movies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movies.findAll", query = "SELECT m FROM Movies m"),
    @NamedQuery(name = "Movies.findById", query = "SELECT m FROM Movies m WHERE m.id = :id"),
    @NamedQuery(name = "Movies.findByName", query = "SELECT m FROM Movies m WHERE m.name = :name"),
    @NamedQuery(name = "Movies.findByGenre", query = "SELECT m FROM Movies m WHERE m.genre = :genre"),
    @NamedQuery(name = "Movies.findByLength", query = "SELECT m FROM Movies m WHERE m.length = :length"),
    @NamedQuery(name = "Movies.findByDispDvd", query = "SELECT m FROM Movies m WHERE m.dispDvd = :dispDvd"),
    @NamedQuery(name = "Movies.findByDispBray", query = "SELECT m FROM Movies m WHERE m.dispBray = :dispBray")})
public class Movies implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "genre")
    private int genre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "synopsis")
    private String synopsis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "length")
    private int length;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_dvd")
    private int dispDvd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disp_bray")
    private int dispBray;

    public Movies() {
    }

    public Movies(Integer id) {
        this.id = id;
    }

    public Movies(Integer id, String name, int genre, String synopsis, int length, int dispDvd, int dispBray) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.synopsis = synopsis;
        this.length = length;
        this.dispDvd = dispDvd;
        this.dispBray = dispBray;
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

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getDispDvd() {
        return dispDvd;
    }

    public void setDispDvd(int dispDvd) {
        this.dispDvd = dispDvd;
    }

    public int getDispBray() {
        return dispBray;
    }

    public void setDispBray(int dispBray) {
        this.dispBray = dispBray;
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
        if (!(object instanceof Movies)) {
            return false;
        }
        Movies other = (Movies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.videostore.jpa.Movies[ id=" + id + " ]";
    }
    
}
