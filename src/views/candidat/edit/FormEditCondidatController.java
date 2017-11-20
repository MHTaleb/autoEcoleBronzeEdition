/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.candidat.edit;

import entities.Candidat;
import entities.CandidatInfo;
import entities.CategoriePermis;
import entities.Inscription;
import entities.dao.CandidatJpaController;
import entities.dao.InscriptionJpaController;
import javafx.util.converter.DateStringConverter;
import jpa.provider.EMF_Provider;
import projectTools.ModalMessageDialog;

/**
 *
 * @author taleb
 */
public class FormEditCondidatController {

    private final CandidatJpaController candidatJpaController;
    private final InscriptionJpaController inscriptionJpaController;


    public FormEditCondidatController() {
        this.candidatJpaController = new CandidatJpaController(EMF_Provider.getCurrentSessionEMF());
        this.inscriptionJpaController = new InscriptionJpaController(EMF_Provider.getCurrentSessionEMF());
      
    }
     
    
    void edit(Long editCandidat, String nom, String prenom,String nomPere,String nomMere, String numeroTelephone, String lieuNaissance, String adresse, String dateIns, String dateNaissance, String nationalite, String permisDemander) {
        Candidat candidat = candidatJpaController.find(editCandidat);
        CandidatInfo candidatInfo = candidat.getCandidatInfo();
        candidatInfo.setNom(nom);
        candidatInfo.setNomMere(nomMere);
        candidatInfo.setNomPere(nomPere);
        candidatInfo.setPrenom(prenom);
        candidatInfo.setAdresse(adresse);
        candidatInfo.setLieuNaissance(lieuNaissance);
        candidatInfo.setDateNaissance(new DateStringConverter("dd-MM-yyyy").fromString(dateNaissance));
        final Inscription firstIns = candidatJpaController.getListInscription(candidat.getId()).get(0);
        firstIns.setDateInscription(new DateStringConverter("dd-MM-yyyy").fromString(dateIns));
        firstIns.setCategoriePermis(CategoriePermis.valueOf(permisDemander));
        candidatJpaController.edit(candidat);
        inscriptionJpaController.edit(firstIns);
        ModalMessageDialog dialog = new ModalMessageDialog(null, true);
        dialog.showConfirmDialog("Candidat modifier avec succes");
        
        
    }
    
}
