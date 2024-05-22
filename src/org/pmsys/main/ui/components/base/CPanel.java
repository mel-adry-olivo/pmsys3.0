package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;


public class CPanel extends AbstractLayoutPanel implements FlatStyleable<CPanel> {

    private FlatStyler styler;

    public CPanel() {
        this("");
    }

    public CPanel(String... constraints) {
        super(constraints);
        setDefaults();
    }

    // FOR METHOD CHAINING
    @Override
    public CPanel setConstraints(String... constraints) {
        super.setConstraints(constraints);
        return this;
    }
    @Override
    public CPanel setLayoutConstraints(String layoutConstraints) {
        super.setLayoutConstraints(layoutConstraints);
        return this;
    }
    @Override
    public CPanel setColumnConstraints(String columnConstraints) {
        super.setColumnConstraints(columnConstraints);
        return this;
    }
    @Override
    public CPanel setRowConstraints(String rowConstraints) {
        super.setRowConstraints(rowConstraints);
        return this;
    }


    @Override
    public CPanel getComponent() {
        return this;
    }

    @Override
    public FlatStyler getStyler() {
        if(styler == null) {
            styler = new FlatStyler();
        }
        return styler;
    }

    private void setDefaults() {
        setPreferredSize(new Dimension(200, 200)); // default
        setOpaque(true);
        setVisible(true);
    }
}
