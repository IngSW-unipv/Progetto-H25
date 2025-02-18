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
}
