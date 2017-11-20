/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.examen.editExam;

import views.examen.addExam.ExamAddFormController.RequestedListModel;



/**
 *
 * @author taleb
 */
public interface ExamEditCallback {
    public void setCount(int count);

    public void updateList(RequestedListModel model);
    
    public void refreshExaminateurs();
}
