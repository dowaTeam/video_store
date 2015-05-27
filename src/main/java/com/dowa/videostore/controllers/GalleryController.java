package com.dowa.videostore.controllers;

import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres on 26/05/2015.
 */
public class GalleryController {
    public static List<String> images;
    public GalleryController(){
        init();
    }
    public void init() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        System.out.println("init called"); //testing to see if it called, currently causing a lot of errors
        images = new ArrayList<String>();
        images.add("green.jpg");
    }

    public List<String> getImages() {
        System.out.println("getImages called"); //testing to see if it called, currently causing a lot of errors
        return images;
    }

}
