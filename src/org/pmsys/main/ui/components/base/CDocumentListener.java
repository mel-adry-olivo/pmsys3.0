package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.components.constants.ColorConstants;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class CDocumentListener implements DocumentListener {

    private final JTextComponent textComponent;
    private final FlatStyleable<?> styleable;

    public CDocumentListener(JTextComponent textComponent, FlatStyleable<?> styleable) {
        this.textComponent = textComponent;
        this.styleable = styleable;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateBackgroundColor();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateBackgroundColor();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateBackgroundColor();
    }

    private void updateBackgroundColor() {
        if (textComponent.getText().isEmpty()) {
            styleable.setInactiveBackgroundColor(ColorConstants.LIGHT_GREY);
            styleable.setBorderColor(ColorConstants.LIGHT_GREY);
            styleable.setBackgroundColor(ColorConstants.LIGHT_GREY);
        } else {
            styleable.setBorderColor("darken(#ffffff, 23%)");
            styleable.setBackgroundColor(ColorConstants.WHITE);
            styleable.setInactiveBackgroundColor(ColorConstants.WHITE);
        }
    }
}
