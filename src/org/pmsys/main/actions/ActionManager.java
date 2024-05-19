package org.pmsys.main.actions;

import org.pmsys.main.ui.views.UIView;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ActionManager {
    private static volatile ActionManager instance;
    private final Map<String, org.pmsys.main.actions.Action> actions = new HashMap<>();

    private ActionManager() {}

    private static ActionManager getInstance() {
        if (instance == null) {
            synchronized (ActionManager.class) {
                if (instance == null) {
                    instance = new ActionManager();
                }
            }
        }
        return instance;
    }

    public static void registerAction(String name, org.pmsys.main.actions.Action action) {
        getInstance().actions.put(name, action);
    }

    public static void executeAction(String name, JComponent source, UIView view) {
        Action action = getInstance().actions.get(name);
        if (action != null) {
            action.execute(source, view);
        }
    }
}
