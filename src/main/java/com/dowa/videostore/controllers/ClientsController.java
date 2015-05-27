package com.dowa.videostore.controllers;

import com.dowa.videostore.model.Client;
import com.dowa.videostore.persistence.ClientService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andres on 26/05/2015.
 */
public class ClientsController {
    private ClientService clientService;
    private List<Client> items;
    private Client current;
    public ClientsController() {
        clientService = new ClientService();
        System.out.println("#######created ClientController");
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
            current = clientService.find(id);
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
            clientService.persistClient(current);
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
            clientService.updateClient(current);
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
            clientService.deleteClient(id);
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
            totalRows =  clientService.count();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            totalRows = -1;
        }
        return totalRows;
    }

    public List<Client> getItems(){
        if(items == null){
            try {
                items = clientService.findAll();
            } catch (SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
            }
        }
        return items;
    }

    public Client getSelected() {
        if (current == null) {
            current = new Client();
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
