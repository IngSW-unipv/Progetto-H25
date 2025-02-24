package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;

/**
 * Classe che rappresenta la pagina per la visualizzazione della lista degli invitati.
 * Fornisce un'interfaccia grafica in cui viene mostrata la lista degli invitati in un'area di testo
 * all'interno di un pannello scorrevole.
 */
public class ListaInvitatiPage extends JPanel {
    private JPanel guestsPanel;
    private Navbar navbar;
    private JTextArea area;

    /**
     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
    public ListaInvitatiPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        // Inizializza e aggiunge la Navbar in cima alla pagina
        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        // Pannello principale contenente il titolo e il pannello scorrevole degli invitati
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Titolo della pagina
        JLabel titleLabel = new JLabel("Lista invitati");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Pannello scorrevole per gli invitati, con layout verticale
        guestsPanel = new JPanel();
        guestsPanel.setLayout(new BoxLayout(guestsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(guestsPanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Aggiorna la lista degli invitati visualizzata nella pagina.
     * Per ogni invitato presente nell'ArrayList, viene creato un testo che lo rappresenta e
     * viene aggiunto all'area di testo del pannello.
     *
     * @param invitati Lista degli oggetti Biglietto che rappresentano gli invitati.
     */
    public void updateGuestsList(ArrayList<Biglietto> invitati) {
        // Crea una nuova area di testo con font ridimensionato
        area = new JTextArea();
        area.setFont(area.getFont().deriveFont(12f));
        guestsPanel.removeAll(); // Rimuove eventuali contenuti precedenti
        guestsPanel.add(area);

        // Costruisce la stringa da visualizzare
        StringBuilder s = new StringBuilder();
        for (Biglietto i : invitati) {
            s.append(i.toString()).append("\n");
        }
        area.setText(s.toString());

        // Aggiorna il pannello per riflettere i cambiamenti
        guestsPanel.revalidate();
        guestsPanel.repaint();
    }
}
