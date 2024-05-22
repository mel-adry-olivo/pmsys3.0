package org.pmsys.main.actions.task;

import org.pmsys.main.entities.Project;
import org.pmsys.main.entities.Task;
import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.components.TaskCard;

import javax.swing.*;

public class SetTaskStatusAction extends AbstractTaskAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        JMenuItem item = (JMenuItem) source;
        String itemName = item.getText();

        Task currentTask = ((TaskCard) comp).getTask();
        String oldStatus = currentTask.getStatus();

        currentTask.setStatus(itemName);
        taskService.updateTaskInFile(currentTask);

        TaskCard updatedTaskCard = projectView.createTaskCard(currentTask);
        projectView.updateTaskInView(updatedTaskCard, oldStatus);
        projectView.getCurrentProject().revalidateCounts();
        updateProjectAndHideForm();
    }
}
