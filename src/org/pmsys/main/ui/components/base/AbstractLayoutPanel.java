package org.pmsys.main.ui.components.base;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public abstract class AbstractLayoutPanel extends JPanel
        implements MigLayoutCompatible<AbstractLayoutPanel>, CComponent {

    private final MigLayout layout;


    public AbstractLayoutPanel(String... constraints) {
        layout = new MigLayout();
        setLayout(layout);
        setConstraints(constraints);
    }

    @Override
    public AbstractLayoutPanel setConstraints(String... constraints) {
        layout.setLayoutConstraints(constraints[0]);
        if (constraints.length > 1) {
            layout.setColumnConstraints(constraints[1]);
        }
        if (constraints.length > 2) {
            layout.setRowConstraints(constraints[2]);
        }
        return this;
    }
    @Override
    public AbstractLayoutPanel setRowConstraints(String rowConstraints) {
        layout.setRowConstraints(rowConstraints);
        return this;
    }
    @Override
    public AbstractLayoutPanel setColumnConstraints(String columnConstraints) {
        layout.setColumnConstraints(columnConstraints);
        return this;
    }
    @Override
    public AbstractLayoutPanel setLayoutConstraints(String layoutConstraints) {
        layout.setLayoutConstraints(layoutConstraints);
        return this;
    }
}
