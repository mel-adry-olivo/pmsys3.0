package org.pmsys.main.actions.auth;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import org.pmsys.main.actions.Actions;
import org.pmsys.main.actions.SimpleAction;
import org.pmsys.main.ui.auth.AuthPanel;
import org.pmsys.main.ui.auth.AuthWindow;
import org.pmsys.main.ui.CComponent;

import javax.swing.*;

public class SwitchAuthAction implements SimpleAction {
    @Override
    public void execute(JComponent source, CComponent comp) {
        AuthPanel form = (AuthPanel) comp;
        AuthWindow authWindow = form.getAuthWindow();

        FlatAnimatedLafChange.showSnapshot();

        boolean isLogin = form.getAction() == Actions.LOGIN;
        String nextPanel = isLogin ? "registerPanel" : "loginPanel";
        authWindow.getCardLayout().show(authWindow.getContentPane(), nextPanel);

        form.resetForm();

        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
}
