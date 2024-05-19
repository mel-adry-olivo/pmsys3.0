package org.pmsys.main.actions.auth;

import org.pmsys.main.Application;
import org.pmsys.main.managers.SessionManager;
import org.pmsys.main.entities.request.AuthRequest;
import org.pmsys.main.entities.result.AuthResult;
import org.pmsys.main.ui.auth.AbstractAuthPanel;
import org.pmsys.main.ui.auth.AuthPanel;
import org.pmsys.main.ui.views.UIView;

public class LoginAuthAction extends AbstractAuthAction {
    @Override
    protected AuthResult executeAction(AuthRequest request) {
        return authService.signIn(request);
    }

    @Override
    protected void handleSuccess(UIView view, AuthResult authResult) {
        SessionManager.setUser(authResult.getUser());
        AbstractAuthPanel authForm = (AbstractAuthPanel) view;
        Application.start().launchApplication();
        authForm.getAuthWindow().dispose();
    }

    @Override
    protected void handleFailure(UIView view, AuthResult authResult) {
        ((AuthPanel) view).showErrorMessage(authResult.getErrorMessage());
    }
}