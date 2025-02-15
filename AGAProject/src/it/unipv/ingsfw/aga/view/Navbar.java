package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Navbar extends JPanel {
    private JButton homeButton;
    private JButton logoutButton;

    public Navbar(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.BLACK);

        homeButton = new JButton("Home");
        homeButton.setForeground(Color.BLACK);
        homeButton.setBackground(Color.ORANGE);
        // homeButton.setPreferredSize(new Dimension(80, 30));
        homeButton.setFont(new Font("Arial", Font.BOLD, 12));
        homeButton.setFocusPainted(false);

        logoutButton = new JButton("Logout");
        logoutButton.setForeground(Color.BLACK);
        // logoutButton.setPreferredSize(new Dimension(80, 30));
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setFocusPainted(false);

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "main");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "login");
            }
        });

        add(homeButton);
        add(logoutButton);
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
