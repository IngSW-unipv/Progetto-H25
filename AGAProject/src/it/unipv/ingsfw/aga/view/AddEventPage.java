package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;

/**
 * Classe che rappresenta la pagina per l'aggiunta di un evento.
 * Fornisce un'interfaccia grafica per inserire la data, il luogo e la capienza dell'evento.
 */
public class AddEventPage extends JPanel {
    private JTextField eventDateField, eventLocationField, eventCapacityField, maxGrucceField;
    private JButton submitButton;
    private Navbar navbar;
    private PersistenceFacade persistence;

    /**
     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
    public AddEventPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // Spaziatura tra i componenti

        eventDateField = new JTextField();
        eventDateField.setFont(new Font("Arial", Font.BOLD, 14));
        eventDateField.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        eventLocationField = new JTextField();
        eventLocationField.setFont(new Font("Arial", Font.BOLD, 14));
        eventLocationField.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        eventCapacityField = new JTextField();
        eventCapacityField.setFont(new Font("Arial", Font.BOLD, 14));
        eventCapacityField.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        maxGrucceField = new JTextField();
        maxGrucceField.setFont(new Font("Arial", Font.BOLD, 14));
        maxGrucceField.setBorder(BorderFactory.createLineBorder(Color.BLACK));



        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Invia");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.lightGray);
        submitButton.setForeground(Color.BLACK);


        panel.add(new JLabel("Data:"));
        panel.add(eventDateField);
        panel.add(new JLabel("Luogo:"));
        panel.add(eventLocationField);
        panel.add(new JLabel("Capienza:"));
        panel.add(eventCapacityField);
        panel.add(new JLabel("Max Grucce:"));
        panel.add(maxGrucceField);
        buttonPanel.add(submitButton);
        panel.add(new JLabel());
        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Restituisce il bottone per l'invio dei dati dell'evento.
     *
     * @return il bottone submit
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Restituisce la data dell'evento inserita.
     *
     * @return la data dell'evento come stringa
     */
    public String getEventDate() {
        return eventDateField.getText();
    }

    /**
     * Restituisce il luogo dell'evento inserito.
     *
     * @return il luogo dell'evento come stringa
     */
    public String getEventLocation() {
        return eventLocationField.getText();
    }

    /**
     * Restituisce la capienza dell'evento inserita.
     *
     * @return la capienza dell'evento come stringa
     */
    public String getEventCapacity() {
        return eventCapacityField.getText();
    }

    public String getMaxGrucce() {
        return maxGrucceField.getText();
    }
}