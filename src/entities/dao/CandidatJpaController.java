/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dao;

import entities.Candidat;
import entities.Inscription;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TemporalType;

/**
 *
 * @author taleb
 */
public class CandidatJpaController extends AbstractController<Candidat> implements Serializable {

    private static final long serialVersionUID = 4587328936390434391L;

    private EntityManagerFactory emf = null;

    public CandidatJpaController(EntityManagerFactory emf) {
        super(Candidat.class);
        this.emf = emf;
    }

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

  
    public List<Inscription> getListInscription(Long id) {
        lazyEm = getEntityManager();
        try {
            Candidat candidat = lazyEm.find(Candidat.class, id);
            return candidat.getInscriptions();
        } finally {
            new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println("start of passive lazy close");
                        sleep(3000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CandidatJpaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (lazyEm.isOpen()) {
                        System.out.println("closing entity manager");
                        lazyEm.close();
                    }

                }

            }.start();
        }
    }

  
    public Candidat findCandidatEntity(String nom, String prenom, Date dateNaissance) {
        EntityManager em = getEntityManager();
        List<Candidat> resultList = em.createNamedQuery("Candidat.findByOccurence", Candidat.class)
                .setParameter("nom", nom)
                .setParameter("prenom", prenom)
                .setParameter("dateNaissance", dateNaissance,TemporalType.DATE)
                .getResultList();
        
                if (!resultList.isEmpty()) {
                    System.out.println("return value");
                    return resultList.get(0);
                }
        em.close();
                System.out.println("return null");
        return null;
    }

}
