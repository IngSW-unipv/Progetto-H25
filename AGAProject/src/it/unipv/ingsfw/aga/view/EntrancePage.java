package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;


public class EntrancePage extends JPanel {
    private JTextField verificationField;
    private JButton verifyButton;
    private Navbar navbar;

    public EntrancePage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 10, 10));
        //Dimension textFieldSize = new Dimension(200, 30);

        JLabel verificationLabel = new JLabel("Codice Ingresso:");
        verificationLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(verificationLabel);

        verificationField = new JTextField();
        verificationField.setFont(new Font("Arial", Font.PLAIN, 14));
        // verificationField.setPreferredSize(textFieldSize);
        verificationField.setBackground(Color.white);
        verificationField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(verificationField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        verifyButton = new JButton("Verifica");
        verifyButton.setFont(new Font("Arial", Font.BOLD, 14));
        verifyButton.setBackground(Color.lightGray);
        verifyButton.setForeground(Color.BLACK);
        verifyButton.setPreferredSize(new Dimension(120, 35));
        buttonPanel.add(verifyButton);

        panel.add(new JLabel());
        panel.add(buttonPanel);

        add(panel, BorderLayout.CENTER);
    }

    public JButton getVerifyButton() {
        return verifyButton;
    }

    public String getVerificationCode() {
        return verificationField.getText();
    }
}
