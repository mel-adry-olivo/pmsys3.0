package org.pmsys.main.actions;

import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;

public interface SimpleAction {
    void execute(JComponent source, CComponent comp);
}
