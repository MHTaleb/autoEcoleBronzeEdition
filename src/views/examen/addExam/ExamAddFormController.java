/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.examen.addExam;

import entities.Candidat;
import entities.CandidatEtatExamen;
import entities.CandidatInfo;
import entities.CategorieExamens;
import entities.EtatExamen;
import entities.Examen;
import entities.Examinateur;
import entities.dao.CandidatJpaController;
import entities.dao.GenericEntityController;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javafx.util.converter.DateStringConverter;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import jpa.provider.EMF_Provider;
import projectTools.EntityListModel;
import projectTools.EntityListModelGroup;
import projectTools.ModalMessageDialog;

/**
 *
 * @author taleb
 */
public class ExamAddFormController {

    public static final int ADD = 0, REMOVE = 1;

    void moveSelectedElement(JList jList1, int operation) {
        EntityListModel<Candidat> model = (EntityListModel<Candidat>) jList1.getModel();
        Candidat elementAt = model.getElementAt(jList1.getSelectedIndex());
        System.out.println("selected " + elementAt);
        switch (operation) {
            case ADD: {
                boolean addDistinct = currentSearchableList.addDistinct(elementAt);
                if (addDistinct) {
                    switch (currentListModel) {
                        case CODE:
                            listModelCode.addDistinct(elementAt);
                            break;
                        case CIRCUIT:
                            listModelCircuit.addDistinct(elementAt);
                            break;
                        case CRENO:
                            listModelCreno.addDistinct(elementAt);
                            break;
                    }
                }
                break;
            }
            case REMOVE: {
                currentSearchableList.removeElement(elementAt);
                switch (currentListModel) {
                    case CODE:
                        listModelCode.removeElement(elementAt);
                        break;
                    case CIRCUIT:
                        listModelCircuit.removeElement(elementAt);
                        break;
                    case CRENO:
                        listModelCreno.removeElement(elementAt);
                        break;
                }
                break;
            }
        }
        //---------------------- end  notify observer 
        observer.setCount(getCount());
        observer.updateList(currentListModel);
    }

    public void quickAddExaminateur(String value) {
        if (value.contains("-")) {
            String[] split = value.split("-");
            Examinateur e = new Examinateur();
            CandidatInfo examinateurInfo = new CandidatInfo();
            examinateurInfo.setNom(split[0].trim());
            examinateurInfo.setPrenom(split[1].trim());
            e.setExaminateurInfo(examinateurInfo);

            GenericEntityController gce = new GenericEntityController(Examinateur.class);
            gce.create(e);
            observer.refreshExaminateurs();
            return;
        }
        ModalMessageDialog dialog = new ModalMessageDialog(null, true);
        dialog.setHorizontalLeading(JLabel.RIGHT);
        dialog.showErrorDialog("يرجى إدخال اللقب ثم رمز - ثم الاسم \n مثال: طالب - محمد حسين");
    }

    void addExamen(Long selectedExamintorId, String dtExamen) {

        final GenericEntityController<CandidatEtatExamen> candidatExamenGenericController = new GenericEntityController<>(CandidatEtatExamen.class);
        GenericEntityController<Examen> examenGenericController = new GenericEntityController<>(Examen.class);
        final Examen exam = new Examen();
        exam.setDateExamen(new DateStringConverter("dd-MM-yyyy").fromString(dtExamen));
        Examinateur examinateur = new GenericEntityController<>(Examinateur.class).find(selectedExamintorId);
        exam.setExaminateur(examinateur);

        examenGenericController.create(exam);
        listModelCode.getEntities().stream().forEach(cnd->{
            CandidatEtatExamen candidatEtatExamen = new CandidatEtatExamen();
            
            candidatEtatExamen.setCandidat(cnd);
            candidatEtatExamen.setExamen(exam);
            candidatEtatExamen.setEtatExamen(EtatExamen.EnCours);
            candidatEtatExamen.setTypeExamen(CategorieExamens.Code);
            
            candidatExamenGenericController.create(candidatEtatExamen);
           // candidatExamenGenericController.find(candidatEtatExamen.getId());
        });
        listModelCreno.getEntities().stream().forEach(cnd->{
            CandidatEtatExamen candidatEtatExamen = new CandidatEtatExamen();
            
            candidatEtatExamen.setCandidat(cnd);
            candidatEtatExamen.setExamen(exam);
            candidatEtatExamen.setEtatExamen(EtatExamen.EnCours);
            candidatEtatExamen.setTypeExamen(CategorieExamens.Creno);
            
            candidatExamenGenericController.create(candidatEtatExamen);
           // candidatExamenGenericController.find(candidatEtatExamen.getId());
        });
        listModelCircuit.getEntities().stream().forEach(cnd->{
            CandidatEtatExamen candidatEtatExamen = new CandidatEtatExamen();
            
            candidatEtatExamen.setCandidat(cnd);
            candidatEtatExamen.setExamen(exam);
            candidatEtatExamen.setEtatExamen(EtatExamen.EnCours);
            candidatEtatExamen.setTypeExamen(CategorieExamens.Circuit);
            
            candidatExamenGenericController.create(candidatEtatExamen);
           // candidatExamenGenericController.find(candidatEtatExamen.getId());
        });
        
        ModalMessageDialog mmd = new ModalMessageDialog(null, true);
        mmd.showConfirmDialog("عملية الاضافة ثمت بنجاح");
        
    }

    public enum RequestedListModel {
        ALL, CODE, CRENO, CIRCUIT
    }
    private RequestedListModel currentListModel;
    private final HashMap<Long, String> allExaminateurs;
    private EntityListModel<Candidat> listModelAllCandida, listModelCode, listModelCreno, listModelCircuit;
    private final HashMap<RequestedListModel, EntityListModel> hmModelToList;
    private EntityListModelGroup modelGroup;

    private void refresh() {
        allExaminateurs.clear();
        hmModelToList.clear();
        listModelAllCandida = new EntityListModel<>();
        listModelCircuit = new EntityListModel<>();
        listModelCode = new EntityListModel<>();
        listModelCreno = new EntityListModel<>();
        modelGroup = new EntityListModelGroup();

        modelGroup.addList(listModelCode);
        modelGroup.addList(listModelCircuit);
        modelGroup.addList(listModelCreno);

        
        
        CandidatJpaController candidatJpaController = new CandidatJpaController(EMF_Provider.getCurrentSessionEMF());
        Predicate<? super Candidat> prdct;
        prdct = (Candidat c) -> {
            GenericEntityController<CandidatEtatExamen> genericCandidatEtatExamen = new GenericEntityController<>(CandidatEtatExamen.class);
            List<CandidatEtatExamen> candidatEtatExamens = genericCandidatEtatExamen.findAll();
            boolean result = true;
            for (CandidatEtatExamen candidatEtatExamen : candidatEtatExamens) {
                if (candidatEtatExamen.getCandidat().getId().equals(c.getId())) {
                    result = false ;
                    EtatExamen etatExamen = candidatEtatExamen.getEtatExamen();
                    CategorieExamens typeExamen = candidatEtatExamen.getTypeExamen();
                    if (typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.Valider ) {
                        return false;
                    }else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.EnCours){
                        return false ;
                    }
                    
                }
            }

            return result;
//                
        };

        candidatJpaController.findAll().stream().filter(prdct).collect(Collectors.toList()).stream().forEach(listModelAllCandida::addElement);

        Predicate<? super Candidat> codePredicat = c -> {

            GenericEntityController<CandidatEtatExamen> genericCandidatEtatExamen = new GenericEntityController<>(CandidatEtatExamen.class);
            List<CandidatEtatExamen> candidatEtatExamens = genericCandidatEtatExamen.findAll();
            boolean result = true;
            for (CandidatEtatExamen candidatEtatExamen : candidatEtatExamens) {
                if (candidatEtatExamen.getCandidat().getId().equals(c.getId())) {
                    EtatExamen etatExamen = candidatEtatExamen.getEtatExamen();
                    CategorieExamens typeExamen = candidatEtatExamen.getTypeExamen();
                    
                    if (typeExamen == CategorieExamens.Code && etatExamen == EtatExamen.EnCours) {
                        return false;
                    } 
                    else if(typeExamen == CategorieExamens.Code && etatExamen == EtatExamen.Valider){
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Creno && etatExamen == EtatExamen.Valider){
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Creno && etatExamen == EtatExamen.EnCours){
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Creno && etatExamen == EtatExamen.Perdus){
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.Valider){
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.Perdus){
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.EnCours){
                        return false;
                    }
                }
            }
            return result;

        };

        candidatJpaController.findAll().stream().filter(codePredicat).collect(Collectors.toList()).stream().forEach(listModelCode::addDistinct);
        Predicate<? super Candidat> crenoPredicat;
        crenoPredicat = c -> {
            try {
                GenericEntityController<CandidatEtatExamen> genericCandidatEtatExamen = new GenericEntityController<>(CandidatEtatExamen.class);
                List<CandidatEtatExamen> candidatEtatExamens = genericCandidatEtatExamen.findAll();
                boolean codeFound = false;
                boolean result = false;
                for (CandidatEtatExamen candidatEtatExamen : candidatEtatExamens) {
                    if (candidatEtatExamen.getCandidat().getId().equals(c.getId())) {
                        EtatExamen etatExamen = candidatEtatExamen.getEtatExamen();
                        CategorieExamens typeExamen = candidatEtatExamen.getTypeExamen();
                        
                        if (typeExamen == CategorieExamens.Code && etatExamen == EtatExamen.EnCours) {
                            return false;
                        }
                        else if(typeExamen == CategorieExamens.Creno && etatExamen == EtatExamen.Valider){
                            return false;
                        }
                        else if(typeExamen == CategorieExamens.Creno && etatExamen == EtatExamen.EnCours){
                            return false;
                        }
                        else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.Valider){
                            return false;
                        }
                        else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.Perdus){
                            return false;
                        }
                        else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.EnCours){
                            return false;
                        }
                        
                        if((typeExamen == CategorieExamens.Code && etatExamen == EtatExamen.Valider)||
                                (typeExamen == CategorieExamens.Creno && etatExamen == EtatExamen.Perdus)){
                            result = true;
                        }
                    }
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        };

        candidatJpaController.findAll().stream().filter(crenoPredicat).collect(Collectors.toList()).stream().forEach(listModelCreno::addDistinct);

        Predicate<? super Candidat> circuitPredicat = c -> {
            try {
                GenericEntityController<CandidatEtatExamen> genericCandidatEtatExamen = new GenericEntityController<>(CandidatEtatExamen.class);
                List<CandidatEtatExamen> candidatEtatExamens = genericCandidatEtatExamen.findAll();
                boolean result = false;
                for (CandidatEtatExamen candidatEtatExamen : candidatEtatExamens) {
                    if (candidatEtatExamen.getCandidat().getId().equals(c.getId())) {
                        EtatExamen etatExamen = candidatEtatExamen.getEtatExamen();
                        CategorieExamens typeExamen = candidatEtatExamen.getTypeExamen();
                        
                    if (typeExamen == CategorieExamens.Code && etatExamen == EtatExamen.EnCours) {
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Creno && etatExamen == EtatExamen.EnCours){
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.Valider){
                        return false;
                    }
                    else if(typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.EnCours){
                        return false;
                    }
                 
                        if((typeExamen == CategorieExamens.Creno && etatExamen == EtatExamen.Valider)||
                                (typeExamen == CategorieExamens.Circuit && etatExamen == EtatExamen.Perdus)) {
                            result = true;
                        }
                    
                    }
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        };

        candidatJpaController.findAll().stream().filter(circuitPredicat).collect(Collectors.toList()).stream().forEach(listModelCircuit::addDistinct);

        refreshExaminateurs();
        
        currentSearchableList = listModelCode;

    }

    private void refreshExaminateurs() {
        GenericEntityController examinateurGenericJPAController = new GenericEntityController(Examinateur.class);
        List<Examinateur> examinateurs = examinateurGenericJPAController.findAll();
        examinateurs.stream().forEach(examinteur -> {
            allExaminateurs.put(examinteur.getId(), examinteur.getExaminateurInfo().toString());
        });
    }

    public ExamAddFormController() {
        hmModelToList = new HashMap<>();
        allExaminateurs = new HashMap<>();
        // get all list values from database
        refresh();
        //setup all datasources
        hmModelToList.put(RequestedListModel.ALL, listModelAllCandida);
        hmModelToList.put(RequestedListModel.CIRCUIT, listModelCircuit);
        hmModelToList.put(RequestedListModel.CODE, listModelCode);
        hmModelToList.put(RequestedListModel.CRENO, listModelCreno);
    }

    private int getCount() {
        return listModelCircuit.getSize() + listModelCode.getSize() + listModelCreno.getSize();
    }

    void doSearchForCandidat(JTextField jTextField2, JList<String> jList2) {
        // --------------  do search using global List
        String searchText = jTextField2.getText().toLowerCase();
        if (searchText.trim().isEmpty()) {
            jList2.setModel(listModelAllCandida);
            return;
        }

        EntityListModel<Candidat> candidatListModel = new EntityListModel();
        Predicate<? super Candidat> prdct;
        prdct = cnd
                -> cnd.getCandidatInfo().getNom().toLowerCase().contains(searchText)
                || cnd.getCandidatInfo().getPrenom().toLowerCase().contains(searchText)
                || cnd.getCandidatInfo().getNumeroTelephone().toLowerCase().contains(searchText);
        listModelAllCandida.getEntities().stream().filter(prdct).collect(Collectors.toList()).stream().forEach(candidatListModel::addElement);

        jList2.setModel(candidatListModel);
    }

    void showListCandidat(JList<String> jList2, RequestedListModel listModel) {
        jList2.setModel(hmModelToList.get(listModel));
        currentSearchableList = hmModelToList.get(listModel);
        currentListModel = listModel;
    }

    HashMap<Long, String> refreshAllExaminateurs() {
        refreshExaminateurs();
        return allExaminateurs;
    }

    HashMap<Long, String> getAllExaminateurs() {
        return allExaminateurs;
    }

    void showAllCandidat(JList<String> jList1) {
        jList1.setModel(hmModelToList.get(RequestedListModel.ALL));
    }

    ExamAddCallback observer;

    void registerListener(ExamAddCallback aThis) {
        observer = aThis;
    }

    EntityListModel<Candidat> currentSearchableList;

    void doSearchForCandidatExamin(JTextField jTextField2, JList<String> jList2) {
        // use the currentSearchableList;
        String searchText = jTextField2.getText().toLowerCase();
        if (searchText.trim().isEmpty()) {
            jList2.setModel(currentSearchableList);
            return;
        }

        EntityListModel<Candidat> candidatListModel = new EntityListModel();
        Predicate<? super Candidat> prdct;
        prdct = cnd
                -> cnd.getCandidatInfo().getNom().toLowerCase().contains(searchText)
                || cnd.getCandidatInfo().getPrenom().toLowerCase().contains(searchText)
                || cnd.getCandidatInfo().getNumeroTelephone().toLowerCase().contains(searchText);
        currentSearchableList.getEntities().stream().filter(prdct).collect(Collectors.toList()).stream().forEach(candidatListModel::addElement);

        jList2.setModel(candidatListModel);
    }

}
