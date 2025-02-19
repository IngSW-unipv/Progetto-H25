package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

public class EventsPage extends JPanel {
    private JPanel buttonsPanel;
    private Navbar navbar;
    private JButton createEventButton;
    private EventSelectionListener eventSelectionListener; // Aggiungi il listener

    public EventsPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        // Aggiunta della Navbar
        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Titolo della pagina
        JLabel titleLabel = new JLabel("I Tuoi Eventi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panello scorrevole per i bottoni degli eventi
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS)); // Layout verticale
        JScrollPane scrollPane = new JScrollPane(buttonsPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Bottone "Crea Evento"
        createEventButton = new JButton("Crea Evento");
        createEventButton.setFont(new Font("Arial", Font.PLAIN, 16));
        createEventButton.setPreferredSize(new Dimension(200, 40));
        panel.add(createEventButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);
    }

    // Metodo per aggiungere un bottone per ogni evento (delegato al controller)
    public void addEventButton(String eventName) {
        JButton eventButton = new JButton(eventName);
        eventButton.setFont(new Font("Arial", Font.PLAIN, 16));

        // Quando l'utente clicca sul bottone evento, il listener si occupa della logica
        eventButton.addActionListener(e -> {
            if (eventSelectionListener != null) {
                eventSelectionListener.onEventSelected(eventName); // Invoca il listener
            }
        });

        buttonsPanel.add(eventButton);
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    // Setter per il listener
    public void setEventSelectionListener(EventSelectionListener listener) {
        this.eventSelectionListener = listener;
    }

    // Getter per il bottone "Crea Evento"
    public JButton getCreateEventButton() {
        return createEventButton;
    }
}
