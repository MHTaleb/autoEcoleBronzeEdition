/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dao;

import entities.Examen;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taleb
 */
public class ExamenJpaController extends AbstractController<Examen> implements Serializable {

    private static final long serialVersionUID = -5243655568412046968L;

    public ExamenJpaController(EntityManagerFactory emf) {
        super(Examen.class);
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

  
    
}
