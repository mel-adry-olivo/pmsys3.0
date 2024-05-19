package org.pmsys.main.ui.components.base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;


public class FlatForm extends JDialog{

    private final FlatPanel myContentPanel;

    public FlatForm() {
        myContentPanel =  new FlatPanel();
        setContentPane(myContentPanel);
        setModal(true);
        setFocusable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public FlatPanel getContentPanel() {
        return myContentPanel;
    }

    public FlatForm addCloseBehavior(WindowAdapter closeBehavior) {
        addWindowListener(closeBehavior);
        return this;
    }

    public FlatForm setConstraints(String... constraints ) {
        myContentPanel.setConstraints(constraints);
        return this;
    }

    public FlatForm setCloseBehavior(int behavior) {
        setDefaultCloseOperation(behavior);
        return this;
    }

    public FlatForm setFormSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        return this;
    }

    public FlatForm showForm() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        return this;
    }

    public FlatForm hideForm() {
        setVisible(false);
        dispose();
        return this;
    }
}
