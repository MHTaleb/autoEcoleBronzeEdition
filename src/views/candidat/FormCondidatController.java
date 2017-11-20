/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.candidat;

import entities.Candidat;
import entities.CandidatEtatExamen;
import entities.CandidatInfo;
import entities.CategorieExamens;
import entities.Inscription;
import entities.dao.CandidatJpaController;
import entities.dao.ExamenJpaController;
import entities.dao.GenericEntityController;
import entities.dao.InscriptionJpaController;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import javafx.util.converter.DateStringConverter;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import jpa.provider.EMF_Provider;
import projectTools.EntityListModel;
import projectTools.ModalInputDialog;
import projectTools.PhoneNumberStringConverter;
import views.candidat.add.FormADDCondidatController;
import views.candidat.add.FormAddCandidat;
import views.candidat.edit.FormEditCandidat;
import views.candidat.edit.FormEditCondidatController;

/**
 *
 * @author taleb
 */
public class FormCondidatController {

    private final CandidatJpaController candidatJpaController;
    private final ExamenJpaController examenJpaController;
    private final InscriptionJpaController inscriptionJpaController;

    private EntityListModel<Candidat> listCandidat;
    
    public FormCondidatController() {
        examenJpaController = new ExamenJpaController(EMF_Provider.getCurrentSessionEMF());
        candidatJpaController = new CandidatJpaController(EMF_Provider.getCurrentSessionEMF());
        inscriptionJpaController = new InscriptionJpaController(EMF_Provider.getCurrentSessionEMF());
        // num : 0773827495
    }

    public void doshowAddCandidat() {
        FormADDCondidatController formADDCondidatController;

        formADDCondidatController = new FormADDCondidatController(candidatJpaController, inscriptionJpaController, examenJpaController);

        FormAddCandidat addCandidat = new FormAddCandidat(formADDCondidatController);
        addCandidat.setVisible(true);

    }
    public void doshowEditCandidat(JList list) {
        FormEditCondidatController formEditCondidatController;

        formEditCondidatController = new FormEditCondidatController();
        int selectedCandidatIndex = list.getSelectedIndex();
        EntityListModel<Candidat> model = (EntityListModel<Candidat>) list.getModel();
        final Candidat candidat = model.getEntities().get(selectedCandidatIndex);
        Long id = candidat.getId();
        FormEditCandidat editCandidat = new FormEditCandidat(formEditCondidatController, id);
        String prenom = candidat.getCandidatInfo().getPrenom();
        String nom = candidat.getCandidatInfo().getNom();
        String nomMere = candidat.getCandidatInfo().getNomMere();
        String nomPere = candidat.getCandidatInfo().getNomPere();
        String adress = candidat.getCandidatInfo().getAdresse();
        String lieuNaissance = candidat.getCandidatInfo().getLieuNaissance();
        String nationalite = candidat.getCandidatInfo().getNationalite();
        
        DateStringConverter dsc = new DateStringConverter("dd-MM-yyyy");
        String dateDepot = dsc.toString(candidat.getInscriptions().get(0).getDateInscription());
        
        String dateNaissanc = dsc.toString(candidat.getCandidatInfo().getDateNaissance());
        PhoneNumberStringConverter pnsc = new PhoneNumberStringConverter();
        String telephone = pnsc.fromString(candidat.getCandidatInfo().getNumeroTelephone());
        
        editCandidat.setEditData(nomMere, nomPere, adress, lieuNaissance, nationalite, dateDepot, dateNaissanc, telephone,nom, prenom);
        
        int categoriePermis = candidat.getInscriptions().get(0).getCategoriePermis().getOrdinal();
        System.out.println("categorie permis "+categoriePermis);
        editCandidat.setSelectedPermis( categoriePermis );
        
        editCandidat.setVisible(true);

    }

    public void getListCandidat(JList jList) {

        listCandidat = new EntityListModel<>();
        candidatJpaController.findAll().forEach((candidat) -> {
            listCandidat.addElement(candidat);
        });
        jList.setModel(listCandidat);
        
    }

    public boolean remove(JList jList1) {

        final int selectedIndex = jList1.getSelectedIndex();
        if (selectedIndex == -1) {
            return false;
        }
        Candidat candidat =  ((EntityListModel<Candidat>) (jList1.getModel())).get(selectedIndex);
        ModalInputDialog dialog = new ModalInputDialog(null, true);
        dialog.showConfirmDialog("Veuillez confirmer la supression de " + candidat.getCandidatInfo().getNom()
                + " " + candidat.getCandidatInfo().getPrenom());
        int userAnswer = dialog.getUserAnswer();
        if (userAnswer == ModalInputDialog.USER_YES) {
            List<Inscription> inscriptions = candidat.getInscriptions();
            inscriptions.forEach((inscription) -> {
                GenericEntityController<CandidatEtatExamen> controllerCandidatEtatExamen = new GenericEntityController<>(CandidatEtatExamen.class);
                Predicate<? super CandidatEtatExamen> prdct = cee -> Objects.equals(candidat.getId(), cee.getCandidat().getId());
                controllerCandidatEtatExamen.findAll().stream().filter(prdct).forEach(cnsmr ->{
                    controllerCandidatEtatExamen.remove(cnsmr.getId());
                });
                inscriptionJpaController.remove(inscription.getId());
            });
            // candidat.getInscriptions().stream().forEach((inscription) -> inscription.getExamens().stream().forEach(examenJpaController::remove));
            // candidat.getInscriptions().stream().forEach(inscriptionJpaController::remove);
            candidatJpaController.remove(candidat.getId());
        }
        return true;
    }

    public void showSelectedCandidat(final JList jList1, final int selectedIndex, final JLabel jlbAdresseValue, final JLabel jlbNomValue, final JLabel jlbdtNaissanceValue, final JLabel jlbNomPereValue, final JLabel jlbNomMereValue,
            final JLabel jlbNumTelValue, final JLabel jlbExamenValue, final JLabel jlbPermisValue, final JLabel jlbdtInscValue, final JLabel jlbLieuNaissanceValue, final JLabel jlbNationaliteValue3) {

        Candidat candidat =  ((EntityListModel<Candidat>) (jList1.getModel())).get(selectedIndex);

        final List<Inscription> inscriptions = candidat.getInscriptions();

        try {
            candidat.getInscriptions().get(0);
        } catch (Exception e) {
            System.out.println("error");
        }

        jlbAdresseValue.setText(candidat.getCandidatInfo().getAdresse());
        jlbNomValue.setText(candidat.getCandidatInfo().getNom() + " " + candidat.getCandidatInfo().getPrenom());

        jlbNomPereValue.setText(candidat.getCandidatInfo().getNomPere());
        jlbNomMereValue.setText(candidat.getCandidatInfo().getNomMere());

        String dtNaissance = new DateStringConverter("dd-MM-yyyy").toString(candidat.getCandidatInfo().getDateNaissance());
        jlbdtNaissanceValue.setText(dtNaissance);

        PhoneNumberStringConverter phoneNumberStringConverter = new PhoneNumberStringConverter();

        jlbNumTelValue.setText(phoneNumberStringConverter.fromString(candidat.getCandidatInfo().getNumeroTelephone()));

        String permisDemander = "non Inscrit / Dossier Incomplet";
        String dtInsc = "non Inscrit / Dossier Incomplet";
        if (inscriptions != null) {

            if (inscriptions.size() > 0) {
                Inscription inscription = inscriptions.get(0);
                Date dateInscription = inscription.getDateInscription();
                if (dateInscription != null) {
                    dtInsc = new DateStringConverter("dd-MM-yyyy").toString(dateInscription);
                }
                permisDemander = inscription.getCategoriePermis().name();

                GenericEntityController<CandidatEtatExamen> ceeController = new GenericEntityController(CandidatEtatExamen.class);
                Predicate<? super CandidatEtatExamen> prdct = cee -> cee.getCandidat().equals(candidat);
                
                long count;
                Predicate<? super CandidatEtatExamen> prdctCircuit;
                prdctCircuit = cee -> cee.getTypeExamen() == CategorieExamens.Circuit;
                count = ceeController.findAll().stream().filter(prdct).filter(prdctCircuit).count();
                if (count>0) {
                    jlbExamenValue.setText(CategorieExamens.Circuit.toString());
                }else{
                    prdctCircuit = cee -> cee.getTypeExamen() == CategorieExamens.Creno;
                    count = ceeController.findAll().stream().filter(prdct).filter(prdctCircuit).count();
                    if (count>0) {
                        
                        jlbExamenValue.setText(CategorieExamens.Creno.toString());
                    }else{
                        
                        jlbExamenValue.setText(CategorieExamens.Code.toString());
                    }
                    
                }
                    
            }
        }
        jlbPermisValue.setText(permisDemander);
        jlbdtInscValue.setText(dtInsc);
        jlbLieuNaissanceValue.setText(candidat.getCandidatInfo().getLieuNaissance());
        jlbNationaliteValue3.setText(candidat.getCandidatInfo().getNationalite());
    }

    void doSearch(JTextField jTextField1, JList<String> jList1) {
        String searchText = jTextField1.getText().toLowerCase();
        if (searchText.trim().isEmpty()) {
            jList1.setModel(listCandidat);
            return;
        }
        EntityListModel<Candidat> candidatListModel = new EntityListModel();
        for (int i = 0; i < listCandidat.size(); i++) {
            Candidat candidat = listCandidat.get(i);
            final CandidatInfo candidatInfo = candidat.getCandidatInfo();
            if (candidatInfo.getNom().toLowerCase().contains(searchText)) {
                candidatListModel.addElement(candidat);
            } 
            else
                if (candidatInfo.getPrenom().toLowerCase().contains(searchText)) {
                candidatListModel.addElement(candidat);
            } 
            else
                if (candidatInfo.getNumeroTelephone().toLowerCase().contains(searchText)) {
                candidatListModel.addElement(candidat);
            } 
            else {
                final Date dateNaissance = candidatInfo.getDateNaissance();
                DateStringConverter converter = new DateStringConverter("dd-MM-yyyy");
                String dtNaiss = converter.toString(dateNaissance);
                if (dtNaiss.toLowerCase().contains(searchText)) {
                    candidatListModel.addElement(candidat);
                } 
            }

        }
        jList1.setModel(candidatListModel);
    }

}
