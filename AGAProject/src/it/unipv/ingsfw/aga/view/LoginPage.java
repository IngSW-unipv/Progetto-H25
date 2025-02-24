package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;

/**
 * Classe che rappresenta la pagina di login.
 * Fornisce un'interfaccia grafica per l'inserimento delle credenziali utente.
 */
public class LoginPage extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;


    public LoginPage() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 righe e 2 colonne

        // Etichetta per lo username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(usernameLabel);

        // Campo di testo per lo username
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.BOLD, 14));
        usernameField.setBackground(Color.WHITE);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(usernameField);

        // Etichetta per la password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(passwordLabel);

        // Campo di testo per la password
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 14));
        passwordField.setBackground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(passwordField);

        // Pannello per i bottoni di login e registrazione
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Bottone "Login"
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.LIGHT_GRAY);
        loginButton.setForeground(Color.BLACK);
        buttonPanel.add(loginButton);

        // Bottone "Registrati"
        registerButton = new JButton("Registrati");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(Color.LIGHT_GRAY);
        registerButton.setForeground(Color.BLACK);
        buttonPanel.add(registerButton);

        // Aggiunge un elemento vuoto e il pannello dei bottoni
        panel.add(new JLabel());
        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Restituisce il nome utente inserito.
     *
     * @return il nome utente come stringa
     */
    public String getUsername() {
        return usernameField.getText();
    }

    /**
     * Restituisce la password inserita.
     *
     * @return la password come stringa
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Restituisce il bottone di login.
     *
     * @return il bottone "Login"
     */
    public JButton getLoginButton() {
        return loginButton;
    }

    /**
     * Restituisce il bottone per la registrazione.
     *
     * @return il bottone "Registrati"
     */
    public JButton getRegisterButton() {
        return registerButton;
    }
}
