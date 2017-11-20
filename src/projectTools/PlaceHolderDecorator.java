/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectTools;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author taleb
 */
public class PlaceHolderDecorator {

    
    
    public static void decorate(JTextField field, String placeHoldingText) {
        field.setForeground(Color.GRAY);
        field.addFocusListener(new FocusListener() {
            @Override
            public synchronized void focusGained(FocusEvent e) {
                if (field.getText().equals(placeHoldingText)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            @Override
            public synchronized void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeHoldingText);
                }
            }
        });
    }
    public static void decorate(JTextField field, String placeHoldingText,Color placeHolderColor,Color textColor) {
        field.setForeground(placeHolderColor);
        field.addFocusListener(new FocusListener() {
            @Override
            public synchronized void focusGained(FocusEvent e) {
                if (field.getText().equals(placeHoldingText)) {
                    field.setText("");
                    field.setForeground(textColor);
                }
            }

            @Override
            public synchronized void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(placeHolderColor);
                    field.setText(placeHoldingText);
                }
            }
        });
    }
    
    final public static int DATE_FIELD_P = 10;
    final public static int PHONE_FIELD_P = 14;
    
    public static void decorate(JFormattedTextField field,String placeHoldingText,Color placeHolderColor,Color textColor,int checkFieldProperty){
        field.setForeground(placeHolderColor);
        final JFormattedTextField.AbstractFormatterFactory formatterFactory = field.getFormatterFactory();
        field.setFormatterFactory(null);
        field.setText(placeHoldingText);
        field.addFocusListener(new FocusListener() {
            @Override
            public synchronized void focusGained(FocusEvent e) {
                if (field.getText().equals(placeHoldingText)) {
                    field.setText("");
                    field.setForeground(textColor);
                    field.setFormatterFactory(formatterFactory);
                }
            }

            @Override
            public synchronized void focusLost(FocusEvent e) {
                if (field.getText().trim().length()!=checkFieldProperty ) {
                    field.setFormatterFactory(null);
                    field.setText("");
                }
                if (field.getText().isEmpty()) {
                    field.setFormatterFactory(null);
                    field.setForeground(placeHolderColor);
                    field.setText(placeHoldingText);
                }
            }
        });
    }
}
