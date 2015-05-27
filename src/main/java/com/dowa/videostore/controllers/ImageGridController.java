package com.dowa.videostore.controllers;

import com.dowa.videostore.model.Movie;
import com.dowa.videostore.persistence.MovieService;

import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres on 27/05/2015.
 */
public class ImageGridController {
    public static List<String> images;
    private MovieService movieService;

    public ImageGridController(){
        movieService = new MovieService();
        init();
    }
    public void init() {
        images = new ArrayList<String>();
        try {
            List<Movie> movies = movieService.findAll();
            for(Movie  movie: movies){
                images.add(movie.getImagePath());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getImages() {
        System.out.println("getImages called"); //testing to see if it called, currently causing a lot of errors
        return images;
    }
}
