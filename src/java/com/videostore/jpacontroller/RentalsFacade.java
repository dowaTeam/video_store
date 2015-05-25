/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.videostore.jpacontroller;

import com.videostore.jpa.Rentals;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres
 */
@Stateless
public class RentalsFacade extends AbstractFacade<Rentals> {
    @PersistenceContext(unitName = "VideoStorePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RentalsFacade() {
        super(Rentals.class);
    }
    
}
