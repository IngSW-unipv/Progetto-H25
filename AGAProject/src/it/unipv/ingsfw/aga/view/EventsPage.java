
package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

public class EventsPage extends JPanel {
    private JButton createEventButton;
    private Navbar navbar;
    private JPanel eventsPanel;

    public EventsPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        // Titolo
        JLabel titleLabel = new JLabel("I tuoi eventi:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Panello scorrevole per gli eventi
        eventsPanel = new JPanel();
        eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS)); // Layout verticale
        JScrollPane scrollPane = new JScrollPane(eventsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Pannello per il bottone "Crea Evento"
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        createEventButton = new JButton("Crea Evento");
        createEventButton.setFont(new Font("Arial", Font.BOLD, 14));
        createEventButton.setBackground(Color.lightGray);
        createEventButton.setForeground(Color.BLACK);
        createEventButton.setPreferredSize(new Dimension(120, 35));
        buttonPanel.add(createEventButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JButton getCreateEventButton() {
        return createEventButton;
    }

    // Metodo vuoto per l'aggiornamento della lista degli eventi
    public void updateEventsList() {
        // Metodo per aggiungere eventi al pannello scrollabile
    }
}
