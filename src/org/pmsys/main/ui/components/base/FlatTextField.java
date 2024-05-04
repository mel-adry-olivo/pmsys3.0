package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class FlatTextField extends JTextField {

    private final FlatStyler styler;

    public FlatTextField() {
        styler = new FlatStyler();
        setBorderColor(AppColors.BORDER);
        setBorderWidth(1);
        setFocusedBorderColor(AppColors.ACCENT);

        setForeground(Color.decode(AppColors.DARK_GREY));
        setOpaque(false);
    }

    public FlatTextField setMinimumWidth(int width) {
        styler.getStyleMap().put("minimumWidth", String.valueOf(width));
        return this;
    }

    public FlatTextField setInactiveBackgroundColor(String color) {
        styler.getStyleMap().put("inactiveBackground", color);
        return this;
    }

    public FlatTextField setTextMargin(int top, int right, int bottom, int left) {
        styler.getStyleMap().put("margin",
                String.valueOf(top) + ", " +
                        String.valueOf(right) + ", " +
                        String.valueOf(bottom) + ", " +
                        String.valueOf(left));
        return this;
    }

    public FlatTextField setPlaceholder(String text) {
        putClientProperty("JTextField.placeholderText", text);
        return this;
    }

    public FlatTextField setBorderColor(String color) {
        styler.getStyleMap().put("borderColor", color);
        return this;
    }

    public FlatTextField setBackgroundColor(String color) {
        styler.getStyleMap().put("background", color);
        return this;
    }

    public FlatTextField setFocusedBackgroundColor(String color) {
        styler.getStyleMap().put("focusedBackground", color);
        return this;
    }

    public FlatTextField setFocusedBorderColor(String color) {
        styler.getStyleMap().put("focusedBorderColor", color);
        return this;
    }

    public FlatTextField setBorderWidth(int width) {
        styler.getStyleMap().put("borderWidth", String.valueOf(width));
        return this;
    }

    public FlatTextField setFontStyle(String style) {
        styler.getStyleMap().put("font", style);
        return this;
    }

    public FlatTextField applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }
}
