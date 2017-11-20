/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.provider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author taleb
 */
public class EMF_Provider {
    private final static String  DEFAULT_PU ="AutoEcoleBronzzaPU";
    private final static EntityManagerFactory EMF;
    static {
        EMF = Persistence.createEntityManagerFactory(DEFAULT_PU);
    }
    
    public static EntityManagerFactory getCurrentSessionEMF(){
        return EMF;
    }
    
}
