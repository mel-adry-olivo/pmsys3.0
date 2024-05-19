package org.pmsys.main.actions.auth;

import org.pmsys.constants.AuthRequestStatus;
import org.pmsys.main.actions.Action;
import org.pmsys.main.model.request.AuthRequest;
import org.pmsys.main.model.result.AuthResult;
import org.pmsys.main.service.AuthService;
import org.pmsys.main.service.ServiceManager;
import org.pmsys.main.ui.forms.AbstractAuthForm;
import org.pmsys.main.ui.views.AuthWindow;
import org.pmsys.main.ui.views.UIView;
import org.pmsys.main.utils.TimeUtils;

import javax.swing.*;


public abstract class AbstractAuthAction implements Action {
    protected final AuthService authService;

    protected AbstractAuthAction() {
        authService = (AuthService) ServiceManager.getService("authentication");
    }

    protected abstract AuthResult executeAction(AuthRequest request);

    protected abstract void handleSuccess(UIView view, AuthResult authResult);

    protected abstract void handleFailure(UIView view, AuthResult authResult);

    @Override
    public final void execute(JComponent source, UIView view) {
        AuthWindow authWindow = ((AbstractAuthForm) view).getAuthParent();
        AuthRequest request = ((AbstractAuthForm) view).getAuthRequest();

        if (request == null) return;

        authWindow.toggleLoadingState(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                AuthResult authResult = executeAction(request);

                if (authResult.getStatus() == AuthRequestStatus.SUCCESS) {
                    handleSuccess(view, authResult);
                } else {
                    handleFailure(view, authResult);
                }

                TimeUtils.delayInMillis(300);
                return null;
            }

            @Override
            protected void done() {
                authWindow.toggleLoadingState(false);
            }
        }.execute();
    }
}
