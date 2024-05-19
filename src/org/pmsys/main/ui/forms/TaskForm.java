package org.pmsys.main.ui.forms;

import org.pmsys.main.model.Task;
import org.pmsys.main.model.request.Request;
import org.pmsys.main.model.request.TaskRequest;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.utils.DateUtils;

public class TaskForm extends AbstractForm {

    private FlatTextField titleField;
    private FlatTextField descriptionField;
    private FlatNumberSpinner durationSpinner;
    private FlatComboBox<String> priorityComboBox;
    private FlatComboBox<String> statusComboBox;

    public TaskForm() {
        setFormTitle("Task Create");
        setButtonText("Create");
    }

    public TaskForm(Task task) {
        setFormTitle("Task Update");
        setButtonText("Update");
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
        Task task = (Task) data;
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
        FlatLabel taskTitle = FlatLabelFactory.createDefaultLabel("Task Title");
        FlatLabel description = FlatLabelFactory.createDefaultLabel("Description");
        FlatLabel status = FlatLabelFactory.createDefaultLabel("Status");
        FlatLabel priority = FlatLabelFactory.createDefaultLabel("Priority");
        FlatLabel duration = FlatLabelFactory.createDefaultLabel("Duration (in days)");

        titleField = FlatFieldFactory.createTextField("Enter the task title");
        descriptionField = FlatFieldFactory.createTextField("Provide a short description");

        FlatPanel wrapper = new FlatPanel("insets 0, fillx");

        priorityComboBox = new FlatComboBox<>(new String[]{"Low", "Normal", "High"});
        statusComboBox = new FlatComboBox<>(new String[]{"Ready", "In Progress", "To Review", "Done"});
        durationSpinner = new FlatNumberSpinner();

        wrapper.add(priorityComboBox, "w 100%");
        wrapper.add(statusComboBox, "w 100%");
        wrapper.add(durationSpinner, "w 100%");

        add(taskTitle, "wrap, growx, span 3 1");
        add(titleField, "wrap, growx, span 3 1");
        add(description, "wrap, growx, span 3 1");
        add(descriptionField, "wrap, growx, span 3 1");

        add(status, "w 0%");
        add(priority, "w 0%");
        add(duration, "wrap, w 0%");

        add(wrapper, "w 100%, span 3 1, wrap");
    }


}
