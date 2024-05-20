package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;
import org.pmsys.main.ui.utils.FlatColorist;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class CPasswordField extends JPasswordField implements FlatColorable<CPasswordField>{

    private final FlatStyler styler;
    private final FlatColorist<CPasswordField> colorist;


    public CPasswordField() {
        styler = new FlatStyler();
        colorist = new FlatColorist<>(this, styler);

        setupDefaults();
    }

    public CPasswordField setTextMargin(int top, int right, int bottom, int left) {
        styler.getStyleMap().put("margin", top + ", " + right + ", " + bottom + ", " + left);
        return this;
    }


    public CPasswordField setPlaceholder(String text) {
        putClientProperty("JTextField.placeholderText", text);
        return this;
    }


    public CPasswordField setBorderWidth(int width) {
        styler.getStyleMap().put("borderWidth", String.valueOf(width));
        return this;
    }

    public CPasswordField setFontStyle(String style) {
        styler.getStyleMap().put("font", style);
        return this;
    }


    @Override
    public CPasswordField setBorderColor(String color) {
        colorist.setBorderColor(color);
        return this;
    }

    @Override
    public CPasswordField setFocusedBorderColor(String color) {
        colorist.setFocusedBorderColor(color);
        return this;
    }

    @Override
    public CPasswordField setInactiveBackgroundColor(String color) {
        colorist.setInactiveBackgroundColor(color);
        return this;
    }

    @Override
    public CPasswordField setFocusedBackgroundColor(String color) {
        colorist.setFocusedBackgroundColor(color);
        return this;
    }

    @Override
    public CPasswordField setBackgroundColor(String color) {
        colorist.setBackgroundColor(color);
        return this;
    }

    @Override
    public CPasswordField applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }

    private void setupDefaults() {
        colorist.setBorderColor(AppColors.BORDER);
        colorist.setFocusedBorderColor(AppColors.ACCENT);

        setBorderWidth(1);
        getDocument().addDocumentListener(new CDocumentListener(this,this));
        setForeground(Color.decode(AppColors.DARK_GREY));
        setOpaque(false);
    }
}
