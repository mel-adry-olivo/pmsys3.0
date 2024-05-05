package org.pmsys.main.ui.views;

import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.forms.SignInUI;
import org.pmsys.main.ui.forms.SignUpUI;

import javax.swing.*;
import java.awt.*;

public class AuthView extends JFrame{

    private FlatPanel contentPanel;
    private CardLayout cardLayout;
    private SignInUI signInUI;
    private SignUpUI signUpUI;

    public AuthView() {
        setupView();
        setupForms();
    }

    private void setupForms() {
        contentPanel.add(signInUI = new SignInUI(), "signInPanel");
        contentPanel.add(signUpUI = new SignUpUI(), "signUpPanel");
    }

    public void setupView() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sign in");
        setResizable(false);
        setSize(450, 520);
        setLocationRelativeTo(null);
        setVisible(true);
        setGlassPane(FlatLoadingSpinner.getInstance());

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

    public SignInUI getSignInForm() {
        return signInUI;
    }

    public SignUpUI getSignUpForm() {
        return signUpUI;
    }

}
