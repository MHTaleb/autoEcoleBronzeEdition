/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.principale;

import views.candidat.FormCandidat;
import views.candidat.FormCondidatController;
import views.examen.ExamenForm;
import views.examen.FormExamenController;

/**
 *
 * @author taleb
 */
public class AcceuilController {

    void doShowCandidatForm() {
        FormCondidatController condidatController = new FormCondidatController();
        FormCandidat candidat = new FormCandidat(condidatController);
        candidat.setVisible(true);
    }

    void doShowExamenForm() {
        FormExamenController controller = new FormExamenController();
        ExamenForm examenForm = new ExamenForm(controller);
        examenForm.setVisible(true);
    }

    void doShowMoneyForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void showAddCandidatForm() {
        FormCondidatController formCondidatController = new FormCondidatController();
        formCondidatController.doshowAddCandidat();
    }

    void showAddExamen() {
        FormExamenController formExamenController = new FormExamenController();
        formExamenController.doshowAddExamen();
    }

}
