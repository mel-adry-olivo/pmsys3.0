package org.pmsys.main.ui.components.base;

import javax.swing.*;
import java.awt.*;

public class FlatLoadingIcon extends FlatLabel {

    public FlatLoadingIcon() {
            super(new ImageIcon("src/org/pmsys/resources/icons/loading_180.gif"),JLabel.CENTER);
            setMaximumSize(new Dimension(100,100));
            setVisible(true);
            setOpaque(true);
    }
}
