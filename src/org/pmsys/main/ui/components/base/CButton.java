package org.pmsys.main.ui.components.base;


import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class CButton extends JButton {

    private FlatStyler styler;

    public CButton(Icon icon) {
        this("", true);
        setIcon(icon);
    }

    public CButton(String text, boolean isIcon) {
        super(text);
        styler = new FlatStyler();

        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if (isIcon) {
            setBorderPainted(false);
        }
    }

    public CButton setFontStyle(String style) {
        styler.getStyleMap().put("font", style);
        return this;
    }

    public CButton setBorderWidth(int width) {
        styler.getStyleMap().put("borderWidth", String.valueOf(width));
        return this;
    }

    public CButton setArc(int arc) {
        styler.getStyleMap().put("arc", String.valueOf(arc));
        return this;
    }

    public CButton setBorderColor(String color) {
        styler.getStyleMap().put("borderColor", color);
        return this;
    }

    public CButton setHoverBorderColor(String color) {
        styler.getStyleMap().put("hoverBorderColor", color);
        return this;
    }

    public CButton setTextMargin(int top, int right, int bottom, int left) {
        styler.getStyleMap().put("margin",
                String.valueOf(top) + ", " +
                String.valueOf(right) + ", " +
                String.valueOf(bottom) + ", " +
                String.valueOf(left));
        return this;
    }

    public CButton setHoverForegroundColor(String color) {
        styler.getStyleMap().put("hoverForeground", color);
        return this;
    }

    public CButton setForegroundColor(String colorCode) {
        styler.getStyleMap().put("foreground", colorCode);
        return this;
    }

    public CButton setBackgroundColor(String colorCode) {
        styler.getStyleMap().put("background", colorCode);
        return this;
    }

    public CButton setHoverBackgroundColor(String colorCode) {
        styler.getStyleMap().put("hoverBackground", colorCode);
        return this;
    }

    public CButton setPressedBackgroundColor(String colorCode) {
        styler.getStyleMap().put("pressedBackground", colorCode);
        return this;
    }

    public CButton setPressedForegroundColor(String colorCode) {
        styler.getStyleMap().put("pressedForeground", colorCode);
        return this;
    }


    public CButton setSVGIcon(Icon icon) {
        setIcon(icon);
        return this;
    }

    public CButton applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }
}
