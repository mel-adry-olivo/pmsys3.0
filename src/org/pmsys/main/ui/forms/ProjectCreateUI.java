package org.pmsys.main.ui.forms;

import org.pmsys.constants.AppColors;

import org.pmsys.main.entity.ProjectCreateRequest;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.listeners.ClickListener;

import javax.swing.*;
import java.awt.event.*;

public class ProjectCreateUI extends FlatForm {

    private FlatButton createButton;
    private FlatTextField nameField;
    private FlatTextField descriptionField;
    private FlatTextField dueDateField;

    public ProjectCreateUI() {
        setupComponent();
        setupEvents();
    }


    public ProjectCreateRequest getInitialProject() {
        dispose();
        return new ProjectCreateRequest(nameField.getText(), descriptionField.getText(), dueDateField.getText());
    }
    public void handleProjectCreation(ActionListener listener) {
        createButton.addActionListener(listener);
    }

    private void setupEvents() {
        addCloseBehavior(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                clearFields();
            }
        });
    }
    private void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
        dueDateField.setText("");
    }
    private void setupComponent() {
        setConstraints("insets 10% 14% 8% 12%, fillx", "", "[]6%[]2%[]4%[]2%[]4%[]2%[]8%[]");
        setFormSize(450, 520);

        FlatLabel title = new FlatLabel("Create Project")
                .setFontStyle(FlatLabel.H1_120)
                .setForegroundColor(AppColors.BLACK)
                .applyFlatStyle();

        FlatLabel name = createLabel("Project Name");
        FlatLabel description = createLabel("Description");
        FlatLabel dueDate = createLabel("Due Date");

        nameField = FlatFieldFactory.createTextField("Enter your project name");
        descriptionField = FlatFieldFactory.createTextField("Provide a short description");
        dueDateField = FlatFieldFactory.createTextField("Provide a due date");

        createButton = createButton("Create Project");

        add(title, "growx, wrap");
        add(name, "wrap, growx");
        add(nameField, "wrap, growx");
        add(description, "wrap, growx");
        add(descriptionField, "wrap, growx");
        add(dueDate, "wrap, growx");
        add(dueDateField, "wrap, growx");
        add(createButton, "growx, gapbottom 5, wrap");
    }
    private FlatLabel createLabel(String text) {
        return new FlatLabel(text)
                .setFontStyle(FlatLabel.SMALL)
                .setForegroundColor(AppColors.DARK_GREY)
                .applyFlatStyle();
    }
    private FlatButton createButton(String text) {
        return new FlatButton(text, false)
                .setForegroundColor(AppColors.WHITE)
                .setTextMargin(9,8,9,12)
                .setBackgroundColor(AppColors.ACCENT)
                .applyFlatStyle();
    }
}
