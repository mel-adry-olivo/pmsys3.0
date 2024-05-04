package org.pmsys.main.ui.components.base;

import javax.swing.*;
import java.awt.*;

public class FlatLoadingSpinner extends FlatLabel {

    private static final FlatLoadingSpinner instance = new FlatLoadingSpinner();
    public static FlatLoadingSpinner getInstance() {
        return instance;
    }

    public FlatLoadingSpinner() {
            super(new ImageIcon("src/org/pmsys/resources/icons/loading_180.gif"),JLabel.CENTER);
            setMaximumSize(new Dimension(100,100));
    }
}
