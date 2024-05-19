package org.pmsys.main.ui.auth;

import org.pmsys.main.actions.Actions;
import org.pmsys.constants.AppColors;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.entities.request.AuthRequest;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.views.UIView;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractAuthPanel extends FlatPanel implements UIView {

    protected boolean isLogin;

    private AuthWindow authWindow;

    private FlatLabel linkLabel;
    private FlatLabel messageLabel;
    protected FlatTextField usernameField;
    protected FlatPasswordField passwordField;
    protected FlatButton button;

    public AbstractAuthPanel(AuthWindow authWindow, boolean isLogin) {
        super("insets 10% 14% 10% 12%, fillx", "[]", "[]2%[]2%[]2%[]2%[]4%[]2%[]8%[]4%[]");
        this.authWindow = authWindow;
        this.isLogin = isLogin;
        setupTitles();
        setupInput();
        setupButton();
        setupQuestion();
    }

    public AuthRequest getAuthRequest() {
        String username = getUsername();
        String password = new String(getPassword());

        if(username.isEmpty() || password.isEmpty()) {
            showErrorMessage("Fields cannot be empty!");
            showErrorInput(username.isEmpty(), password.isEmpty());
            return null;
        }
        return new AuthRequest(username, password);
    }


    public AuthWindow getAuthWindow() {
        return authWindow;
    }

    abstract String getTitle();
    abstract String getSubtitle();
    abstract String getButtonName();
    abstract String getQuestion();
    abstract String getLink();
    public abstract Actions getAction();

    public String getUsername() {
        return usernameField.getText();
    }
    public char[] getPassword() {
        return passwordField.getPassword();
    }

    void handleLinkClick(MouseEvent e) {
        ActionManager.executeAction(Actions.SWITCH_AUTH, (FlatLabel) e.getSource(), this);
    }

    void handleButtonClick(ActionEvent e) {
        ActionManager.executeAction(getAction(), (FlatButton) e.getSource(), this);
    }

    public final void resetForm() {
        usernameField.setText("");
        passwordField.setText("");

        messageLabel.setText("This is a message label");
        messageLabel.setForegroundColor(AppColors.WHITE).applyFlatStyle();

        usernameField.setBorderColor("darken(#ffffff, 5%)")
                .applyFlatStyle();
        passwordField.setBorderColor("darken(#ffffff, 5%)")
                .applyFlatStyle();
    }

    public final void showErrorMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setForegroundColor(AppColors.ERROR).applyFlatStyle();
    }


    public final void showErrorInput(boolean username, boolean password) {
        if(username) {
            usernameField
                    .setBorderColor(AppColors.ERROR)
                    .applyFlatStyle();
        }

        if(password) {
            passwordField
                    .setBorderColor(AppColors.ERROR)
                    .applyFlatStyle();
        }
    }

    private void setupTitles() {
        FlatLabel titleLabel = FlatLabelFactory.createScaledH1Label(getTitle());
        FlatLabel subtitleLabel = FlatLabelFactory.createDefaultLabel(getSubtitle());

        add(titleLabel, "wrap, growx");
        add(subtitleLabel, "wrap, growx");
    }
    private void setupInput() {
        messageLabel = FlatLabelFactory.createDefaultLabel("This is a message label", AppColors.WHITE);
        FlatLabel usernameLabel = FlatLabelFactory.createDefaultLabel("Username", AppColors.DARK_GREY);
        FlatLabel passwordLabel = FlatLabelFactory.createDefaultLabel("Password", AppColors.DARK_GREY);

        usernameField = FlatFieldFactory.createTextField("Enter your username");
        passwordField = FlatFieldFactory.createPasswordField("Enter your password");

        add(messageLabel, "h 0%, wrap, growx");

        add(usernameLabel, "wrap, growx");
        add(usernameField, "wrap, growx");

        add(passwordLabel, "wrap, growx");
        add(passwordField, "wrap, growx");
    }
    private void setupButton() {
        button = FlatButtonFactory.createFilledButton(getButtonName());
        button.addActionListener(this::handleButtonClick);
        add(button, "wrap, growx");
    }
    private void setupQuestion() {
        FlatLabel questionLabel = FlatLabelFactory.createDefaultLabel(getQuestion());
        linkLabel = FlatLabelFactory.createLinkLabel(getLink());
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleLinkClick(e);
            }
        });

        add(questionLabel, "al center, split 2");
        add(linkLabel, "al center, wrap");
    }
}
