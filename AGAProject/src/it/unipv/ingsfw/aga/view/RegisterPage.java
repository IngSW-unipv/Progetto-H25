package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che rappresenta la pagina di registrazione.
 * Fornisce un'interfaccia grafica per l'inserimento dei dati necessari alla registrazione di un nuovo utente.
 */
public class RegisterPage extends JPanel {
    private JTextField cfField, emailField, nameField, surnameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private Navbar navbar;

    /**
     
     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
    public RegisterPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());
        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Creazione e impostazione dei componenti per il Codice Fiscale
        JLabel cfLabel = new JLabel("Codice Fiscale:");
        cfField = new JTextField();
        cfField.setFont(new Font("Arial", Font.PLAIN, 14));
        cfField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Creazione e impostazione dei componenti per l'Email
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Creazione e impostazione dei componenti per la Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Creazione e impostazione dei componenti per il Nome
        JLabel nameLabel = new JLabel("Nome:");
        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Creazione e impostazione dei componenti per il Cognome
        JLabel surnameLabel = new JLabel("Cognome:");
        surnameField = new JTextField();
        surnameField.setFont(new Font("Arial", Font.PLAIN, 14));
        surnameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Etichetta per il GDPR (informativa gestione dati)
        JLabel gpdrLabel = new JLabel("Andando avanti, accetti la gestione dei tuoi dati");
        gpdrLabel.setFont(new Font("Arial", Font.PLAIN, 6));

        // Creazione e impostazione del bottone di invio della registrazione
        submitButton = new JButton("Registrati");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.LIGHT_GRAY);
        submitButton.setForeground(Color.BLACK);

        // Aggiunta dei componenti al pannello
        panel.add(cfLabel);
        panel.add(cfField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(new JLabel()); // Linea vuota per padding
        panel.add(gpdrLabel);
        panel.add(submitButton);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Restituisce il Codice Fiscale inserito.
     *
     * @return il Codice Fiscale come stringa
     */
    public String getCf() {
        return cfField.getText();
    }

    /**
     * Restituisce l'Email inserita.
     *
     * @return l'Email come stringa
     */
    public String getEmail() {
        return emailField.getText();
    }

    /**
     * Restituisce la Password inserita.
     *
     * @return la Password come stringa
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Restituisce il Nome inserito.
     *
     * @return il Nome come stringa
     */
    public String getName() {
        return nameField.getText();
    }

    /**
     * Restituisce il Cognome inserito.
     *
     * @return il Cognome come stringa
     */
    public String getSurname() {
        return surnameField.getText();
    }

    /**
     * Restituisce il bottone per inviare la registrazione.
     *
     * @return il bottone "Registrati"
     */
    public JButton getSubmitButton() {
        return submitButton;
    }
}