/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectTools;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 *
 * @author taleb
 * @param <T> the entity from the JPA Diagram
 *
 */
public class EntityListModel<T> extends DefaultListModel {

    private static final long serialVersionUID = -8497331289674098925L;
    private final ArrayList<T> entities;

    public ArrayList<T> getEntities() {
        return entities;
    }

    @Override
    public boolean removeElement(Object o) {
        int indexOf = entities.indexOf((T) o);
        if (indexOf != -1) {
            removeElementAt(indexOf);
            entities.remove(indexOf);
            return true;
        }
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * addDistinct is used to add one occurence of the same entity object, the
     * entity class must implement the equals methode in ordre to work properly
     *
     * @param object the entity to add to this ListModel
     * @return true if there is no previous entity false if not added
     */
    public boolean addDistinct(Object object) {

       
        if (!entities.stream().noneMatch(( T entity) -> (entity.equals(object)))) {
            return false;
        }
        boolean ignore;
        if (verifiable) {
            ignore = verify((T) object);
            if (!ignore) {
                ModalMessageDialog dialog = new ModalMessageDialog(null, true);
                dialog.showErrorDialog("Vous avez deja ajouté l'élément à l'une des listes");
                return false;
            }
        }
        this.addElement(object);
        return true;
    }

    public EntityListModel() {
        entities = new ArrayList<>();
    }

    @Override
    public void addElement(Object c) {
        entities.add((T) c);
        super.addElement((T) c.toString());
    }

    @Override
    public T getElementAt(int i) {
        return entities.get(i); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        entities.clear();
        super.clear(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T get(int i) {
        return entities.get(i); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * this is an internal method that should never be used on your code, this
     * is used on the addDistinct method which will check the exitense of the
     * object entity in the listModelGroup
     *
     * @param object the object to be verified in the list model group
     * @return
     */
    private boolean verify(T object) {
        for (int i = 0; i < currentListNodeIndex; i++) {
            if (elmsGroup.get(i).getEntities().contains(object)) {
                return false;
            }
        }
        for (int i = currentListNodeIndex + 1; i < elmsGroup.size(); i++) {
            if (elmsGroup.get(i).getEntities().contains(object)) {
                return false;
            }
        }

        return true;
    }

    /**
     * to unregister this list from it current ListModelGroup so the addDistinct
     * will check only in this list not the others
     *
     */
    public void setNonVerifiable() {
        verifiable = false;
        currentListNodeIndex = -1;
        elmsGroup = null;
    }

    private boolean verifiable = false;
    private int currentListNodeIndex;

    public void setVerifiable(int currentListNodeIndex) {
        verifiable = true;
        this.currentListNodeIndex = currentListNodeIndex;
    }

    private List<EntityListModel> elmsGroup;

    public void setNodes(List<EntityListModel> elms) {
        this.elmsGroup = elms;
    }

}
