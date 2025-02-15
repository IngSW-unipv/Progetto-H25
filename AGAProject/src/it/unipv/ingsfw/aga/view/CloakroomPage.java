package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;

public class CloakroomPage extends JPanel {
    private JTextField itemCodeField;
    private JButton deliverButton, returnButton;
    private Navbar navbar;

    public CloakroomPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        // Dimension textFieldSize = new Dimension(200, 30);

        JLabel itemCodeLabel = new JLabel("QrCode:");
        itemCodeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(itemCodeLabel);
        itemCodeField = new JTextField();
        itemCodeField.setFont(new Font("Arial", Font.PLAIN, 14));
        // itemCodeField.setPreferredSize(textFieldSize);
        itemCodeField.setBackground(Color.WHITE);
        itemCodeField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Da documentazione
        panel.add(itemCodeField);

        // Pannello separato per i bottoni centrati
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        deliverButton = new JButton("Consegna");
        deliverButton.setFont(new Font("Arial", Font.BOLD, 14));
        deliverButton.setBackground(Color.lightGray); // Bottone con lo stesso colore dello sfondo
        deliverButton.setForeground(Color.black);
        deliverButton.setPreferredSize(new Dimension(120, 35)); // Dimensione del botton
        buttonPanel.add(deliverButton);

        returnButton = new JButton("Restituisci");
        returnButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnButton.setBackground(Color.lightGray);
        returnButton.setForeground(Color.BLACK);
        returnButton.setPreferredSize(new Dimension(120, 35)); // Imposta dimensione bottone
        buttonPanel.add(returnButton);

        panel.add(new JLabel());
        panel.add(buttonPanel);
        add(panel, BorderLayout.CENTER);
    }

    public JButton getDeliverButton() {
        return deliverButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public String getItemCode() {
        return itemCodeField.getText();
    }

}
