package com.dowa.videostore.controllers;

import com.dowa.videostore.model.Worker;
import com.dowa.videostore.persistence.WorkerService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.List;

public class WorkersController {
    private WorkerService workerService;
    private List<Worker> items;
    private Worker current;
    public WorkersController() {
        workerService = new WorkerService();
        System.out.println("#######created WorkerController");
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
            current = workerService.find(id);
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
            workerService.persistWorker(current);
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
            workerService.updateWorker(current);
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
            workerService.deleteWorker(id);
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
            totalRows =  workerService.count();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            totalRows = -1;
        }
        return totalRows;
    }

    public List<Worker> getItems(){
        if(items == null){
            try {
                items = workerService.findAll();
            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
            }
        }
        return items;
    }

    public Worker getSelected() {
        if (current == null) {
            current = new Worker();
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
