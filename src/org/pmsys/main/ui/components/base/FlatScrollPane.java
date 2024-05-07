package org.pmsys.main.ui.components.base;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;

public class FlatScrollPane extends JScrollPane implements MigLayoutCompatible<FlatScrollPane>{

    protected final FlatPanel view;

    public FlatScrollPane() {
        this("");
    }

    public FlatScrollPane(String... constraints) {
        view = new FlatPanel(constraints);
        getViewport().add(view);
        setupComponent();
    }

    private void setupComponent() {
        setBorder(BorderFactory.createEmptyBorder());
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUnitIncrement(10);
        getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,
                "width:10;" +
                        "trackArc:999;");
    }

    public void addToView(JComponent component, String constraint) {
        view.add(component, constraint);
    }

    public void addToView(JComponent component) {
        view.add(component);
    }

    public String getColumnConstraints() {
        return view.getColumnConstraints();
    }

    @Override
    public FlatScrollPane setConstraints(String... params) {
        view.setConstraints(params);
        return this;
    }

    @Override
    public FlatScrollPane setLayoutConstraints(String layoutConstraints) {
        view.setLayoutConstraints(layoutConstraints);
        return this;
    }

    @Override
    public FlatScrollPane setRowConstraints(String rowConstraints) {
        view.setRowConstraints(rowConstraints);
        return this;
    }

    @Override
    public FlatScrollPane setColumnConstraints(String columnConstraints) {
        view.setColumnConstraints(columnConstraints);
        return this;
    }
}
