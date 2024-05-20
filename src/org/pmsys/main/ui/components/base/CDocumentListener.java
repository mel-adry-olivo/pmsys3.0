package org.pmsys.main.ui.components.base;

import org.pmsys.constants.AppColors;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

public class CDocumentListener implements DocumentListener {

    private final JTextComponent textComponent;
    private final FlatColorable<?> colorable;

    public CDocumentListener(JTextComponent textComponent, FlatColorable<?> colorable) {
        this.textComponent = textComponent;
        this.colorable = colorable;
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
            colorable.setInactiveBackgroundColor(AppColors.LIGHT_GREY);
            colorable.setBorderColor(AppColors.LIGHT_GREY);
            colorable.setBackgroundColor(AppColors.LIGHT_GREY);
        } else {
            colorable.setBorderColor("darken(#ffffff, 23%)");
            colorable.setBackgroundColor(AppColors.WHITE);
            colorable.setInactiveBackgroundColor(AppColors.WHITE);
        }

        colorable.applyFlatStyle();
    }
}
