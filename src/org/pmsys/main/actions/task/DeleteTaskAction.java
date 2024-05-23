package org.pmsys.main.actions.task;

import org.pmsys.main.ui.components.base.CComponent;
import org.pmsys.main.ui.components.TaskCard;
import org.pmsys.main.ui.utils.MessageUtils;

import javax.swing.*;

public class DeleteTaskAction extends AbstractTaskAction{
    @Override
    public void execute(JComponent source, CComponent comp) {
        if (MessageUtils.CONFIRM_DELETION("task")) {
            TaskCard taskCard = (TaskCard) comp;
            projectView.removeTaskFromView(taskCard);
            projectView.getCurrentProject().removeTask(taskCard.getTask());
            taskService.deleteTask(taskCard.getTask());
            projectService.updateProject(projectView.getCurrentProject());
        }
    }
}
