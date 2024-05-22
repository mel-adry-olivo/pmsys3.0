package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.components.constants.ColorConstants;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class CPasswordField extends JPasswordField implements FlatStyleable<CPasswordField>, CComponent {

    private FlatStyler styler;

    public CPasswordField() {
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
    public CPasswordField getComponent() {
        return this;
    }

    @Override
    public FlatStyler getStyler() {
        if(styler == null) {
            styler = new FlatStyler();
        }
        return styler;
    }


    private void setupDefaults() {
        setBorderColor(ColorConstants.BORDER).setFocusedBorderColor(ColorConstants.ACCENT);
        setBorderWidth(1);
        getDocument().addDocumentListener(new CDocumentListener(this, this));
        setForeground(Color.decode(ColorConstants.DARK_GREY));
        setOpaque(false);
    }
}
