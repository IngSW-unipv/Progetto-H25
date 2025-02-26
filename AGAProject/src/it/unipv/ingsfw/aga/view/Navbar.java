package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe che rappresenta la Navbar dell'applicazione.
 * La Navbar contiene pulsanti per navigare alla Home e per eseguire il Logout,
 * oltre a mostrare il logo dell'applicazione.
 */
public class Navbar extends JPanel {
    private JButton homeButton;
    private JButton logoutButton;

    /**
     *
     * @param cardLayout     il CardLayout utilizzato per il cambio delle pagine
     * @param containerPanel il pannello contenitore in cui risiedono le pagine
     */
    public Navbar(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        homeButton = new JButton("Home");
        homeButton.setForeground(Color.BLACK);
        homeButton.setBackground(Color.ORANGE);
        homeButton.setFont(new Font("Arial", Font.BOLD, 12));
        homeButton.setFocusPainted(false);

        logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setFocusPainted(false);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(Color.BLACK);
        leftPanel.add(homeButton);
        leftPanel.add(logoutButton);
        add(leftPanel, BorderLayout.WEST);

        // Carica e ridimensiona il logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/LogoAga.jpeg"));
        Image image = logoIcon.getImage();
        Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 20));
        add(logoLabel, BorderLayout.EAST);

     // ActionListener per il pulsante "Home"
        getHomeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "main");
            }
        });

        // ActionListener per il pulsante "Logout"
        getLogoutButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "login");
            }
        });
    }

    /**
     * Restituisce il pulsante "Home".
     *
     * @return il pulsante Home
     */
    public JButton getHomeButton() {
        return homeButton;
    }

    /**
     * Restituisce il pulsante "Logout".
     *
     * @return il pulsante Logout
     */
    public JButton getLogoutButton() {
        return logoutButton;
    }
}
