package org.pmsys.main.controller;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import org.pmsys.main.Application;
import org.pmsys.main.entity.Result;
import org.pmsys.main.manager.SessionManager;
import org.pmsys.main.service.AuthService;
import org.pmsys.main.ui.forms.SignInUI;
import org.pmsys.main.ui.forms.SignUpUI;
import org.pmsys.main.ui.windows.AuthWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AuthController {

    private final AuthService authService;

    private final Application application;
    private final AuthWindow authWindow;
    private final SignInUI signInUI;
    private final SignUpUI signUpUI;
    private Result result;

    public AuthController(AuthService authService,AuthWindow authWindow, Application application) {
        this.authService = authService;
        this.authWindow = authWindow;
        this.application = application;

        signInUI = authWindow.getSignInForm();
        signUpUI = authWindow.getSignUpForm();

        attachListeners();
    }

    private void attachListeners() {
        // sign up
        signUpUI.handleLinkClick(this::handleChangeView);
        signUpUI.handleButtonClick(this::handleSignUpClick);

        // sign in
        signInUI.handleLinkClick(this::handleChangeView);
        signInUI.handleButtonClick(this::handleSignInClick);
    }

    private void handleChangeView() {
        FlatAnimatedLafChange.showSnapshot();

        authWindow.getCardLayout().show(authWindow.getContentPanel(), signInUI.isVisible() ? "signUpPanel" : "signInPanel");
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

        authWindow.getContentPanel().setVisible(false);
        authWindow.getGlassPane().setVisible(true);

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
                authWindow.getGlassPane().setVisible(false);

                if (application.isApplicationLoaded()) {
                    authWindow.dispose();
                    application.showApplication();

                } else {
                    authWindow.getContentPanel().setVisible(true);
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

        authWindow.getContentPanel().setVisible(false);
        authWindow.getGlassPane().setVisible(true);


        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {

                Result result = authService.signUp(username, password);
                return null;
            }

            @Override
            protected void done() {
                authWindow.getGlassPane().setVisible(false);
                authWindow.getContentPanel().setVisible(true);
                authWindow.getCardLayout().show(authWindow.getContentPanel(), "signInPanel");
            }
        };
        worker.execute();

    }
}
