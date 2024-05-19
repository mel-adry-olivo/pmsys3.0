package org.pmsys.main.ui.forms;

import org.pmsys.constants.AppColors;
import org.pmsys.main.model.request.Request;
import org.pmsys.main.ui.components.base.*;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class AbstractFormUI extends FlatForm{

    protected FlatLabel errorLabel;
    protected FlatButton actionButton;

    private FlatLabel titleLabel;

    private ActionListener currentListener = null;

    public AbstractFormUI() {
        setConstraints("insets 8% 10% 8% 10%, fill", colConstraints(), "[]2%[]2%" + rowConstraints() + "8%[]");
        setFormSize(430, 500);
        setupLabels();
        setupForm();
        setupButton();
        addCloseBehavior(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                clearFields();
            }
        });
    }

    public void handleActionButton(ActionListener listener) {
        if (currentListener != null) {
            actionButton.removeActionListener(currentListener);
        }

        actionButton.addActionListener(listener);
        currentListener = listener;
    }

    public boolean hasActionListener() {
        return actionButton.getActionListeners().length > 0;
    }

    public final void showErrorMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setForegroundColor(AppColors.ERROR).applyFlatStyle();
    }
    public abstract void showErrorFields();
    public abstract Request getFormData();
    public abstract Request getFormData(String id);
    public abstract void setFormData(Object data);

    abstract String getFormTitle();
    abstract String getButtonText();
    abstract String rowConstraints();
    abstract String colConstraints();
    public abstract void clearFields();
    abstract void setupForm();

    protected void setFieldError(FlatTextField c) {
        c.setBorderColor(AppColors.ERROR).applyFlatStyle();
    }

    protected void resetError() {
        errorLabel.setText("This is a message label");
        errorLabel.setForegroundColor(AppColors.WHITE).applyFlatStyle();
    }

    private void setupLabels() {
        titleLabel = FlatLabelFactory.createScaledH1Label(getFormTitle());
        errorLabel = FlatLabelFactory.createDefaultLabel("This is an error label", AppColors.WHITE);

        add(titleLabel, "growx, wrap, span 3 1");
        add(errorLabel, "h 0%, growx, wrap , span 3 1");
    }

    private void setupButton() {
        actionButton = FlatButtonFactory.createFilledButton("Create");
        add(actionButton, "growx, gapbottom 5, wrap, span 3 1");
    }

    // New methods to update title and button text dynamically
    public void setFormTitle(String newTitle) {
        titleLabel.setText(newTitle);
    }

    public void setButtonText(String newText) {
        actionButton.setText(newText);
    }

}
