package org.pmsys.main.actions.auth;

import org.pmsys.main.Application;
import org.pmsys.main.manager.SessionManager;
import org.pmsys.main.model.request.AuthRequest;
import org.pmsys.main.model.result.AuthResult;
import org.pmsys.main.ui.forms.LoginAuthForm;
import org.pmsys.main.ui.views.UIView;

public class LoginAction extends AbstractAuthAction {
    @Override
    protected AuthResult executeAction(AuthRequest request) {
        return authService.signIn(request);
    }

    @Override
    protected void handleSuccess(UIView view, AuthResult authResult) {
        SessionManager.getInstance().setCurrentUser(authResult.getUser());
        Application.start().launchApplication();
        ((LoginAuthForm) view).getAuthParent().dispose(); // Ensure type safety
    }

    @Override
    protected void handleFailure(UIView view, AuthResult authResult) {
        ((LoginAuthForm) view).showErrorMessage(authResult.getErrorMessage());
    }
}