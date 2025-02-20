package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;

public class LoginPage extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginPage() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 righe e 2 colonne

        //Dimension textFieldSize = new Dimension(200, 30); // Dimensione uniforme per i campi di input

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.BOLD, 14));
        //usernameField.setPreferredSize(textFieldSize);
        usernameField.setBackground(Color.white);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 14));
        //passwordField.setPreferredSize(textFieldSize);
        passwordField.setBackground(Color.white); // Sfondo bianco per i campi di password
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(passwordField);

        // Pannello separato per i bottoni
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Bottone "Login"
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.lightGray); // Bottone con lo stesso colore dello sfondo
        loginButton.setForeground(Color.BLACK);
        // loginButton.setPreferredSize(new Dimension(120, 35)); // Dimensione del bottone
        buttonPanel.add(loginButton);

        // Bottone "Registrati"
        registerButton = new JButton("Registrati");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(Color.lightGray); // Bottone con lo stesso colore dello sfondo
        registerButton.setForeground(Color.BLACK);
        // registerButton.setPreferredSize(new Dimension(120, 35)); // Dimensione del bottone
        buttonPanel.add(registerButton);

        panel.add(new JLabel());
        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
    
}
