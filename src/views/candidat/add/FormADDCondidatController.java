/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.candidat.add;

import entities.Candidat;
import entities.CandidatInfo;
import entities.CategoriePermis;
import entities.Inscription;
import entities.dao.CandidatJpaController;
import entities.dao.ExamenJpaController;
import entities.dao.InscriptionJpaController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javafx.util.converter.DateStringConverter;
import javax.swing.JOptionPane;
import projectTools.ModalMessageDialog;

/**
 *
 * @author taleb
 */
public class FormADDCondidatController {

    private final CandidatJpaController candidatJpaController;
    private final InscriptionJpaController inscriptionJpaController;
 

    public FormADDCondidatController(CandidatJpaController candidatJpaController, InscriptionJpaController inscriptionJpaController, ExamenJpaController examenJpaController) {
        this.candidatJpaController = candidatJpaController;
        this.inscriptionJpaController = inscriptionJpaController;
    
    }

    void addCandidat(String nom, String prenom, String nomPere, String nomMere, String adresse, String numeroTelephone, String dateNaissance, String lieuNaissance, String nationalite, String dateIns, String permisDemander) {
        boolean candidatJpaInitialized = Objects.nonNull(candidatJpaController);
        
        if (candidatJpaInitialized) {
         
            DateStringConverter converter = new DateStringConverter("dd-MM-yyyy");
            Date fromString = null;
            try {
                fromString = converter.fromString(dateNaissance);
                
            } catch (Exception e) {
                ModalMessageDialog modalMessageDialog = new ModalMessageDialog(null, true);
                modalMessageDialog.showErrorDialog("la date "+dateNaissance+" n'est pas une date valide veuillez la corriger");
                return;
            }
            Candidat candidat = candidatJpaController.findCandidatEntity(nom, prenom, fromString);
            try {
                if (candidat != null) {
                    if (candidat.getCandidatInfo() != null) {
                        System.out.println("Nom :" + candidat.getCandidatInfo().getNom());
                        ModalMessageDialog errorDialog = new ModalMessageDialog(null, true);
                        errorDialog.showErrorDialog("Cette utilisateur exist deja ");
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            candidat = new Candidat();

            CandidatInfo candidatInfo = new CandidatInfo();

            candidatInfo.setNomMere(nomMere);
            candidatInfo.setNomPere(nomPere);
            candidatInfo.setDateNaissance(fromString);
            candidatInfo.setLieuNaissance(lieuNaissance);
            candidatInfo.setNom(nom);
            candidatInfo.setPrenom(prenom);
            candidatInfo.setNumeroTelephone(numeroTelephone);
            candidatInfo.setAdresse(adresse);
            candidatInfo.setNationalite(nationalite);

            candidat.setCandidatInfo(candidatInfo);

            List<Inscription> inscriptions = new ArrayList<>();

            Inscription inscription = new Inscription();
            inscription.setCategoriePermis(CategoriePermis.valueOf(permisDemander));
            try{
            inscription.setDateInscription(converter.fromString(dateIns));
    
            } catch (Exception e) {
                ModalMessageDialog modalMessageDialog = new ModalMessageDialog(null, true);
                modalMessageDialog.showErrorDialog("la date "+dateIns+" n'est pas une date valide veuillez la corriger");
                return;
            }
          

            inscriptionJpaController.create(inscription);

            inscriptions.add(inscription);

            candidat.setInscriptions(inscriptions);

            candidatJpaController.create(candidat);
            
            ModalMessageDialog dialog = new ModalMessageDialog(null, true);
            dialog.showConfirmDialog("Candidat ajouté avec succes");
        }else{
            JOptionPane.showMessageDialog(null, "error num 12459686545 ");
        }
    }

}
