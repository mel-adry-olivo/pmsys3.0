package org.pmsys.main.ui.components.base;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public abstract class AbstractLayoutPanel extends JPanel
        implements MigLayoutCompatible<AbstractLayoutPanel>, CComponent {

    private final MigLayout layout;

    private String layoutConstraints = "";
    private String columnConstraints = "";
    private String rowConstraints = "";

    public AbstractLayoutPanel(String... constraints) {
        layout = new MigLayout();
        setLayout(layout);
        setConstraints(constraints);
    }

    @Override
    public AbstractLayoutPanel setConstraints(String... constraints) {
        layoutConstraints = constraints[0];
        layout.setLayoutConstraints(layoutConstraints);
        if (constraints.length > 1) {
            columnConstraints = constraints[1];
            layout.setColumnConstraints(columnConstraints);
        }
        if (constraints.length > 2) {
            rowConstraints = constraints[2];
            layout.setRowConstraints(rowConstraints);
        }
        return this;
    }
    @Override
    public AbstractLayoutPanel setRowConstraints(String rowConstraints) {
        this.rowConstraints = rowConstraints;
        layout.setRowConstraints(rowConstraints);
        return this;
    }
    @Override
    public AbstractLayoutPanel setColumnConstraints(String columnConstraints) {
        this.columnConstraints = columnConstraints;
        layout.setColumnConstraints(columnConstraints);
        return this;
    }
    @Override
    public AbstractLayoutPanel setLayoutConstraints(String layoutConstraints) {
        this.layoutConstraints = layoutConstraints;
        layout.setLayoutConstraints(layoutConstraints);
        return this;
    }
}
