/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dao;

import entities.Photo;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taleb
 */
public class PhotoJpaController extends AbstractController<Photo> implements Serializable {

    private static final long serialVersionUID = 8850181713970394057L;

    public PhotoJpaController(EntityManagerFactory emf) {
        super(Photo.class);
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

  
}
