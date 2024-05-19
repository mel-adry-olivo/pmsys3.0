package org.pmsys.main.ui.forms;

import org.pmsys.constants.AppColors;
import org.pmsys.main.ui.components.base.*;
import org.pmsys.main.ui.listeners.LinkClickListener;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class AbstractAuthUI extends FlatPanel {

    private FlatLabel linkLabel;
    private FlatLabel messageLabel;
    protected FlatTextField usernameField;
    protected FlatPasswordField passwordField;
    protected FlatButton button;

    public AbstractAuthUI() {
        super("insets 10% 14% 10% 12%, fillx", "[]", "[]2%[]2%[]2%[]2%[]4%[]2%[]8%[]4%[]");
        setupTitles();
        setupInput();
        setupButton();
        setupQuestion();
    }


    abstract String getTitle();
    abstract String getSubtitle();
    abstract String getButtonName();
    abstract String getQuestion();
    abstract String getLink();

    public abstract String getUsername();
    public abstract char[] getPassword();

    public final void handleLinkClick(LinkClickListener listener) {
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                listener.onClick();
            }
        });
    }

    public final void handleButtonClick(ActionListener listener) {
        button.addActionListener(listener);
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
        add(button, "wrap, growx");
    }
    private void setupQuestion() {
        FlatLabel questionLabel = FlatLabelFactory.createDefaultLabel(getQuestion());
        linkLabel = FlatLabelFactory.createLinkLabel(getLink());

        add(questionLabel, "al center, split 2");
        add(linkLabel, "al center, wrap");
    }
}
