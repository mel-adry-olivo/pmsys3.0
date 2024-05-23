package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.components.constants.ColorConstants;
import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;
import java.awt.*;

public class CTextField extends JTextField implements FlatStyleable<CTextField>, CComponent {

    private FlatStyler styler;

    private boolean isBorderless = false;

    public CTextField(boolean isBorderless) {
        this.isBorderless = isBorderless;
        setupDocumentListener();
        applyStyles();
        setForeground(Color.decode(ColorConstants.DARK_GREY));
        setOpaque(false);
    }


    public CTextField setTextMargin(int top, int right, int bottom, int left) {
        styler.getStyleMap().put("margin", top + ", " + right + ", " + bottom + ", " + left);
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


    public CTextField setFontStyle(String style) {
        styler.getStyleMap().put("font", style);
        return this;
    }

    @Override
    public CTextField getComponent() {
        return this;
    }

    @Override
    public FlatStyler getStyler() {
        if(styler == null) {
            styler = new FlatStyler();
        }
        return styler;
    }

    private void setupDocumentListener() {
        if(isBorderless) return;
        getDocument().addDocumentListener(new CDocumentListener(this,this));
    }
}
