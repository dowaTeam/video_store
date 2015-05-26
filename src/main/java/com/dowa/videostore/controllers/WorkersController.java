package com.dowa.videostore.controllers;

import com.dowa.videostore.model.Worker;
import com.dowa.videostore.persistence.WorkerService;
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
        return "Create.faces";
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
}
