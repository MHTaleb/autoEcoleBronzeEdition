/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dao;

import entities.Inscription;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taleb
 */
public class InscriptionJpaController extends AbstractController<Inscription> implements Serializable {

    private static final long serialVersionUID = 7293682931855682161L;

    public InscriptionJpaController(EntityManagerFactory emf) {
        super(Inscription.class);
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
