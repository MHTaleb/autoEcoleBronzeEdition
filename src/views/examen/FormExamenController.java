/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.examen;

import main.ConnectionProvider;
import entities.CandidatEtatExamen;
import entities.CategorieExamens;
import entities.EtatExamen;
import entities.Examen;
import entities.dao.CandidatJpaController;
import entities.dao.ExamenJpaController;
import entities.dao.GenericEntityController;
import entities.dao.InscriptionJpaController;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.util.converter.DateStringConverter;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jpa.provider.EMF_Provider;
import net.sf.jasperreports.engine.JRException;
import printable.PrintJob;

import projectTools.EntityListModel;
import projectTools.ModalInputDialog;
import projectTools.ModalListDialog;
import projectTools.ModalMessageDialog;
import projectTools.ModalOptionDialog;
import views.examen.addExam.ExamAddFormController;
import views.examen.addExam.ExamAddFrom;
import views.examen.editExam.ExamEditFormController;
import views.examen.editExam.ExamenEditForm;

/**
 *
 * @author taleb
 */
public class FormExamenController {

    private final ExamenJpaController examenJpaController;
    private final CandidatJpaController candidatJpaController;
    private final InscriptionJpaController inscriptionJpaController;

    private final EntityListModel<entities.Examen> entityListModel;

    public FormExamenController() {
        this.examenJpaController = new ExamenJpaController(EMF_Provider.getCurrentSessionEMF());
        this.candidatJpaController = new CandidatJpaController(EMF_Provider.getCurrentSessionEMF());
        this.inscriptionJpaController = new InscriptionJpaController(EMF_Provider.getCurrentSessionEMF());
        entityListModel = new EntityListModel<>();
    }

    void doSearch(JTextField jTextField1, JList jList1) {
        String searchText = jTextField1.getText().toLowerCase();
        if (searchText.trim().isEmpty()) {
            jList1.setModel(entityListModel);
            return;
        }
        EntityListModel<Examen> examenListModel = new EntityListModel();
        Predicate<? super Examen> prdct;
        prdct = (Examen e) -> new DateStringConverter("dd-MM-yyyy").toString(e.getDateExamen()).contains(searchText)
                || e.getExaminateur().getExaminateurInfo().getNom().toLowerCase().contains(searchText)
                || e.getExaminateur().getExaminateurInfo().getPrenom().toLowerCase().contains(searchText);

        entityListModel.getEntities().stream().filter(prdct).collect(Collectors.toList()).stream().forEach(examenListModel::addElement);

        jList1.setModel(examenListModel);
    }

    public void doshowAddExamen() {
        ExamAddFrom addFrom = new ExamAddFrom(new ExamAddFormController());
        addFrom.setVisible(true);
    }

    void doshowEditExamen(JList jList1) {
        
        ExamenEditForm examEditFrom = new ExamenEditForm(new ExamEditFormController(selectedExamen));
        examEditFrom.setVisible(true);
    }

    void remove(JList jList1) {
        /**
         * ModalInputDialog est une class dans la quel j ai implémenté la logique d'un message qui s'affiche en
         * mode MODAL : un mode Modal est quand la fenetre courante est la seul fenetre clickable
         * 
         * comme tu le vois j'essaye de cliquer sur le login mais il est tjr en dessous car la nvl fenetre est modal
         * cela fait partie du jargon technique on di sois modal ou alwaysOnTop dans html cela se fait grace au Z-Index
         * 
         * alor j ai developper mon propre mechanisme qui me permet d afficher des message de validation , d erreur et d'avertissement
         * non c du pure java
         * 
         * je n ai pas encore corriger l erreur il affiche tjr le truc vert je vais le changer regarde
         * 
         * pour cela je vais a la classe ModalInputDialog qui contient toute la logique
         */
        ModalInputDialog mid = new ModalInputDialog(null, true); // cicle de vie  Etape 1 : instanciation 
        final String warningMessage = "هل تأكد حذف : " + 
                new DateStringConverter("dd-MM-YYYY").toString(selectedExamen.getDateExamen());
        // un appel a un comportement :: Method
        mid.showWarningDialog(warningMessage);

        if (mid.getUserAnswer() == ModalInputDialog.USER_NO) {
            return;
        }
        GenericEntityController<Examen> gecE = new GenericEntityController<>(Examen.class);
        GenericEntityController<CandidatEtatExamen> gecCEE = new GenericEntityController<>(CandidatEtatExamen.class);
        Hashtable<String, Object> param = new Hashtable<>();
        param.put(CandidatEtatExamen.REMOVE_BY_EXAM_ID, selectedExamen.getId());
        gecCEE.RemoveUpdateByQuery(CandidatEtatExamen.REMOVE_BY_EXAM, param);
        gecE.remove(selectedExamen.getId());

        ModalMessageDialog mmd = new ModalMessageDialog(null, true);
        mmd.showConfirmDialog("ثم الحذف بنجاح");
    }

    void getlistExamen(JList jList1) {

        entityListModel.clear();
        Predicate<Examen> predicate = p -> p.getDateExamen() != null;
        examenJpaController.findAll().stream().filter(predicate).forEach(entityListModel::addElement);
        jList1.setModel(entityListModel);
    }

    private Examen selectedExamen;

    void showSelectedExamInfo(JList jList1, JLabel jlNombreCandidat, JLabel jLExaminateur, JLabel jLDateExam) {
        try {

            EntityListModel<Examen> model = (EntityListModel<Examen>) jList1.getModel();
            selectedExamen = model.getElementAt(jList1.getSelectedIndex());

            Predicate<Examen> predicate = e -> e.getId().intValue() == selectedExamen.getId().intValue();
            int count = 0;
            GenericEntityController<CandidatEtatExamen> gecCandidatExamen = new GenericEntityController<>(CandidatEtatExamen.class);
            List<CandidatEtatExamen> cees = gecCandidatExamen.findAll();
            count = cees.stream().filter((cee) -> (cee.getExamen().getId().equals(selectedExamen.getId()))).map((_item) -> 1).reduce(count, Integer::sum);

            jlNombreCandidat.setText(count + " ممتحن");

            jLExaminateur.setText(selectedExamen.getExaminateur().getExaminateurInfo().toString());

            jLDateExam.setText(new DateStringConverter("dd-MM-yyyy").toString(selectedExamen.getDateExamen()));

        } catch (Exception e) {
            //Logger.writeExecption(e);
        }
    }

    void showSelectedExamenCircuitCandidats(JList jList1) {

        showExamenCandidats(CategorieExamens.Circuit);

    }

    private void showExamenCandidats(CategorieExamens categorieExamens) {
        GenericEntityController<CandidatEtatExamen> gec = new GenericEntityController(CandidatEtatExamen.class);
        Hashtable<String, Object> params = new Hashtable<>();
        params.put(CandidatEtatExamen.FIND_BY_EXAM_ID, selectedExamen.getId());
        params.put(CandidatEtatExamen.FIND_BY_EXAM_TYPE, categorieExamens);
        List<CandidatEtatExamen> findByQuery = gec.findByQuery(CandidatEtatExamen.FIND_BY_EXAM, params);

        EntityListModel<CandidatEtatExamen> listModel = new EntityListModel<>();

        findByQuery.stream().forEach((candidatEtatExamen) -> {
            listModel.addDistinct(candidatEtatExamen);
        });

        final ModalListDialog<CandidatEtatExamen> mld = new ModalListDialog(null, true);
        Predicate<? super CandidatEtatExamen> prdct = cee -> {
            String searchText = mld.getSearchText().trim();
            return cee.getCandidat().toString().contains(searchText);

        };
        mld.setSearchPredecate(prdct);
        MouseListener ml;
        ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() < 2) {
                    return;
                }
                CandidatEtatExamen selectedCandidatEtatExamen = mld.getSelectedElement();
                ModalOptionDialog mod = new ModalOptionDialog(null, true);

                mod.addOption("راسب", FAILED);
                mod.addOption("يمتحن", EXAMINATED);
                mod.addOption("ناجح", SUCCESS);
                switch (selectedCandidatEtatExamen.getEtatExamen()) {
                    case EnCours: {
                        mod.selectedOption(EXAMINATED);
                        break;
                    }
                    case Perdus: {
                        mod.selectedOption(FAILED);
                        break;
                    }
                    case Valider: {
                        mod.selectedOption(SUCCESS);
                        break;
                    }
                }

                mod.setVisible(true);

                if (mod.getUserAnswer() == ModalInputDialog.USER_YES) {
                    int selectedOptionValue = mod.getSelectedOptionValue();
                    switch (selectedOptionValue) {
                        case FAILED: {
                            CandidatEtatExamen find = gec.find(selectedCandidatEtatExamen.getId());
                            find.setEtatExamen(EtatExamen.Perdus);
                            gec.edit(find);
                            break;
                        }
                        case EXAMINATED: {
                            CandidatEtatExamen find = gec.find(selectedCandidatEtatExamen.getId());
                            find.setEtatExamen(EtatExamen.EnCours);
                            gec.edit(find);
                            break;
                        }
                        case SUCCESS: {
                            CandidatEtatExamen find = gec.find(selectedCandidatEtatExamen.getId());
                            find.setEtatExamen(EtatExamen.Valider);
                            gec.edit(find);
                            break;
                        }
                    }

                    ModalMessageDialog mmd = new ModalMessageDialog(null, true);
                    mmd.showConfirmDialog("تم تغير نتيجة الامتحان بنجاح");

                    List<CandidatEtatExamen> findByQuery = gec.findByQuery(CandidatEtatExamen.FIND_BY_EXAM, params);

                    EntityListModel<CandidatEtatExamen> listModel = new EntityListModel<>();

                    findByQuery.stream().forEach((candidatEtatExamen) -> {
                        listModel.addDistinct(candidatEtatExamen);
                    });
                    
                    mld.refreshList(listModel);

                }
            }
            private static final int FAILED = 0;
            private static final int EXAMINATED = 1;
            private static final int SUCCESS = 2;

        };

        mld.addJListMouseClickedEvent(ml);
        mld.showArabicModalList(listModel);
    }

    void showSelectedExamenCrenoCandidats(JList jList1) {
        showExamenCandidats(CategorieExamens.Creno);
    }

    void showSelectedExamenCodeCandidats(JList jList1) {
        showExamenCandidats(CategorieExamens.Code);

    }

    void print() {
        
        
        try {
            
            
            PrintJob pj = new PrintJob("LCI.jasper", ConnectionProvider.getConnection());
            
            Map<String, Object> params = new HashMap<>();
            params.put("idExamen", selectedExamen.getId().intValue());
            params.put("dateExamen", new DateStringConverter("dd/MM/yyyy").toString( selectedExamen.getDateExamen()));
    
            pj.showReport(params);
            
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, ex.getStackTrace());
            Logger.getLogger(FormExamenController.class.getName()).log(Level.SEVERE, null, ex);
        }catch(HeadlessException e){
            JOptionPane.showMessageDialog(null, e.getStackTrace());
            
        }
    }

    
}
