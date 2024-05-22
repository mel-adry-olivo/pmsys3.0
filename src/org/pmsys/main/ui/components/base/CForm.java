package org.pmsys.main.ui.components.base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;


public class CForm extends JDialog implements CComponent {

    private final CPanel myContentPanel;

    public CForm() {
        myContentPanel =  new CPanel();
        setContentPane(myContentPanel);
        setModal(true);
        setFocusable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void addCloseBehavior(WindowAdapter closeBehavior) {
        addWindowListener(closeBehavior);
    }

    public CForm setConstraints(String... constraints ) {
        myContentPanel.setConstraints(constraints);
        return this;
    }

    public void setFormSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void showForm() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
