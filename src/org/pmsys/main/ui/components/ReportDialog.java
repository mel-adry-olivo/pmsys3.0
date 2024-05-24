package org.pmsys.main.ui.components;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.base.CButton;
import org.pmsys.main.ui.components.base.CButtonFactory;
import org.pmsys.main.ui.components.base.CDialog;

import javax.swing.*;
import java.awt.*;

public class ReportDialog extends CDialog {

    private JTextArea textArea;

    public ReportDialog() {
        setupComponent();
    }

    public void setReportContent(String content) {
        textArea.setText(content);
    }

    private void setupComponent() {
        setFormSize(500, 500);
        setConstraints("insets 8% 10% 8% 10%, fillx", "", "[]4%[]");

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setCaretColor(textArea.getBackground());
        textArea.setEditable(false);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

        CButton saveButton = CButtonFactory.createFilledButton("Save");
        saveButton.addActionListener(e -> ActionManager.executeAction(Actions.SAVE_REPORT, saveButton, this));

        add(textArea, "h 100%, growx, wrap");
        add(saveButton, "growx");
    }
}
