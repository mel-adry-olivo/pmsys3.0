package org.pmsys.main.controller;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import org.pmsys.main.Application;
import org.pmsys.main.entity.Result;
import org.pmsys.main.manager.SessionManager;
import org.pmsys.main.service.AuthService;
import org.pmsys.main.ui.forms.SignInUI;
import org.pmsys.main.ui.forms.SignUpUI;
import org.pmsys.main.ui.views.AuthView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AuthController{

    private final AuthService authService;

    private final Application application;
    private final AuthView authView;
    private final SignInUI signInUI;
    private final SignUpUI signUpUI;
    private Result result;

    public AuthController(AuthService authService, AuthView authView, Application application) {
        this.authService = authService;
        this.authView = authView;
        this.application = application;

        signInUI = authView.getSignInForm();
        signUpUI = authView.getSignUpForm();

        attachListeners();
    }

    public void attachListeners() {
        // sign up
        signUpUI.handleLinkClick(this::handleChangeView);
        signUpUI.handleButtonClick(this::handleSignUpClick);

        // sign in
        signInUI.handleLinkClick(this::handleChangeView);
        signInUI.handleButtonClick(this::handleSignInClick);
    }

    private void handleChangeView() {
        FlatAnimatedLafChange.showSnapshot();

        authView.getCardLayout().show(authView.getContentPanel(), signInUI.isVisible() ? "signUpPanel" : "signInPanel");
        resetForms();

        FlatAnimatedLafChange.hideSnapshotWithAnimation();
    }

    private void resetForms() {
        signInUI.resetErrorMessage();
        signInUI.resetErrorInput();
        signInUI.resetFields();

        signUpUI.resetErrorMessage();
        signUpUI.resetErrorInput();
        signUpUI.resetFields();
    }

    private void handleSignInClick(ActionEvent e) {

        String username = signInUI.getUsername();
        String password = new String(signInUI.getPassword());

        authView.getContentPanel().setVisible(false);
        authView.getGlassPane().setVisible(true);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground(){

                result = authService.signIn(username, password);

                if(result.getStatus() == Result.Status.SUCCESS) {

                    SessionManager.getInstance().setCurrentUser(result.getUser());

                    application.loadApplication();
                    application.showApplication();
                }

                return null;
            }

            @Override
            protected void done() {
                authView.getGlassPane().setVisible(false);

                if (application.isApplicationLoaded()) {
                    authView.dispose();
                    application.showApplication();

                } else {
                    authView.getContentPanel().setVisible(true);
                    signInUI.showErrorMessage(result.getErrorMessage());
                }
            }
        };
        worker.execute();

    }

    private void handleSignUpClick(ActionEvent e) {

        String username = signUpUI.getUsername();
        String password = new String(signUpUI.getPassword());

        boolean usernameIsEmpty = username.isEmpty();
        boolean passwordIsEmpty = password.isEmpty();

        if(usernameIsEmpty || passwordIsEmpty) {
            signUpUI.showErrorMessage("Fields cannot be empty!");
            signUpUI.showErrorInput(usernameIsEmpty, usernameIsEmpty);
            return;
        }

        authView.getContentPanel().setVisible(false);
        authView.getGlassPane().setVisible(true);


        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {

                Result result = authService.signUp(username, password);
                return null;
            }

            @Override
            protected void done() {
                authView.getGlassPane().setVisible(false);
                authView.getContentPanel().setVisible(true);
                authView.getCardLayout().show(authView.getContentPanel(), "signInPanel");
            }
        };
        worker.execute();

    }
}
