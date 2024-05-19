package org.pmsys.main.actions.auth;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import org.pmsys.main.actions.Action;
import org.pmsys.main.ui.forms.AbstractAuthForm;
import org.pmsys.main.ui.forms.LoginAuthForm;
import org.pmsys.main.ui.views.AuthWindow;
import org.pmsys.main.ui.views.UIView;

import javax.swing.*;

public class SwitchFormAction implements Action {
    @Override
    public void execute(JComponent source, UIView view) {
        AbstractAuthForm form = (AbstractAuthForm) view; // Cast to the common base class
        AuthWindow authWindow = form.getAuthParent();

        FlatAnimatedLafChange.showSnapshot();

        String nextPanel = form instanceof LoginAuthForm ? "registerPanel" : "loginPanel";
        authWindow.getCardLayout().show(authWindow.getContentPanel(), nextPanel);

        form.resetForm();

        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }
}
