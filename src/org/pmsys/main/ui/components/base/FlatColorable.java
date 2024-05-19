package org.pmsys.main.ui.components.base;

import javax.swing.*;

public interface FlatColorable<E extends JComponent> extends FlatStyleable<E> {

    E setBorderColor(String color);

    E setFocusedBorderColor(String color);

    E setInactiveBackgroundColor(String color);

    E setFocusedBackgroundColor(String color);

    E setBackgroundColor(String color);
}
