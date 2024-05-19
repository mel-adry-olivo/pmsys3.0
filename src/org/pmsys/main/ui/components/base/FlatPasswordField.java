package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;
import org.pmsys.main.ui.utils.FlatColorist;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class FlatPasswordField extends JPasswordField implements FlatColorable<FlatPasswordField>{

    private final FlatStyler styler;
    private final FlatColorist<FlatPasswordField> colorist;


    public FlatPasswordField() {
        styler = new FlatStyler();
        colorist = new FlatColorist<>(this, styler);

        setupDefaults();
    }

    public FlatPasswordField setTextMargin(int top, int right, int bottom, int left) {
        styler.getStyleMap().put("margin", top + ", " + right + ", " + bottom + ", " + left);
        return this;
    }


    public FlatPasswordField setPlaceholder(String text) {
        putClientProperty("JTextField.placeholderText", text);
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


    @Override
    public FlatPasswordField setBorderColor(String color) {
        colorist.setBorderColor(color);
        return this;
    }

    @Override
    public FlatPasswordField setFocusedBorderColor(String color) {
        colorist.setFocusedBorderColor(color);
        return this;
    }

    @Override
    public FlatPasswordField setInactiveBackgroundColor(String color) {
        colorist.setInactiveBackgroundColor(color);
        return this;
    }

    @Override
    public FlatPasswordField setFocusedBackgroundColor(String color) {
        colorist.setFocusedBackgroundColor(color);
        return this;
    }

    @Override
    public FlatPasswordField setBackgroundColor(String color) {
        colorist.setBackgroundColor(color);
        return this;
    }

    @Override
    public FlatPasswordField applyFlatStyle() {
        styler.applyFlatStyle(this);
        return this;
    }

    private void setupDefaults() {
        colorist.setBorderColor(AppColors.BORDER);
        colorist.setFocusedBorderColor(AppColors.ACCENT);

        setBorderWidth(1);
        getDocument().addDocumentListener(new FlatDocumentListener(this,this));
        setForeground(Color.decode(AppColors.DARK_GREY));
        setOpaque(false);
    }
}
