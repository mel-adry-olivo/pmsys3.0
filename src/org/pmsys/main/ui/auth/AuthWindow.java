package org.pmsys.main.ui.auth;

import org.pmsys.main.ui.components.base.CComponent;
import org.pmsys.main.ui.components.base.*;

import javax.swing.*;
import java.awt.*;

public class AuthWindow extends JFrame implements CComponent {

    private CardLayout cardLayout;

    public  AuthWindow() {
        setupView();
        setupForms();
    }

    private void setupForms() {
        AuthPanel loginForm = new AuthPanel(this, true);
        AuthPanel registerForm = new AuthPanel(this, false);

        add(loginForm, "loginPanel");
        add(registerForm, "registerPanel");
    }

    public void toggleLoadingState(boolean isLoading) {
        getContentPane().setVisible(!isLoading);
        getGlassPane().setVisible(isLoading);
    }

    public void setupView() {
        setTitle("Sign in");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(450, 520);
        setLocationRelativeTo(null);
        setGlassPane(new CLoadingIcon());
        setLayout(cardLayout = new CardLayout());
        setVisible(true);
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }
}
