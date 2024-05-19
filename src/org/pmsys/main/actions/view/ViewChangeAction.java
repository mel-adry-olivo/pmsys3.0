package org.pmsys.main.actions.view;

import org.pmsys.constants.AppColors;
import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.managers.ViewManager;
import org.pmsys.main.ui.MainWindow;
import org.pmsys.main.ui.components.base.FlatButton;
import org.pmsys.main.ui.views.UIView;
import org.pmsys.main.ui.views.Views;

import javax.swing.*;
import java.awt.*;

public class ViewChangeAction implements SimpleAction {

    @Override
    public void execute(JComponent source, UIView view) {
        MainWindow.WindowMenu menu = (MainWindow.WindowMenu) view;
        FlatButton clickedButton = (FlatButton) source;

        if (!clickedButton.equals(menu.getSelectedButton())) {
            FlatButton previousButton = menu.getSelectedButton();
            if (previousButton != null) {
                previousButton.setSelected(false);
                previousButton.setBackground(Color.WHITE);
            }

            menu.setSelectedButton(clickedButton);
            clickedButton.setPressedBackgroundColor(AppColors.PRESSED_GREY).applyFlatStyle();
            clickedButton.setSelected(true);
            clickedButton.setBackground(new Color(80, 80, 80));

            menu.handleViewChange(clickedButton);
        }
    }
}
