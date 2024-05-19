package org.pmsys.main.ui.utils;

import org.pmsys.main.ui.components.base.FlatColorable;

import javax.swing.*;

public class FlatColorist<E extends JComponent> implements FlatColorable<E> {

    E comp;
    FlatStyler styler;

    public FlatColorist(E comp, FlatStyler styler) {
        this.comp = comp;
        this.styler = styler;
    }

    @Override
    public E setBorderColor(String color) {
        styler.getStyleMap().put("borderColor", color);
        return comp;
    }

    @Override
    public E setFocusedBorderColor(String color) {
        styler.getStyleMap().put("focusedBorderColor", color);
        return comp;
    }

    @Override
    public E setInactiveBackgroundColor(String color) {
        styler.getStyleMap().put("inactiveBackground", color);
        return comp;
    }

    @Override
    public E setFocusedBackgroundColor(String color) {
        styler.getStyleMap().put("focusedBackground", color);
        return comp;
    }

    @Override
    public E setBackgroundColor(String color) {
        styler.getStyleMap().put("background", color);
        return comp;
    }

    @Override
    public E applyFlatStyle() {
        return comp;
    }
}
