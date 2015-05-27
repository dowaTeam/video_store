package com.dowa.videostore.controllers;

import com.dowa.videostore.model.Genre;
import com.dowa.videostore.persistence.GenreService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.List;

public class GenresController {
    private GenreService genreService;
    private List<Genre> items;
    private Genre current;
    public GenresController() {
        genreService = new GenreService();
        System.out.println("#######created GenreController");
    }

    public String prepareList(){
        recreateModel();
        recreateCurrent();
        return "List.faces";
    }

    public String prepareGenres(){
        recreateModel();
        return "/genres/List.faces";
    }

    public String prepareCreate(){
        recreateModel();
        return "Create.faces";
    }

    public String prepareEdit(){
        int id =new Integer (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        try {
            current = genreService.find(id);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ha ocurrido un error"));
            return "List.faces";
        }
        return "Edit.faces";
    }

    public String create(){
        try {
            genreService.persistGenre(current);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se ha agregado satisfactoriamente"));
            recreateCurrent();
            return prepareList();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ha ocurrido un error"));
            return null;
        }
    }

    public String edit(){
        try {
            genreService.updateGenre(current);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se han guardado los cambios"));
            recreateCurrent();
            return prepareList();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ha ocurrido un error"));
            return null;
        }
    }

    public String destroy(){
        try {
            int id =new Integer (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
            genreService.deleteGenre(id);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Exito","Se ha eliminado correctamente"));
            recreateCurrent();
            recreateModel();
            return prepareList();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Ha ocurrido un error"));
            return null;
        }
    }

    public int getRowCount(){
        int totalRows = 0;
        try {
            totalRows =  genreService.count();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            totalRows = -1;
        }
        return totalRows;
    }

    public List<Genre> getItems(){
        if(items == null){
            try {
                items = genreService.findAll();
            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
            }
        }
        return items;
    }

    public Genre getSelected() {
        if (current == null) {
            current = new Genre();
        }
        return current;
    }

    private void recreateModel(){
        items = null;
    }

    private void recreateCurrent(){
        current = null;
    }
}
