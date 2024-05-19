package org.pmsys.main.manager;

import org.pmsys.constants.View;

import javax.swing.*;

public interface ViewManager {

    void showView(View view);
    void addView(View view, JComponent component);
}
