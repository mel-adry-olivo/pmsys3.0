package org.pmsys.main.ui.components.base;


import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FlatButton extends JButton {

    private FlatStyler styler;

    public FlatButton(Icon icon) {
        this("", true);
        setIcon(icon);
    }

    public FlatButton(String text, boolean isIcon) {
        super(text);
        styler = new FlatStyler();

        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        if (isIcon) {
            setBorderPainted(false);
        }
    }

    public FlatButton setFontStyle(String style) {
        styler.getStyleMap().put("font", style);
        return this;
    }

    public FlatButton setBorderWidth(int width) {
        styler.getStyleMap().put("borderWidth", String.valueOf(width));
        return this;
    }

    public FlatButton setArc(int arc) {
        styler.getStyleMap().put("arc", String.valueOf(arc));
        return this;
    }

    public FlatButton setBorderColor(String color) {
        styler.getStyleMap().put("borderColor", color);
        return this;
    }

    public FlatButton setHoverBorderColor(String color) {
        styler.getStyleMap().put("hoverBorderColor", color);
        return this;
    }

    public FlatButton setTextMargin(int top, int right, int bottom, int left) {
        styler.getStyleMap().put("margin",
                String.valueOf(top) + ", " +
                String.valueOf(right) + ", " +
                String.valueOf(bottom) + ", " +
                String.valueOf(left));
        return this;
    }

    public FlatButton setHoverForegroundColor(String color) {
        styler.getStyleMap().put("hoverForeground", color);
        return this;
    }

    public FlatButton setForegroundColor(String colorCode) {
        styler.getStyleMap().put("foreground", colorCode);
        return this;
    }

    public FlatButton setBackgroundColor(String colorCode) {
        styler.getStyleMap().put("background", colorCode);
        return this;
    }

    public FlatButton setHoverBackgroundColor(String colorCode) {
        styler.getStyleMap().put("hoverBackground", colorCode);
        return this;
    }

    public FlatButton setPressedBackgroundColor(String colorCode) {
        styler.getStyleMap().put("pressedBackground", colorCode);
        return this;
    }

    public FlatButton setPressedForegroundColor(String colorCode) {
        styler.getStyleMap().put("pressedForeground", colorCode);
        return this;
    }


    public FlatButton setSVGIcon(Icon icon) {
        setIcon(icon);
        return this;
    }

    public FlatButton applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }
}
