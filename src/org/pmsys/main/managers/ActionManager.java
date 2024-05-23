package org.pmsys.main.managers;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;
import java.util.EnumMap;
import java.util.Map;

public enum ActionManager {

    INSTANCE;

    private final Map<Actions, SimpleAction> actions = new EnumMap<>(Actions.class);

    public void clearActions() {
        actions.clear();
    }

    public void registerAction(Actions action, SimpleAction simpleAction) {
        actions.put(action, simpleAction);
    }

    public static void executeAction(Actions action, JComponent source, CComponent view) {
        SimpleAction simpleAction = INSTANCE.actions.get(action);
        if (simpleAction != null) {
            simpleAction.execute(source, view);
        } else {
            throw new IllegalArgumentException("Action not registered: " + action.name()); // for debugging
        }
    }

    public void loadActions() {
        for(Actions action : Actions.values()) {
            registerAction(action, action.getActionImpl());
        }
    }
}