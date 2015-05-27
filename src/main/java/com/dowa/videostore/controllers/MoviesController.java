package com.dowa.videostore.controllers;

import com.dowa.videostore.Util.FileManager;
import com.dowa.videostore.model.Movie;
import com.dowa.videostore.persistence.MovieService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andres on 27/05/2015.
 */
public class MoviesController {
    private MovieService movieService;
    private List<Movie> items;
    private Movie current;
    public MoviesController() {
        movieService = new MovieService();
        System.out.println("#######created MovieController");
        items = null;
    }

    public String prepareList(){
        recreateModel();
        return "List.faces";
    }

    public String prepareCreate(){
        recreateModel();
        recreateCurrent();
        return "Create.faces";
    }

    public String prepareEdit(){
        int id =new Integer (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
        try {
            current = movieService.find(id);
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
            movieService.persistMovie(current);
            FileManager.handleFileUpload(current.getFile());
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
            if(current.getFile() != null){
                System.out.println("Archivo agregado");
                FileManager.deleteFile(current.getImagePath());
                FileManager.handleFileUpload(current.getFile());
                current.setImagePath(current.getFile().getFileName());
            }
            movieService.updateMovie(current);
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
            movieService.deleteMovie(id);
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
            totalRows =  movieService.count();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            totalRows = -1;
        }
        return totalRows;
    }

    public List<Movie> getItems(){
        if(items == null){
            try {
                items = movieService.findAll();
            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
            }
        }
        return items;
    }

    public Movie getSelected() {
        if (current == null) {
            current = new Movie();
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
