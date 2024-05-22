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

    public CForm addCloseBehavior(WindowAdapter closeBehavior) {
        addWindowListener(closeBehavior);
        return this;
    }

    public CForm setConstraints(String... constraints ) {
        myContentPanel.setConstraints(constraints);
        return this;
    }

    public CForm setFormSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        return this;
    }

    public CForm showForm() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        return this;
    }
}
