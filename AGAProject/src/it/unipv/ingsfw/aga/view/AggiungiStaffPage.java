package it.unipv.ingsfw.aga.view;

import javax.swing.*;
        import java.awt.*;
        import it.unipv.ingsfw.aga.view.Navbar;

public class AggiungiStaffPage extends JPanel {
    private JTextField cfField, emailField, nameField, surnameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private Navbar navbar;

    public AggiungiStaffPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());
        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel cfLabel = new JLabel("Codice Fiscale:");
        cfField = new JTextField();
        cfField.setFont(new Font("Arial", Font.PLAIN, 14));
        cfField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel nameLabel = new JLabel("Nome:");
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        // usernameField.setPreferredSize(textFieldSize);
        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel surnameLabel = new JLabel("Cognome:");
        surnameField = new JTextField();
        surnameField.setFont(new Font("Arial", Font.PLAIN, 14));
        // usernameField.setPreferredSize(textFieldSize);
        surnameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Invia");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.lightGray);
        submitButton.setForeground(Color.BLACK);
        buttonPanel.add(submitButton);

        panel.add(cfLabel);
        panel.add(cfField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(new JLabel()); // Empty space
        panel.add(submitButton);

        add(panel, BorderLayout.CENTER);
    }

    public String getCf() {
        return cfField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getEmail() {
        return emailField.getText();
    }
    public String getName() {
        return nameField.getText();
    }

    public String getSurname() {
        return surnameField.getText();
    }

    public JButton getSubmitButton() {
        return submitButton;
    }
}

