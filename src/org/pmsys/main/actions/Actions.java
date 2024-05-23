package org.pmsys.main.actions;

import org.pmsys.main.actions.auth.*;
import org.pmsys.main.actions.project.*;
import org.pmsys.main.actions.task.*;
import org.pmsys.main.actions.view.*;

public enum Actions {
    LOGIN(new LoginAuthAction()),
    REGISTER(new RegisterAuthAction()),
    SWITCH_AUTH(new SwitchAuthAction()),
    SHOW_PROJECT_ADD_FORM(new ShowProjectAddFormAction()),
    SHOW_PROJECT_EDIT_FORM(new ShowProjectEditFormAction()),
    SHOW_PROJECT_OPTIONS(new ShowProjectOptionsAction()),
    OPEN_PROJECT(new OpenProjectAction()),
    CLOSE_PROJECT(new CloseProjectAction()),
    ADD_PROJECT(new AddProjectAction()),
    EDIT_PROJECT(new EditProjectAction()),
    SET_PROJECT_STATUS(new SetProjectStatusAction()),
    DELETE_PROJECT(new DeleteProjectAction()),
    SHOW_TASK_ADD_FORM(new ShowTaskAddFormAction()),
    SHOW_TASK_EDIT_FORM(new ShowTaskEditFormAction()),
    SHOW_TASK_OPTIONS(new ShowTaskOptionsAction()),
    ADD_TASK(new AddTaskAction()),
    EDIT_TASK(new EditTaskAction()),
    SET_TASK_STATUS(new SetTaskStatusAction()),
    DELETE_TASK(new DeleteTaskAction()),
    NEXT_PAGE(new ListNextAction()),
    PREVIOUS_PAGE(new ListPreviousAction()),
    EXPORT(null), // TODO
    VIEW_CHANGE(new ViewChangeAction()),
    SEARCH_ITEM_CLICK(new SearchItemClickAction());

    private final SimpleAction action;

    Actions(SimpleAction action) {
        this.action = action;
    }

    public SimpleAction getActionImpl() {
        return action;
    }
}
