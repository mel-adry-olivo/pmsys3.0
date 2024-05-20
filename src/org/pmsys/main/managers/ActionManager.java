package org.pmsys.main.managers;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.actions.auth.LoginAuthAction;
import org.pmsys.main.actions.auth.RegisterAuthAction;
import org.pmsys.main.actions.auth.SwitchAuthAction;
import org.pmsys.main.actions.project.*;
import org.pmsys.main.actions.task.*;
import org.pmsys.main.actions.view.ViewChangeAction;
import org.pmsys.main.ui.CComponent;

import javax.swing.*;
import java.util.EnumMap;
import java.util.Map;

public enum ActionManager {

    INSTANCE;

    private final Map<Actions, SimpleAction> actions = new EnumMap<>(Actions.class);

    public void registerAction(Actions action, SimpleAction simpleAction) {
        actions.put(action, simpleAction);
    }

    public static void executeAction(Actions action, JComponent source, CComponent view) {
        SimpleAction simpleAction = INSTANCE.actions.get(action);
        if (simpleAction != null) {
            //Benchmark.of(() -> simpleAction.execute(source, view), "Action: " + action.name()); // for benchmarking method performance
            simpleAction.execute(source, view);
        } else {
            throw new IllegalArgumentException("Action not registered: " + action.name());
        }
    }

    public void loadActions() {
        registerAction(Actions.LOGIN, new LoginAuthAction());
        registerAction(Actions.REGISTER, new RegisterAuthAction());
        registerAction(Actions.SWITCH_AUTH, new SwitchAuthAction());
        registerAction(Actions.SHOW_PROJECT_ADD_FORM, new ShowProjectAddFormAction());
        registerAction(Actions.SHOW_PROJECT_EDIT_FORM, new ShowProjectEditFormAction());
        registerAction(Actions.SHOW_TASK_ADD_FORM, new ShowTaskAddFormAction());
        registerAction(Actions.SHOW_TASK_EDIT_FORM, new ShowTaskEditFormAction());
        registerAction(Actions.ADD_PROJECT, new AddProjectAction());
        registerAction(Actions.VIEW_CHANGE, new ViewChangeAction());
        registerAction(Actions.OPEN_PROJECT, new OpenProjectAction());
        registerAction(Actions.CLOSE_PROJECT, new CloseProjectAction());
        registerAction(Actions.NEXT_PAGE, new ListNextAction());
        registerAction(Actions.PREVIOUS_PAGE, new ListPreviousAction());
        registerAction(Actions.SHOW_PROJECT_OPTIONS, new ShowProjectOptionsAction());
        registerAction(Actions.SHOW_TASK_OPTIONS, new ShowTaskOptionsAction());
        registerAction(Actions.EDIT_PROJECT, new EditProjectAction());
        registerAction(Actions.DELETE_PROJECT, new DeleteProjectAction());
        registerAction(Actions.ADD_TASK, new AddTaskAction());
        registerAction(Actions.DELETE_TASK, new DeleteTaskAction());
        registerAction(Actions.EDIT_TASK, new EditTaskAction());

        // TODO: Add other actions

        // INSTANCE.registerAction(Actions.EDIT_TASK, new EditTaskAction());
        // INSTANCE.registerAction(Actions.DELETE_TASK, new DeleteTaskAction());
    }
}