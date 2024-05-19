package org.pmsys.main.ui.views;

import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.forms.LoginAuthForm;
import org.pmsys.main.ui.forms.RegisterAuthForm;

import javax.swing.*;
import java.awt.*;

public class AuthWindow extends JFrame{

    private FlatPanel contentPanel;
    private CardLayout cardLayout;
    private LoginAuthForm loginForm;
    private RegisterAuthForm registerForm;

    public AuthWindow() {
        setupView();
        setupForms();
    }

    private void setupForms() {
        loginForm = new LoginAuthForm();
        loginForm.setWindow(this);

        registerForm = new RegisterAuthForm();
        registerForm.setWindow(this);

        contentPanel.add(loginForm, "loginPanel");
        contentPanel.add(registerForm, "registerPanel");
    }

    public void toggleLoadingState(boolean isLoading) {
        getContentPanel().setVisible(!isLoading);
        getGlassPane().setVisible(isLoading);
    }

    public void setupView() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign in");
        setResizable(false);
        setSize(450, 520);
        setLocationRelativeTo(null);
        setVisible(true);
        setGlassPane(new FlatLoadingIcon());

        contentPanel = new FlatPanel();
        contentPanel.setLayout(cardLayout = new CardLayout());

        add(contentPanel, BorderLayout.CENTER);
    }

    public FlatPanel getContentPanel() {
        return contentPanel;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public LoginAuthForm getSignInForm() {
        return loginForm;
    }

    public RegisterAuthForm getSignUpForm() {
        return registerForm;
    }

}
