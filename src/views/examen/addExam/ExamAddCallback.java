/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.examen.addExam;

import views.examen.addExam.ExamAddFormController.RequestedListModel;

/**
 *
 * @author taleb
 */
public interface ExamAddCallback {
    public void setCount(int count);

    public void updateList(RequestedListModel model);
    
    public void refreshExaminateurs();
}
