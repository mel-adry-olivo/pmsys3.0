package org.pmsys.main.actions.auth;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.ui.auth.AuthPanel;
import org.pmsys.main.ui.auth.AuthWindow;
import org.pmsys.main.ui.views.UIView;

import javax.swing.*;

public class SwitchAuthAction implements SimpleAction {
    @Override
    public void execute(JComponent source, UIView view) {
        AuthPanel form = (AuthPanel) view;
        AuthWindow authWindow = form.getAuthWindow();

        FlatAnimatedLafChange.showSnapshot();

        boolean isLogin = form.getAction() == Actions.LOGIN;
        String nextPanel = isLogin ? "registerPanel" : "loginPanel";
        authWindow.getCardLayout().show(authWindow.getContentPane(), nextPanel);

        form.resetForm();

        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
}
