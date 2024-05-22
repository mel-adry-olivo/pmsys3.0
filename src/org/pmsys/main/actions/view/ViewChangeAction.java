package org.pmsys.main.actions.view;

import org.pmsys.main.ui.components.constants.ColorConstants;
import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.ui.MainWindow;
import org.pmsys.main.ui.components.base.CButton;
import org.pmsys.main.ui.components.base.CComponent;

import javax.swing.*;
import java.awt.*;

public class ViewChangeAction implements SimpleAction {

    @Override
    public void execute(JComponent source, CComponent comp) {
        MainWindow.WindowNavBar menu = (MainWindow.WindowNavBar) comp;
        CButton clickedButton = (CButton) source;

        if (!clickedButton.equals(menu.getSelectedButton())) {
            CButton previousButton = menu.getSelectedButton();
            if (previousButton != null) {
                previousButton.setSelected(false);
                previousButton.setBackground(Color.WHITE);
            }

            menu.setSelectedButton(clickedButton);
            clickedButton.setPressedBackgroundColor(ColorConstants.PRESSED_GREY).applyStyles();
            clickedButton.setSelected(true);
            clickedButton.setBackground(new Color(80, 80, 80));

            menu.handleViewChange(clickedButton);
        }
    }
}
