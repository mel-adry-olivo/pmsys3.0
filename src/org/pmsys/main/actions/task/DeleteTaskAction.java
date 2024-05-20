package org.pmsys.main.actions.task;

import org.pmsys.main.ui.CComponent;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.utils.PopupMessages;

import javax.swing.*;

public class DeleteTaskAction extends AbstractTaskAction{
    @Override
    public void execute(JComponent source, CComponent view) {
        if (PopupMessages.CONFIRM_DELETION("task")) {
            TaskCard taskCard = (TaskCard) view;
            projectView.removeTaskFromView(taskCard);
            projectView.getCurrentProject().removeTask(taskCard.get());
            taskService.deleteTaskFromFile(taskCard.get());
            projectService.updateProjectInFile(projectView.getCurrentProject());
        }
    }
}
