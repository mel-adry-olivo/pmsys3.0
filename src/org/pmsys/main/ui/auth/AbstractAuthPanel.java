package org.pmsys.main.ui.auth;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.ui.components.constants.ColorConstants;
import org.pmsys.main.managers.ActionManager;
import org.pmsys.main.entities.request.AuthRequest;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.components.base.CComponent;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractAuthPanel extends CPanel implements CComponent {

    protected boolean isLogin;

    private AuthWindow authWindow;

    private CLabel linkLabel;
    private CLabel messageLabel;
    protected CTextField usernameField;
    protected CPasswordField passwordField;
    protected CButton button;

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
        ActionManager.executeAction(Actions.SWITCH_AUTH, (CLabel) e.getSource(), this);
    }

    void handleButtonClick(ActionEvent e) {
        ActionManager.executeAction(getAction(), (CButton) e.getSource(), this);
    }

    public final void resetForm() {
        usernameField.setText("");
        passwordField.setText("");

        messageLabel.setText("This is a message label");
        messageLabel.setForegroundColor(ColorConstants.WHITE).applyFlatStyle();

        usernameField.setBorderColor("darken(#ffffff, 5%)")
                .applyStyles();
        passwordField.setBorderColor("darken(#ffffff, 5%)")
                .applyStyles();
    }

    public final void showErrorMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setForegroundColor(ColorConstants.ERROR).applyFlatStyle();
    }


    public final void showErrorInput(boolean username, boolean password) {
        if(username) {
            usernameField
                    .setBorderColor(ColorConstants.ERROR)
                    .applyStyles();
        }

        if(password) {
            passwordField
                    .setBorderColor(ColorConstants.ERROR)
                    .applyStyles();
        }
    }

    private void setupTitles() {
        CLabel titleLabel = CLabelFactory.createScaledH1Label(getTitle());
        CLabel subtitleLabel = CLabelFactory.createDefaultLabel(getSubtitle());

        add(titleLabel, "wrap, growx");
        add(subtitleLabel, "wrap, growx");
    }
    private void setupInput() {
        messageLabel = CLabelFactory.createDefaultLabel("This is a message label", ColorConstants.WHITE);
        CLabel usernameLabel = CLabelFactory.createDefaultLabel("Username", ColorConstants.DARK_GREY);
        CLabel passwordLabel = CLabelFactory.createDefaultLabel("Password", ColorConstants.DARK_GREY);

        usernameField = CFieldFactory.createTextField("Enter your username");
        passwordField = CFieldFactory.createPasswordField("Enter your password");

        add(messageLabel, "h 0%, wrap, growx");

        add(usernameLabel, "wrap, growx");
        add(usernameField, "wrap, growx");

        add(passwordLabel, "wrap, growx");
        add(passwordField, "wrap, growx");
    }
    private void setupButton() {
        button = CButtonFactory.createFilledButton(getButtonName());
        button.addActionListener(this::handleButtonClick);
        add(button, "wrap, growx");
    }
    private void setupQuestion() {
        CLabel questionLabel = CLabelFactory.createDefaultLabel(getQuestion());
        linkLabel = CLabelFactory.createLinkLabel(getLink());
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
