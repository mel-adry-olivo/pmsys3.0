package org.pmsys.main.ui.forms;

import org.pmsys.constants.AppColors;
import org.pmsys.constants.FormType;
import org.pmsys.main.model.Project;
import org.pmsys.main.model.request.ProjectRequest;
import org.pmsys.main.model.request.Request;
import org.pmsys.main.model.request.TaskRequest;
import org.pmsys.main.ui.components.base.*;

public class ProjectFormUI extends AbstractFormUI{

    private FlatTextField nameField;
    private FlatTextField descriptionField;
    private FlatDatePicker datePicker;

    public ProjectFormUI() {
        setFormTitle("Project Create");
        setButtonText("Create");
    }

    public ProjectFormUI(Project project) {
        setFormTitle("Project Edit");
        setButtonText("Update");
    }

    @Override
    public Request getFormData() {
        return new ProjectRequest(nameField.getText(), descriptionField.getText(), datePicker.getFormattedDate());
    }

    @Override
    public Request getFormData(String id) {
        return new ProjectRequest(id, nameField.getText(), descriptionField.getText(), datePicker.getFormattedDate());
    }

    @Override
    public void setFormData(Object data) {
        Project project = (Project) data;
        nameField.setText(project.getTitle());
        descriptionField.setText(project.getDescription());
        datePicker.setDate(project.getDueDate());
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
