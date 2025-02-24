package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import it.unipv.ingsfw.aga.view.Navbar;

/**
 * Classe che rappresenta la pagina per l'aggiunta di un membro dello staff.
 * Fornisce un'interfaccia grafica per inserire il codice fiscale, la password, l'email,
 * il nome e il cognome del nuovo staff.
 */
public class AggiungiStaffPage extends JPanel {
    private JTextField cfField, emailField, nameField, surnameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private Navbar navbar;

    /**
    *
     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
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
        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel surnameLabel = new JLabel("Cognome:");
        surnameField = new JTextField();
        surnameField.setFont(new Font("Arial", Font.PLAIN, 14));
        surnameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Invia");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.LIGHT_GRAY);
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
        panel.add(new JLabel()); // Spazio vuoto
        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Restituisce il codice fiscale inserito.
     *
     * @return il codice fiscale come stringa
     */
    public String getCf() {
        return cfField.getText();
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
     * Restituisce l'email inserita.
     *
     * @return l'email come stringa
     */
    public String getEmail() {
        return emailField.getText();
    }

    /**
     * Restituisce il nome inserito.
     *
     * @return il nome come stringa
     */
    public String getName() {
        return nameField.getText();
    }

    /**
     * Restituisce il cognome inserito.
     *
     * @return il cognome come stringa
     */
    public String getSurname() {
        return surnameField.getText();
    }

    /**
     * Restituisce il bottone per l'invio dei dati dello staff.
     *
     * @return il bottone submit
     */
    public JButton getSubmitButton() {
        return submitButton;
    }
}

