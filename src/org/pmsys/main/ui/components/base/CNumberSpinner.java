package org.pmsys.main.ui.components.base;

import javax.swing.*;
import java.awt.*;

public class CNumberSpinner extends JSpinner implements CComponent {

    SpinnerNumberModel model;

    public CNumberSpinner() {
        model = new SpinnerNumberModel(0,0, 60, 1);

        setupComponent();
        disableEditable();
        setupCursor();
    }

    public int getNumberValue() {
        return ((SpinnerNumberModel) getModel()).getNumber().intValue();
    }

    public void setupComponent() {
        setModel(model);
        setPreferredSize(new Dimension(100, getHeight()));
    }
    private void disableEditable() {
        JFormattedTextField textField = ((JSpinner.DefaultEditor) getEditor()).getTextField();
        textField.setEditable(false);
        textField.setCaretColor(textField.getBackground());
    }
    private void setupCursor() {
        for (Component comp : getComponents()) {
            if (comp instanceof JButton) {
                comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
        }
    }
}
