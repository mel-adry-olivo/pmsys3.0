package org.pmsys.main.ui.windows;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import org.pmsys.main.Application;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.forms.SignInUI;
import org.pmsys.main.ui.forms.SignUpUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AuthWindow extends JFrame {

    private FlatPanel contentPanel;
    private CardLayout cardLayout;
    private SignInUI signInUI;
    private SignUpUI signUpUI;

    public AuthWindow() {
        setupWindow();
        setupForms();
    }

    private void setupForms() {
        contentPanel.add(signInUI = new SignInUI(), "signInPanel");
        contentPanel.add(signUpUI = new SignUpUI(), "signUpPanel");
    }

    private void setupWindow() {
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
