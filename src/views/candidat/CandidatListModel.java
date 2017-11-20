/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.candidat;

import entities.Candidat;
import entities.CandidatInfo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author taleb
 */
public class CandidatListModel extends DefaultListModel{

    private static final long serialVersionUID = -1535064186564842428L;
    private final List<Candidat> candidats;

    public List<Candidat> getCandidats() {
        return candidats;
    }
    
    public CandidatListModel() {
        this.candidats = new ArrayList<>();
    }

    @Override
    public void addElement(Object c) {
        candidats.add((Candidat) c);
        final CandidatInfo candidatInfo = ((Candidat)c).getCandidatInfo();
        super.addElement(candidatInfo.getNom()+" "+candidatInfo.getPrenom());
    }

    @Override
    public Object getElementAt(int i) {
        return candidats.get(i); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        candidats.clear();
        super.clear(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(int i) {
        return candidats.get(i); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
