package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class FlatComboBox<E> extends JComboBox<E> implements FlatColorable<FlatComboBox<E>>{

    private final FlatStyler styler;

    public FlatComboBox(E[] items) {
        super(items);

        styler = new FlatStyler();

        setPreferredSize(new Dimension(30, getHeight()));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ((JComponent) getUI().getAccessibleChild(this, 0)).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public Object getSelectedItem() {
        return (Object) super.getSelectedItem();
    }

    @Override
    public FlatComboBox setBorderColor(String color) {
        styler.getStyleMap().put("borderColor", color);
        return this;
    }

    @Override
    public FlatComboBox setFocusedBorderColor(String color) {
        styler.getStyleMap().put("focusedBorderColor", color);
        return this;
    }

    @Override
    public FlatComboBox setInactiveBackgroundColor(String color) {
        styler.getStyleMap().put("inactiveBackground", color);
        return this;
    }

    @Override
    public FlatComboBox setFocusedBackgroundColor(String color) {
        styler.getStyleMap().put("focusedBackground", color);
        return this;
    }

    @Override
    public FlatComboBox setBackgroundColor(String color) {
        styler.getStyleMap().put("background", color);
        return this;
    }

    @Override
    public FlatComboBox applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }
}
