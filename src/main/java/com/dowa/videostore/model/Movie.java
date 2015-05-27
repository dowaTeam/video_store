package com.dowa.videostore.model;

import org.primefaces.model.UploadedFile;
import java.util.Date;

/**
 * Created by Andres on 27/05/2015.
 */
public class Movie {
    private int id;
    private  String name;
    private int idGenre;
    private String genreName;
    private String synopsis;
    private int length;
    private Date registrationDate;
    private int dispDvd;
    private int dispBray;
    private String imagePath;
    private UploadedFile file;

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

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
