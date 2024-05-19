package org.pmsys.main.actions.auth;

import org.pmsys.main.actions.ActionManager;
import org.pmsys.main.model.request.AuthRequest;
import org.pmsys.main.model.result.AuthResult;
import org.pmsys.main.ui.forms.RegisterAuthForm;
import org.pmsys.main.ui.views.UIView;

public class RegisterAction extends AbstractAuthAction {
    @Override
    protected AuthResult executeAction(AuthRequest request) {
        return authService.signUp(request);
    }

    @Override
    protected void handleSuccess(UIView view, AuthResult authResult) {
        ActionManager.executeAction("switchAuthForm", null, view);
    }

    @Override
    protected void handleFailure(UIView view, AuthResult authResult) {
        ((RegisterAuthForm) view).showErrorMessage(authResult.getErrorMessage());
    }
}
