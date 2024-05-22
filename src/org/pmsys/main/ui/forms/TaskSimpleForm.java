package org.pmsys.main.ui.forms;

import org.pmsys.main.entities.Task;
import org.pmsys.main.entities.request.Request;
import org.pmsys.main.entities.request.TaskRequest;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.utils.DateUtils;

public class TaskSimpleForm extends AbstractSimpleForm {

    private Task task;

    private CTextField titleField;
    private CTextField descriptionField;
    private CNumberSpinner durationSpinner;
    private CComboBox<String> priorityComboBox;
    private CComboBox<String> statusComboBox;

    public TaskSimpleForm() {
        setFormTitle("Task Create");
        setButtonText("Create");
    }

    public Task getTaskFromForm() {
        return task;
    }

    @Override
    public Request getFormData() {
        return getFormData("");
    }

    @Override
    public Request getFormData(String id) {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String priority = (String) priorityComboBox.getSelectedItem();
        String status = (String) statusComboBox.getSelectedItem();
        String dueDate = DateUtils.getFormattedDate(DateUtils.addDaysToCurrentDate(durationSpinner.getNumberValue()));

        return new TaskRequest(id, title, description, dueDate, priority, status);
    }

    @Override
    public void setFormData(Object data) {
        task = (Task) data;
        titleField.setText(task.getTitle());
        descriptionField.setText(task.getDescription());
        priorityComboBox.setSelectedItem(task.getPriority());
        statusComboBox.setSelectedItem(task.getStatus());
        durationSpinner.setValue(DateUtils.getDaysBetweenDates(task.getDueDate()));
    }

    @Override
    public void showErrorFields() {
        setFieldError(titleField);
        setFieldError(descriptionField);
    }

    @Override
    String getFormTitle() {
        return "Task";
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
        return "[grow 0][grow 0][grow 0]";
    }

    @Override
    public void clearFields() {
        titleField.setText("");
        descriptionField.setText("");
    }

    @Override
    void setupForm() {
        CLabel taskTitle = CLabelFactory.createDefaultLabel("Task Title");
        CLabel description = CLabelFactory.createDefaultLabel("Description");
        CLabel status = CLabelFactory.createDefaultLabel("Status");
        CLabel priority = CLabelFactory.createDefaultLabel("Priority");
        CLabel duration = CLabelFactory.createDefaultLabel("Duration (in days)");

        titleField = CFieldFactory.createTextField("Enter the task title");
        descriptionField = CFieldFactory.createTextField("Provide a short description");

        CPanel wrapper = new CPanel("insets 0, fillx");

        priorityComboBox = new CComboBox<>(new String[]{"Low", "Normal", "High"});
        statusComboBox = new CComboBox<>(new String[]{"Ready", "In Progress", "To Review", "Done"});
        durationSpinner = new CNumberSpinner();

        wrapper.add(priorityComboBox, "w 100%");
        wrapper.add(statusComboBox, "w 100%");
        wrapper.add(durationSpinner, "w 100%");

        add(taskTitle, "wrap, growx, span 3 1");
        add(titleField, "wrap, growx, span 3 1");
        add(description, "wrap, growx, span 3 1");
        add(descriptionField, "wrap, growx, span 3 1");

        add(priority, "w 0%");
        add(status, "w 0%");
        add(duration, "wrap, w 0%");

        add(wrapper, "w 100%, span 3 1, wrap");
    }


}
