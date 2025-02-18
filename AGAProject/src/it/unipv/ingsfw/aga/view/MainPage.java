package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JPanel {
    private JButton addGuestButton, entranceButton, cloakroomButton;
    private JButton modificaVenditeButton, aggiungiStaffButton, vediInvitatiButton;
    private Navbar navbar;

    public MainPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);


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


    public JButton getAddGuestButton() {
        return addGuestButton;
    }

    public JButton getEntranceButton() {
        return entranceButton;
    }

    public JButton getCloakroomButton() {
        return cloakroomButton;
    }

    public JButton getModificaVenditeButton() {
        return modificaVenditeButton;
    }

    public JButton getAggiungiStaffButton() {
        return aggiungiStaffButton;
    }

    public JButton getVediInvitatiButton() {
        return vediInvitatiButton;
    }

    /**
     * Imposta i permessi per il ruolo dell'utente.
     * Se il ruolo è "staff", nasconde i pulsanti extra (organizzatore).
     *
     * @param role il ruolo dell'utente ("staff" o "organizer")
     */
    public void setRolePermissions(boolean isOrganizer) {
        modificaVenditeButton.setVisible(isOrganizer);
        aggiungiStaffButton.setVisible(isOrganizer);
        vediInvitatiButton.setVisible(isOrganizer);
    }

    // Se si utilizza un enum per il ruolo, il metodo potrebbe essere:
    // public void setRolePermissions(User.Role role) {
    //     boolean isOrganizer = role != User.Role.STAFF;
    //     modificaVenditeButton.setVisible(isOrganizer);
    //     aggiungiStaffButton.setVisible(isOrganizer);
    //     vediInvitatiButton.setVisible(isOrganizer);
    // }
}
