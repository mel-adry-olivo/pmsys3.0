package org.pmsys.main.controller;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import org.pmsys.constants.AuthRequestStatus;
import org.pmsys.main.Application;
import org.pmsys.main.model.request.AuthRequest;
import org.pmsys.main.model.result.AuthResult;
import org.pmsys.main.manager.SessionManager;
import org.pmsys.main.service.AuthService;
import org.pmsys.main.ui.forms.AbstractAuthUI;
import org.pmsys.main.ui.forms.SignInUI;
import org.pmsys.main.ui.forms.SignUpUI;
import org.pmsys.main.ui.views.AuthView;
import org.pmsys.main.utils.TimeUtils;

import javax.swing.SwingWorker;
import java.awt.event.ActionEvent;

/**
 * This class is the controller for handling authentication actions such as sign in and sign up.
 */
public final class AuthController{

    private final AuthService authService;
    private final AuthView authView;
    private final Application application;
    private final SignInUI signInUI;
    private final SignUpUI signUpUI;

    /**
     * Constructs an AuthController with the specified services and views.
     *
     * @param authService the authentication service
     * @param authView the authentication view
     * @param application the main application instance
     */
    public AuthController(AuthService authService, AuthView authView, Application application) {
        this.authService = authService;
        this.authView = authView;
        this.application = application;
        this.signInUI = authView.getSignInForm();
        this.signUpUI = authView.getSignUpForm();
        attachListeners();
    }

    /**
     * Attaches listeners to the sign in and sign up forms.
     */
    public void attachListeners() {
        // Sign up
        signUpUI.handleLinkClick(this::handleChangeView);
        signUpUI.handleButtonClick(this::handleAuthClick);
        // Sign in
        signInUI.handleLinkClick(this::handleChangeView);
        signInUI.handleButtonClick(this::handleAuthClick);
    }

    /**
     * Handles changing the view between sign in and sign up forms.
     */
    private void handleChangeView() {
        FlatAnimatedLafChange.showSnapshot();
        authView.getCardLayout().show(authView.getContentPanel(), signInUI.isVisible() ? "signUpPanel" : "signInPanel");
        resetForms();
        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    /**
     * Resets the fields and error messages in both sign in and sign up forms.
     */
    private void resetForms() {
        signInUI.resetForm();
        signUpUI.resetForm();
    }

    /**
     * Handles authentication actions for sign in and sign up.
     *
     * @param e the action event triggered by the button click
     */
    private void handleAuthClick(ActionEvent e) {
        AbstractAuthUI authUI = signInUI.isVisible() ? signInUI : signUpUI;
        AuthRequest request = getUserCredentials(authUI);
        if (request == null) return;

        toggleLoadingState(true);

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {

                AuthResult authResult = authUI == signInUI ?
                        authService.signIn(request) : authService.signUp(request);

                if (authUI == signInUI) {
                    handleSignInResult(authResult);
                } else {
                    handleSignUpResult(authResult);
                }

                TimeUtils.delayInMillis(300);
                return null;
            }

            @Override
            protected void done() {
                toggleLoadingState(false);
            }
        }.execute();
    }

    /**
     * Retrieves user credentials from the current UI.
     *
     * @param authUI the authentication UI (sign in or sign up)
     * @return an AuthRequest containing the user credentials, or null if validation fails
     */
    private AuthRequest getUserCredentials(AbstractAuthUI authUI) {
        String username = authUI.getUsername();
        String password = new String(authUI.getPassword());

        if(username.isEmpty() || password.isEmpty()) {
            authUI.showErrorMessage("Fields cannot be empty!");
            authUI.showErrorInput(username.isEmpty(), password.isEmpty());
            return null;
        }
        return new AuthRequest(username, password);
    }

    /**
     * Handles the result of a sign in attempt.
     *
     * @param authResult the result of the sign in attempt
     */
    private void handleSignInResult(AuthResult authResult) {
        if (authResult.getStatus() == AuthRequestStatus.SUCCESS) {
            SessionManager.getInstance().setCurrentUser(authResult.getUser());
            application.launchApplication();
            authView.dispose();
        } else {
            signInUI.showErrorMessage(authResult.getErrorMessage());
        }
    }

    /**
     * Handles the result of a sign up attempt.
     *
     * @param authResult the result of the sign up attempt
     */
    private void handleSignUpResult(AuthResult authResult) {
        if (authResult.getStatus() == AuthRequestStatus.SUCCESS) {
            handleChangeView();
        } else {
            signUpUI.showErrorMessage(authResult.getErrorMessage());
        }
    }

    /**
     * Toggles the loading state of the authentication view.
     *
     * @param isLoading true to show the loading state, false to hide it
     */
    private void toggleLoadingState(boolean isLoading) {
        authView.getContentPanel().setVisible(!isLoading);
        authView.getGlassPane().setVisible(isLoading);
    }
}
