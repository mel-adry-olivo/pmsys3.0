package org.pmsys.main.actions.task;

import org.pmsys.main.entities.Task;
import org.pmsys.main.entities.request.TaskRequest;
import org.pmsys.main.entities.request.TaskRequestStatus;
import org.pmsys.main.entities.result.TaskResult;
import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.components.TaskCard;

import javax.swing.*;

public class EditTaskAction extends AbstractTaskAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        Task taskToEdit = taskForm.getTaskFromForm();
        String oldStatus = taskToEdit.getStatus(); // used to change sections if updated task status changes
        TaskRequest taskRequest = (TaskRequest) taskForm.getFormData(taskToEdit.getId());
        TaskResult result = validateTaskRequest(taskRequest);

        if(result.getStatus() == TaskRequestStatus.SUCCESS) {
            Task validatedTask = result.getTask();
            taskToEdit.setUpdateFrom(validatedTask);
            taskService.updateInFile(taskToEdit);

            TaskCard updatedTaskCard = projectView.createTaskCard(taskToEdit);
            projectView.updateTaskInView(updatedTaskCard, oldStatus);
            projectView.getCurrentProject().revalidateCounts();
            updateProjectAndHideForm();
        }
    }
}
