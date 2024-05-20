package org.pmsys.main.actions;

import org.pmsys.main.ui.CComponent;

import javax.swing.*;

public interface SimpleAction {
    void execute(JComponent source, CComponent view);
}
