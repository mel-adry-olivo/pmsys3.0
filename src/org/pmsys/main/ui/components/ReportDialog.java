package org.pmsys.main.ui.components;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.constants.ColorConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ReportDialog extends CDialog {

    private JTextArea textArea;
    private CButton saveButton;

    public ReportDialog(String label) {
        setupComponent(label);
    }

    public String getContent() {
        return textArea.getText();
    }

    public void handleSaveButton(ActionListener listener) {
        saveButton.addActionListener(listener);
    }

    public void setReportContent(String content) {
        textArea.setText(content);
    }

    public void clearReportContent() {
        textArea.setText("");
    }

    public void goToTop() {
        textArea.setCaretPosition(0);
    }

    private void setupComponent(String label) {
        setFormSize(500, 500);
        setConstraints("insets 6% 10% 8% 10%, fillx", "", "[]4%[]");

        CLabel title = CLabelFactory.createH1Label(label);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setCaretColor(textArea.getBackground());
        textArea.setEditable(false);
        textArea.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        textArea.setMargin(new Insets(4,4,4,4));

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.decode(ColorConstants.BORDER)));

        saveButton = CButtonFactory.createFilledButton("Save");

        add(title, "growx, wrap");
        add(scrollPane, "h 100%, growx, wrap");
        add(saveButton, "growx");
    }
}
