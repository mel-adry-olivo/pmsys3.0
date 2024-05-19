package org.pmsys.main.actions;

import org.pmsys.main.ui.views.UIView;

import javax.swing.*;

public interface Action {
    void execute(JComponent source, UIView view);
}
