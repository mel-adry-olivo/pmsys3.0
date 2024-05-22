package org.pmsys.main.actions.task;

import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.ui.utils.PopupMessages;

import javax.swing.*;

public class DeleteTaskAction extends AbstractTaskAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        if (PopupMessages.CONFIRM_DELETION("task")) {
            TaskCard taskCard = (TaskCard) comp;
            projectView.removeTaskFromView(taskCard);
            projectView.getCurrentProject().removeTask(taskCard.getTask());
            taskService.deleteTaskFromFile(taskCard.getTask());
            projectService.updateProjectInFile(projectView.getCurrentProject());
        }
    }
}
