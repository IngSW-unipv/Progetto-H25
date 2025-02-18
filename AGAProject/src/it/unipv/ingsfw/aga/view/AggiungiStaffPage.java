package it.unipv.ingsfw.aga.view;

import javax.swing.*;
        import java.awt.*;
        import it.unipv.ingsfw.aga.view.Navbar;

public class AggiungiStaffPage extends JPanel {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private Navbar navbar;

    public AggiungiStaffPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());
        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        submitButton = new JButton("Aggiungi");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.lightGray);
        submitButton.setForeground(Color.BLACK);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(new JLabel()); // Empty space
        panel.add(submitButton);

        add(panel, BorderLayout.CENTER);
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

