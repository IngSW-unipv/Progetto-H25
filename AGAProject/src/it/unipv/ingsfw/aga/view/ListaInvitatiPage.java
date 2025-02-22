package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

public class ListaInvitatiPage extends JPanel {
    private JPanel guestsPanel;
    private Navbar navbar;

    public ListaInvitatiPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel titleLabel = new JLabel("Lista invitati");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panello scorrevole per gli invitati
        guestsPanel = new JPanel();
        guestsPanel.setLayout(new BoxLayout(guestsPanel, BoxLayout.Y_AXIS)); // Layout verticale
        JScrollPane scrollPane = new JScrollPane(guestsPanel);
        panel.add(scrollPane, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
    }

    // Metodo vuoto per aggiornare la lista degli invitati
    public void updateGuestsList() {
        // Metodo per aggiungere gli invitati al pannello scrollabile //TODO
    }
    /*public void addEventButton(String eventName) {
        JPanel eventButton = new JButton(eventName);
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
    }*/

	
}
