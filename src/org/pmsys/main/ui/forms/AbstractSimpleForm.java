package org.pmsys.main.ui.forms;

import org.pmsys.constants.AppColors;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.request.Request;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.CComponent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Simple form with a title and action button
 */
public abstract class AbstractSimpleForm extends FlatForm implements CComponent {

    protected FlatLabel errorLabel;
    protected FlatButton actionButton;

    private FlatLabel titleLabel;

    protected Actions action;

    public AbstractSimpleForm() {
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


    public final void showErrorMessage(String message) {
        errorLabel.setText(message);
        errorLabel.setForegroundColor(AppColors.ERROR).applyFlatStyle();
    }
    public void setAction(Actions action) {
        this.action = action;
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
        actionButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ActionManager.executeAction(action, actionButton, AbstractSimpleForm.this);
            }
        });
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
