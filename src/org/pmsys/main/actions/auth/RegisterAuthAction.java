package org.pmsys.main.actions.auth;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.entities.request.AuthRequest;
import org.pmsys.main.entities.result.AuthResult;
import org.pmsys.main.ui.auth.AuthPanel;
import org.pmsys.main.ui.CComponent;

public class RegisterAuthAction extends AbstractAuthAction {
    @Override
    protected AuthResult executeAction(AuthRequest request) {
        return authService.signUp(request);
    }

    @Override
    protected void handleSuccess(CComponent view, AuthResult authResult) {
        ActionManager.executeAction(Actions.SWITCH_AUTH, null, view);
    }

    @Override
    protected void handleFailure(CComponent view, AuthResult authResult) {
        ((AuthPanel) view).showErrorMessage(authResult.getErrorMessage());
    }
}
