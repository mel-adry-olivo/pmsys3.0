package org.pmsys.main.ui.components.base;

import org.pmsys.main.ui.utils.FlatStyler;

import javax.swing.*;

public interface FlatStyleable<E extends JComponent> {

    E applyFlatStyle();
}
