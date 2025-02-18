package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Navbar extends JPanel {
    private JButton homeButton;
    private JButton logoutButton;

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
        //leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10,0));
        add(leftPanel, BorderLayout.WEST);


        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/LogoAga.jpeg"));
        Image image = logoIcon.getImage();
        Image scaledImage = image.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaledImage);

        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 20));

        add(logoLabel, BorderLayout.EAST);

        //Non li inserisco in Controller in quanto questa è una classe fortemente stabile
        // ActionListener per il bottone "Home" nella navbar
        getHomeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "main");
            }
        });

        // ActionListener per il bottone "Logout" nella navbar
        getLogoutButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "login");
            }
        });



    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
