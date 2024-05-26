package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public interface FlatStyleable<E extends JComponent>{

    E getComponent();
    FlatStyler getStyler();

    default E applyStyles() {
        getStyler().applyFlatStyle(getComponent());
        return getComponent();
    }

    default E setArc(int arc) {
        getStyler().getStyleMap().put("arc", String.valueOf(arc));
        return getComponent();
    }

    default E  setBorder(String border) {
        getStyler().getStyleMap().put("border", border);
        return getComponent();
    }

    default E setBorderColor(String color) {
        getStyler().getStyleMap().put("borderColor", color);
        return getComponent();
    }

    default void setFocusedBorderColor(String color) {
        getStyler().getStyleMap().put("focusedBorderColor", color);
        getComponent();
    }

    default void setInactiveBackgroundColor(String color) {
        getStyler().getStyleMap().put("inactiveBackground", color);
        getComponent();
    }

    default E setFocusedBackgroundColor(String color) {
        getStyler().getStyleMap().put("focusedBackground", color);
        return getComponent();
    }

    default E setBackgroundColor(String color) {
        getStyler().getStyleMap().put("background", color);
        return getComponent();
    }

    default E setForegroundColor(String color) {
        getStyler().getStyleMap().put("foreground", color);
        return getComponent();
    }

    default E setHoverBackgroundColor(String colorCode) {
        getStyler().getStyleMap().put("hoverBackground", colorCode);
        return getComponent();
    }

    default E setHoverForegroundColor(String colorCode) {
        getStyler().getStyleMap().put("hoverForeground", colorCode);
        return getComponent();
    }


    default E setTextMargin(int t, int l, int b, int r) {
        getStyler().getStyleMap().put("margin",
                t + ", " +
                        l + ", " +
                        b + ", " +
                        r);
        return getComponent();
    }

    default E setPressedBackgroundColor(String colorCode) {
        getStyler().getStyleMap().put("pressedBackground", colorCode);
        return getComponent();
    }

    default E setLineBorder(int top, int right, int bottom, int left, int radius) {
        setLineBorder(top,right,bottom,left, "#8F8F8F", radius);
        return getComponent();
    }
    default void setLineBorder(int top, int right, int bottom, int left, String color, int radius) {
        if(getComponent().getBorder() != null) {
            throw new IllegalArgumentException("Border already set");
        }
        getStyler().getStyleMap().put("border",
                top + ", " +
                        right + ", " +
                        bottom + ", " +
                        left + ", "   +
                        color +", 1," +
                        radius * 2);

        getStyler().applyFlatStyle(getComponent());
    }

    default E setMatteBorder(int top, int right, int bottom, int left) {
        if (getStyler().getStyleMap().containsKey("border")) {
            throw new IllegalArgumentException("Border already set");
        }
        getComponent().setBorder(BorderFactory.createMatteBorder(top, right, bottom, left, Color.decode("#8F8F8F")));
        return getComponent();
    }
}
