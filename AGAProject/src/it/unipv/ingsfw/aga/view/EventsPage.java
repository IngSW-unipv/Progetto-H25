package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import it.unipv.ingsfw.aga.view.Navbar;
/**
 * Classe che rappresenta la pagina degli eventi.
 * Fornisce un'interfaccia grafica per visualizzare e selezionare gli eventi esistenti,
 * nonché per creare un nuovo evento.
 */
public class EventsPage extends JPanel {
    private JPanel buttonsPanel;
    private Navbar navbar;
    private JButton createEventButton;
    private EventSelectionListener eventSelectionListener; // Listener per la selezione degli eventi

    /**
     *
     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
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

        // Pannello scorrevole per i bottoni degli eventi
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

    /**
     * Aggiunge un bottone per un evento specifico.
     * Quando il bottone viene cliccato, viene invocato il listener per la selezione dell'evento.
     *
     * @param eventName il nome dell'evento da visualizzare sul bottone
     */
    public void addEventButton(String eventName) {
        JButton eventButton = new JButton(eventName);
        eventButton.setFont(new Font("Arial", Font.PLAIN, 16));

        // Quando l'utente clicca sul bottone, il listener gestisce la logica
        eventButton.addActionListener(e -> {
            if (eventSelectionListener != null) {
                eventSelectionListener.onEventSelected(eventName);
            }
        });

        buttonsPanel.add(eventButton);
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    /**
     * Imposta il listener per la selezione degli eventi.
     *
     * @param listener l'istanza di EventSelectionListener da utilizzare
     */
    public void setEventSelectionListener(EventSelectionListener listener) {
        this.eventSelectionListener = listener;
    }

    /**
     * Restituisce il bottone "Crea Evento".
     *
     * @return il bottone utilizzato per la creazione di un nuovo evento
     */
    public JButton getCreateEventButton() {
        return createEventButton;
    }
}
