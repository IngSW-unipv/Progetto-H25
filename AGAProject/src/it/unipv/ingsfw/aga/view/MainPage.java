package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che rappresenta la pagina principale dell'applicazione.
 * Visualizza i pulsanti per accedere alle diverse funzionalità:
 * - Aggiungi invitato
 * - Ingresso
 * - Guardaroba
 * - Modifica Vendite
 * - Aggiungi Staff
 * - Vedi Invitati
 * 
 */
public class MainPage extends JPanel {
    private JButton addGuestButton, entranceButton, cloakroomButton;
    private JButton modificaVenditeButton, aggiungiStaffButton, vediInvitatiButton;
    private Navbar navbar;

    /**

     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
    public MainPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        // Inizializza la Navbar e la aggiunge nella parte superiore della pagina
        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        // Pannello centrale contenente i pulsanti delle varie funzionalità
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        addGuestButton = new JButton("Aggiungi invitato");
        entranceButton = new JButton("Ingresso");
        cloakroomButton = new JButton("Guardaroba");
        modificaVenditeButton = new JButton("Modifica Vendite");
        aggiungiStaffButton = new JButton("Aggiungi Staff");
        vediInvitatiButton = new JButton("Vedi Invitati");

        panel.add(addGuestButton);
        panel.add(entranceButton);
        panel.add(cloakroomButton);
        panel.add(modificaVenditeButton);
        panel.add(aggiungiStaffButton);
        panel.add(vediInvitatiButton);

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Restituisce il pulsante "Aggiungi invitato".
     *
     * @return il pulsante per aggiungere un invitato
     */
    public JButton getAddGuestButton() {
        return addGuestButton;
    }

    /**
     * Restituisce il pulsante "Ingresso".
     *
     * @return il pulsante per l'ingresso
     */
    public JButton getEntranceButton() {
        return entranceButton;
    }

    /**
     * Restituisce il pulsante "Guardaroba".
     *
     * @return il pulsante per il guardaroba
     */
    public JButton getCloakroomButton() {
        return cloakroomButton;
    }

    /**
     * Restituisce il pulsante "Modifica Vendite".
     *
     * @return il pulsante per la modifica delle vendite
     */
    public JButton getModificaVenditeButton() {
        return modificaVenditeButton;
    }

    /**
     * Restituisce il pulsante "Aggiungi Staff".
     *
     * @return il pulsante per aggiungere un membro dello staff
     */
    public JButton getAggiungiStaffButton() {
        return aggiungiStaffButton;
    }

    /**
     * Restituisce il pulsante "Vedi Invitati".
     *
     * @return il pulsante per la visualizzazione degli invitati
     */
    public JButton getVediInvitatiButton() {
        return vediInvitatiButton;
    }

    /**
     * Imposta i permessi relativi al ruolo dell'utente.
     * Se l'utente non è un organizzatore, i pulsanti Modifica Vendite, Aggiungi Staff, Vedi Invitati verranno nascosti.
     *
     * @param isOrganizer true se l'utente è un organizzatore, false se è uno staff
     */
    public void setRolePermissions(boolean isOrganizer) {
        modificaVenditeButton.setVisible(isOrganizer);
        aggiungiStaffButton.setVisible(isOrganizer);
        vediInvitatiButton.setVisible(isOrganizer);
    }
}
