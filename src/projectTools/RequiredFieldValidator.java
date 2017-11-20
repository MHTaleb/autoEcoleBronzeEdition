/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectTools;

import java.util.Hashtable;
import javax.swing.text.JTextComponent;

/**
 *
 * @author taleb
         */
        public class RequiredFieldValidator {

            final private Hashtable<JTextComponent,String> components;
            private ModalMessageDialog dialog;

            public RequiredFieldValidator() {
                components = new Hashtable<>();
            }

            public void add(JTextComponent jTextComponent,String placeHolder) {
                components.put(jTextComponent,placeHolder);
            }

            public boolean validate() {

                for (final JTextComponent component : components.keySet()) {
                    String placeHolder = components.get(component);
                    final String currentText = component.getText().trim();
                   
                    if (currentText.isEmpty() || currentText.equals(placeHolder)) {
                        System.out.println("validation started");
                        dialog = new ModalMessageDialog(null, true);
                        dialog.showErrorDialog("Veuillez remplir le champs " +placeHolder+ " obligatoir");
                        component.requestFocus();
                        return false;
                    }

                }
                return true;
            }
            
        }

