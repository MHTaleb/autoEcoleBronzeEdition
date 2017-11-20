/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectTools;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author taleb
 */
public class EntityListModelGroup {
    private List<EntityListModel> elms;
    private int index ;

    public EntityListModelGroup() {
        elms = new ArrayList<>();
    }

    public void addList(EntityListModel elm){
        elms.add(elm);
        refresh();
    }
    
    public void remove(EntityListModel elm){
        elm.setNonVerifiable();
        elms.remove(elm);
        index = 0 ;
        refresh();
    }

    private void refresh() {
        index = 0;
        elms.stream().forEach((EntityListModel elm1) -> {
            elm1.setVerifiable(index);
            elm1.setNodes(elms);
            index++;
        });
    }
    
    public void remove(int elmIndex){
        elms.get(elmIndex).setNonVerifiable();
        elms.remove(elmIndex);
        index = 0 ;
        refresh();
    }
    
    
}
