package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;

public class AddEventPage extends JPanel {
    private JTextField eventDateField, eventLocationField, eventCapacityField;
    private JButton submitButton;
    private Navbar navbar;
    private PersistenceFacade persistence;

    public AddEventPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // Spaziatura tra i componenti

        eventDateField = new JTextField();
        eventDateField.setFont(new Font("Arial", Font.BOLD, 14));
        eventDateField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Data:"));
        panel.add(eventDateField);

        eventLocationField = new JTextField();
        eventLocationField.setFont(new Font("Arial", Font.BOLD, 14));
        eventLocationField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Luogo:"));
        panel.add(eventLocationField);

        eventCapacityField = new JTextField();
        eventCapacityField.setFont(new Font("Arial", Font.BOLD, 14));
        eventCapacityField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Capienza:"));
        panel.add(eventCapacityField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Invia");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.lightGray);
        submitButton.setForeground(Color.BLACK);
        buttonPanel.add(submitButton);

        panel.add(new JLabel());
        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);

        // Aggiungi l'ActionListener direttamente alla view
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ottieni i dati dai campi di testo
                String date = getEventDate();
                String location = getEventLocation();
                String capacity = getEventCapacity();

                // Utilizza PersistenceFacade per salvare l'evento
                persistence = PersistenceFacade.getInstance();
                boolean result = persistence.addEvento(date, location, capacity);
                if (result) {
                    JOptionPane.showMessageDialog(null, "Evento aggiunto con successo!");
                } else {
                    JOptionPane.showMessageDialog(null, "Errore nell'aggiunta dell'evento");
                }
            }
        });
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public String getEventDate() {
        return eventDateField.getText();
    }

    public String getEventLocation() {
        return eventLocationField.getText();
    }

    public String getEventCapacity() {
        return eventCapacityField.getText();
    }
}