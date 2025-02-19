package it.unipv.ingsfw.aga.view;

import javax.swing.*;
import java.awt.*;
import it.unipv.ingsfw.aga.view.Navbar;

public class AddGuestPage extends JPanel {
    private JTextField guestNameField,guestSurnameField,guestEmailField;
    private JButton submitButton;
    private Navbar navbar;

    public AddGuestPage(CardLayout cardLayout, JPanel containerPanel) {
        setLayout(new BorderLayout());
        //kground(Color.lightGray);

        navbar = new Navbar(cardLayout, containerPanel);
        add(navbar, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10)); // Spaziatura tra i componenti
        // Dimension textFieldSize = new Dimension(200, 30); // Dimensione uniforme per i campi di input

        guestNameField = new JTextField();
        guestNameField.setFont(new Font("Arial", Font.BOLD, 14));
        // guestNameField.setPreferredSize(textFieldSize);
        guestNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Nome:"));
        panel.add(guestNameField);

        guestSurnameField = new JTextField();
        guestSurnameField.setFont(new Font("Arial", Font.BOLD, 14));
        // guestPhoneField.setPreferredSize(textFieldSize);
        guestSurnameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Cognome:"));
        panel.add(guestSurnameField);

        guestEmailField = new JTextField();
        guestEmailField.setFont(new Font("Arial", Font.BOLD, 14));
        // guestEmailField.setPreferredSize(textFieldSize);
        guestEmailField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Email:"));
        panel.add(guestEmailField);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitButton = new JButton("Invia");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.lightGray);
        submitButton.setForeground(Color.BLACK);
        buttonPanel.add(submitButton);

        panel.add(new JLabel());
        panel.add(buttonPanel);


        add(panel, BorderLayout.CENTER);
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public String getGuestName() {
        return guestNameField.getText();
    }

    public String getGuestEmail() {
        return guestEmailField.getText();
    }

    public String getGuestSurname() {
        return guestSurnameField.getText();
    }
}
