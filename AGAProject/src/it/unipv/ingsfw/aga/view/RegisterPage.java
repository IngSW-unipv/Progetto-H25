package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends JPanel {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton submitButton;

    public RegisterPage() {
        setLayout(new GridLayout(4, 2, 10, 10));

        // Dimensione uniforme per i campi di input
        // Dimension textFieldSize = new Dimension(200, 30);

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        // usernameField.setPreferredSize(textFieldSize);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        // passwordField.setPreferredSize(textFieldSize);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        // emailField.setPreferredSize(textFieldSize);
        emailField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        submitButton = new JButton("Registrati");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.lightGray);
        submitButton.setForeground(Color.BLACK);


        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(emailLabel);
        add(emailField);
        add(new JLabel()); // Empty space
        add(submitButton);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getEmail() {
        return emailField.getText();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
}
