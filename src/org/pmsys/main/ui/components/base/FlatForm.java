package org.pmsys.main.ui.components.base;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;


public class FlatForm extends JDialog{

    private FlatPanel contentPanel;

    public FlatForm() {
        setContentPane(contentPanel = new FlatPanel());
        setModal(true);
        setFocusable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public FlatForm addCloseBehavior(WindowAdapter closeBehavior) {
        addWindowListener(closeBehavior);
        return this;
    }

    public FlatForm setConstraints(String... constraints ) {
        contentPanel.setConstraints(constraints);
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


    public void showForm() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
