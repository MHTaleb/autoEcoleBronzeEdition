/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectTools;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;


/**
 *
 * @author taleb
 */
public class ButtonEnablement implements DocumentListener {

        private final ButtonModel buttonModel;
        private final List<Document> documents = new ArrayList<>();

        public ButtonEnablement(ButtonModel buttonModel) {
            this.buttonModel = buttonModel;
        }

        public void addDocument(Document document) {
            document.addDocumentListener(this);
            this.documents.add(document);
            documentChanged();
        }

        public void documentChanged() {
            boolean buttonEnabled = true;
            
            for (Document document : documents) {
                if (document.getLength() == 0) {
                    buttonEnabled = false;
                    break;
                }
            }
            buttonModel.setEnabled(buttonEnabled);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            documentChanged();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            documentChanged();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            documentChanged();
        }
    }