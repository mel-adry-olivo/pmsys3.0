package org.pmsys.main.actions.task;

import org.pmsys.main.entities.Task;
import org.pmsys.main.entities.request.TaskRequest;
import org.pmsys.main.entities.request.TaskRequestStatus;
import org.pmsys.main.entities.result.TaskResult;
import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;

public class AddTaskAction extends AbstractTaskAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        TaskRequest taskRequest = (TaskRequest) taskForm.getFormData();
        TaskResult result = validateTaskRequest(taskRequest);

        if(result.getStatus() == TaskRequestStatus.SUCCESS) {
            Task validatedTask = result.getTask();
            validatedTask.setProjectId(projectView.getCurrentProject().getId());
            taskService.saveTask(validatedTask);

            projectView.addTaskToView(projectView.createTaskCard(validatedTask));
            projectView.getCurrentProject().addTask(validatedTask);

            updateProjectAndHideForm();
        }
    }
}
