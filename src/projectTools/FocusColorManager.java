/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectTools;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 *
 * @author taleb
 *
 * this component is designed for jpanel jtextfields jbutton and every
 * jcomponent that uses a background property
 *
 */
public class FocusColorManager {

    private final List<JComponent> components;

    public FocusColorManager() {

        components = new ArrayList<>();
    }

    public void addComponent(JComponent component) {
        components.add(component);
    }

    public void setupFocusColorEvent(final Color c) {
        components.forEach((component) -> {
            final Color UNFOCUSED_COLOR = component.getBackground();
            component.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent me) {
                    component.setBackground(c);
                    super.mouseEntered(me); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent me) {
                    component.setBackground(UNFOCUSED_COLOR);
                    super.mouseExited(me); //To change body of generated methods, choose Tools | Templates.
                }
            });
        });
    }

}
