package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class FlatPasswordField extends JPasswordField {

    private final FlatStyler styler;

    public FlatPasswordField() {
        styler = new FlatStyler();
        setBorderColor(AppColors.BORDER);
        setBorderWidth(1);
        setFocusedBorderColor(AppColors.ACCENT);

        setForeground(Color.decode(AppColors.DARK_GREY));
        setOpaque(false);
    }

    public FlatPasswordField setMinimumWidth(int width) {
        styler.getStyleMap().put("minimumWidth", String.valueOf(width));
        return this;
    }

    public FlatPasswordField setTextMargin(int top, int right, int bottom, int left) {
        styler.getStyleMap().put("margin",
                String.valueOf(top) + ", " +
                        String.valueOf(right) + ", " +
                        String.valueOf(bottom) + ", " +
                        String.valueOf(left));
        return this;
    }

    public FlatPasswordField setFocusedBackgroundColor(String color) {
        styler.getStyleMap().put("focusedBackground", color);
        return this;
    }


    public FlatPasswordField setBackgroundColor(String color) {
        styler.getStyleMap().put("background", color);
        return this;
    }

    public FlatPasswordField setInactiveBackgroundColor(String color) {
        styler.getStyleMap().put("inactiveBackground", color);
        return this;
    }

    public FlatPasswordField setPlaceholder(String text) {
        putClientProperty("JTextField.placeholderText", text);
        return this;
    }

    public FlatPasswordField setBorderColor(String color) {
        styler.getStyleMap().put("borderColor", color);
        return this;
    }

    public FlatPasswordField setFocusedBorderColor(String color) {
        styler.getStyleMap().put("focusedBorderColor", color);
        return this;
    }

    public FlatPasswordField setBorderWidth(int width) {
        styler.getStyleMap().put("borderWidth", String.valueOf(width));
        return this;
    }

    public FlatPasswordField setFontStyle(String style) {
        styler.getStyleMap().put("font", style);
        return this;
    }

    public FlatPasswordField applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }
}
