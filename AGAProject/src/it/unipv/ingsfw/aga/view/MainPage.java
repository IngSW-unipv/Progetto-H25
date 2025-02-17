package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainPage extends JPanel {
    private JButton addGuestButton, entranceButton, cloakroomButton;
    private Navbar navbar;

    public MainPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());


        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));


        addGuestButton = new JButton("Aggiungi invitato");
        entranceButton = new JButton("Ingresso");
        cloakroomButton = new JButton("Guardaroba");

        panel.add(addGuestButton);
        panel.add(entranceButton);
        panel.add(cloakroomButton);

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
}
