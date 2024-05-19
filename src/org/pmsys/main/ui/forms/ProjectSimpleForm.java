package org.pmsys.main.ui.forms;

import org.pmsys.constants.AppColors;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.request.ProjectRequest;
import org.pmsys.main.entities.request.Request;
import org.pmsys.main.ui.components.base.*;

import java.awt.event.ActionEvent;

public class ProjectSimpleForm extends AbstractSimpleForm {

    private FlatTextField nameField;
    private FlatTextField descriptionField;
    private FlatDatePicker datePicker;

    public ProjectSimpleForm() {

    }

    @Override
    public Request getFormData() {
        return this.getFormData("");
    }

    @Override
    public Request getFormData(String id) {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String formattedDate = datePicker.getFormattedDate();

        return new ProjectRequest(id, name, description, formattedDate);
    }

    @Override
    public void setFormData(Object data) {

        Project project = (Project) data;
        nameField.setText(project.getTitle());
        descriptionField.setText(project.getDescription());
        datePicker.setDate(project.getDueDate());

        repaint();
        revalidate();
    }

    public void showInvalidDateError() {
        datePicker.setBorderColor(AppColors.ERROR).applyFlatStyle();
    }

    @Override
    public void showErrorFields() {
        if(nameField.getText().isBlank()) {
            setFieldError(nameField);
        }
        if(descriptionField.getText().isBlank()) {
            setFieldError(descriptionField);
        }
    }

    @Override
    String getFormTitle() {
        return "Project";
    }

    @Override
    String getButtonText() {
        return "Action";
    }

    @Override
    String rowConstraints() {
        return "[]2%[]4%[]2%[]4%[]2%[]";
    }

    @Override
    String colConstraints() {
        return "[]";
    }

    @Override
    public void clearFields() {
        nameField.setText("");
        descriptionField.setText("");
        datePicker.resetDates();
        resetError();
    }

    @Override
    void setupForm() {
        FlatLabel projectName = FlatLabelFactory.createSmallLabel("Project Name");
        FlatLabel description = FlatLabelFactory.createSmallLabel("Description");
        FlatLabel dueDate = FlatLabelFactory.createSmallLabel("Due Date");

        nameField = FlatFieldFactory.createTextField("Enter your project name");
        descriptionField = FlatFieldFactory.createTextField("Provide a short description");
        datePicker = new FlatDatePicker();

        add(projectName, "wrap, growx");
        add(nameField, "wrap, growx");
        add(description, "wrap, growx");
        add(descriptionField, "wrap, growx");
        add(dueDate, "wrap, growx");
        add(datePicker, "wrap, growx");
    }
}