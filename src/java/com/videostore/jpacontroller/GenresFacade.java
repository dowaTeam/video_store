/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.videostore.jpacontroller;

import com.videostore.jpa.Genres;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres
 */
@Stateless
public class GenresFacade extends AbstractFacade<Genres> {
    @PersistenceContext(unitName = "VideoStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GenresFacade() {
        super(Genres.class);
    }
    
}
