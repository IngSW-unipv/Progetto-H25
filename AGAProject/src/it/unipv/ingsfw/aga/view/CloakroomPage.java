package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che rappresenta la pagina del guardaroba per la gestione
 * della consegna e restituzione degli oggetti. Fornisce un'interfaccia grafica
 * per inserire il codice dell'oggetto ed eseguire le operazioni di consegna e restituzione.
 */
public class CloakroomPage extends JPanel {
    private JTextField itemCodeField;
    private JButton deliverButton, returnButton;
    private Navbar navbar;

    /**
     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
    public CloakroomPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        // Inizializza la navbar e la aggiunge nella parte alta del pannello
        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        // Pannello centrale contenente il campo di testo e i bottoni
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));

        // Etichetta per il QrCode
        JLabel itemCodeLabel = new JLabel("QrCode:");
        itemCodeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(itemCodeLabel);

        // Campo di testo per l'inserimento del codice QrCode
        itemCodeField = new JTextField();
        itemCodeField.setFont(new Font("Arial", Font.PLAIN, 14));
        itemCodeField.setBackground(Color.WHITE);
        itemCodeField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(itemCodeField);

        // Pannello separato per i bottoni centrati
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Bottone per la consegna degli oggetti
        deliverButton = new JButton("Consegna");
        deliverButton.setFont(new Font("Arial", Font.BOLD, 14));
        deliverButton.setBackground(Color.LIGHT_GRAY);
        deliverButton.setForeground(Color.BLACK);
        deliverButton.setPreferredSize(new Dimension(120, 35));
        buttonPanel.add(deliverButton);

        // Bottone per la restituzione degli oggetti
        returnButton = new JButton("Restituisci");
        returnButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnButton.setBackground(Color.LIGHT_GRAY);
        returnButton.setForeground(Color.BLACK);
        returnButton.setPreferredSize(new Dimension(120, 35));
        buttonPanel.add(returnButton);

        // Aggiunge un elemento vuoto e il pannello dei bottoni al pannello principale
        panel.add(new JLabel());
        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Restituisce il bottone per la consegna degli oggetti.
     *
     * @return il bottone "Consegna"
     */
    public JButton getDeliverButton() {
        return deliverButton;
    }

    /**
     * Restituisce il bottone per la restituzione degli oggetti.
     *
     * @return il bottone "Restituisci"
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Restituisce il codice inserito nel campo di testo per il QrCode.
     *
     * @return il codice come stringa
     */
    public String getItemCode() {
        return itemCodeField.getText();
    }
}
