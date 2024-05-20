package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class CTextField extends JTextField implements FlatColorable<CTextField>{

    private final FlatStyler styler;

    private boolean isBorderless = false;

    public CTextField(boolean isBorderless) {
        this.isBorderless = isBorderless;
        styler = new FlatStyler();
        setupDocumentListener();
        applyFlatStyle();
        setForeground(Color.decode(AppColors.DARK_GREY));
        setOpaque(false);
    }


    @Override
    public CTextField setInactiveBackgroundColor(String color) {
        styler.getStyleMap().put("inactiveBackground", color);
        return this;
    }

    public CTextField setTextMargin(int top, int right, int bottom, int left) {
        styler.getStyleMap().put("margin",
                String.valueOf(top) + ", " +
                        String.valueOf(right) + ", " +
                        String.valueOf(bottom) + ", " +
                        String.valueOf(left));
        return this;
    }

    public CTextField setBorder(String border) {
        styler.getStyleMap().put("border", border);
        return this;
    }

    public CTextField setPlaceholder(String text) {
        putClientProperty("JTextField.placeholderText", text);
        return this;
    }

    @Override
    public CTextField setBorderColor(String color) {
        styler.getStyleMap().put("borderColor", color);
        return this;
    }

    @Override
    public CTextField setBackgroundColor(String color) {
        styler.getStyleMap().put("background", color);
        return this;
    }

    @Override
    public CTextField setFocusedBackgroundColor(String color) {
        styler.getStyleMap().put("focusedBackground", color);
        return this;
    }

    @Override
    public CTextField setFocusedBorderColor(String color) {
        styler.getStyleMap().put("focusedBorderColor", color);
        return this;
    }

    public CTextField setBorderWidth(int width) {
        styler.getStyleMap().put("borderWidth", String.valueOf(width));
        return this;
    }

    public CTextField setFontStyle(String style) {
        styler.getStyleMap().put("font", style);
        return this;
    }

    public CTextField applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }


    private void setupDocumentListener() {
        if(isBorderless) return;
        getDocument().addDocumentListener(new CDocumentListener(this,this));
    }
}
