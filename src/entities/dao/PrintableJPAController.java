/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dao;

import entities.Printable;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taleb
 */
public class PrintableJPAController extends AbstractController<Printable> implements Serializable {

    private static final long serialVersionUID = 184643216451L;

    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PrintableJPAController(EntityManagerFactory emf) {
        super(Printable.class);
        this.emf = emf;
    }

}
