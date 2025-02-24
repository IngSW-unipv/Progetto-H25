package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import it.unipv.ingsfw.aga.view.Navbar;

/**
 * Classe che rappresenta la pagina per l'aggiunta di un ospite.
 * Fornisce un'interfaccia grafica per inserire il nome, il cognome e l'email dell'ospite.
 */
public class AddGuestPage extends JPanel {
    private JTextField guestNameField, guestSurnameField, guestEmailField;
    private JButton submitButton;
    private Navbar navbar;

    /**
     *
     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
    public AddGuestPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());
        // setBackground(Color.lightGray);

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // Spaziatura tra i componenti

        guestNameField = new JTextField();
        guestNameField.setFont(new Font("Arial", Font.BOLD, 14));
        guestNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Nome:"));
        panel.add(guestNameField);

        guestSurnameField = new JTextField();
        guestSurnameField.setFont(new Font("Arial", Font.BOLD, 14));
        guestSurnameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Cognome:"));
        panel.add(guestSurnameField);

        guestEmailField = new JTextField();
        guestEmailField.setFont(new Font("Arial", Font.BOLD, 14));
        guestEmailField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Email:"));
        panel.add(guestEmailField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Invia");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.lightGray);
        submitButton.setForeground(Color.BLACK);
        buttonPanel.add(submitButton);

        panel.add(new JLabel());
        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Restituisce il bottone per l'invio dei dati dell'ospite.
     *
     * @return il bottone submit
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Restituisce il nome dell'ospite inserito.
     *
     * @return il nome dell'ospite come stringa
     */
    public String getGuestName() {
        return guestNameField.getText();
    }

    /**
     * Restituisce l'email dell'ospite inserita.
     *
     * @return l'email dell'ospite come stringa
     */
    public String getGuestEmail() {
        return guestEmailField.getText();
    }

    /**
     * Restituisce il cognome dell'ospite inserito.
     *
     * @return il cognome dell'ospite come stringa
     */
    public String getGuestSurname() {
        return guestSurnameField.getText();
    }
}
