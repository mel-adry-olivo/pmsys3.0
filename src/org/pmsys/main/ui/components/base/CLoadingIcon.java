package org.pmsys.main.ui.components.base;

import javax.swing.*;
import java.awt.*;

public class CLoadingIcon extends CLabel {

    public CLoadingIcon() {
            super(new ImageIcon("src/org/pmsys/resources/icons/loading_180.gif"),JLabel.CENTER);
            setMaximumSize(new Dimension(100,100));
            setVisible(true);
            setOpaque(true);
    }
}
