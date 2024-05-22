package org.pmsys.main.ui.components.base;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;

public class CScrollPane extends JScrollPane implements MigLayoutCompatible<CScrollPane>, CComponent {

    protected final CPanel view;

    public CScrollPane() {
        this("");
    }

    public CScrollPane(String... constraints) {
        view = new CPanel(constraints);
        getViewport().add(view);
        setupComponent();
    }

    private void setupComponent() {
        setBorder(BorderFactory.createEmptyBorder());
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUnitIncrement(12);
        getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,
                "width:10;" +
                        "trackArc:999;");
    }

    public void addToView(JComponent component, String constraint) {
        view.add(component, constraint);
    }

    @Override
    public CScrollPane setConstraints(String... params) {
        view.setConstraints(params);
        return this;
    }

    @Override
    public CScrollPane setLayoutConstraints(String layoutConstraints) {
        view.setLayoutConstraints(layoutConstraints);
        return this;
    }

    @Override
    public CScrollPane setRowConstraints(String rowConstraints) {
        view.setRowConstraints(rowConstraints);
        return this;
    }

    @Override
    public CScrollPane setColumnConstraints(String columnConstraints) {
        view.setColumnConstraints(columnConstraints);
        return this;
    }
}
