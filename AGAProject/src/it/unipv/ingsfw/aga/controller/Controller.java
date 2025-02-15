package it.unipv.ingsfw.aga.controller;

import it.unipv.ingsfw.aga.model.Model;
import it.unipv.ingsfw.aga.view.LoginPage;
import it.unipv.ingsfw.aga.view.MainPage;
import it.unipv.ingsfw.aga.view.Navbar;
import it.unipv.ingsfw.aga.view.AddGuestPage;
import it.unipv.ingsfw.aga.view.EntrancePage;
import it.unipv.ingsfw.aga.view.CloakroomPage;
import it.unipv.ingsfw.aga.view.RegisterPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private Model model;
    private LoginPage loginPage;
    private MainPage mainPage;
    private AddGuestPage addGuestPage;
    private EntrancePage entrancePage;
    private CloakroomPage cloakroomPage;
    private RegisterPage registerPage; // Pagina di registrazione
    private CardLayout cardLayout;
    private JPanel containerPanel;
    private Navbar navbar;
    private JFrame frame;

    public Controller() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        model = new Model();
        loginPage = new LoginPage();
        registerPage = new RegisterPage(); // Crea la pagina di registrazione
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);

        mainPage = new MainPage(cardLayout, containerPanel);
        addGuestPage = new AddGuestPage(cardLayout, containerPanel);
        entrancePage = new EntrancePage(cardLayout, containerPanel);
        cloakroomPage = new CloakroomPage(cardLayout, containerPanel);
        navbar = new Navbar(cardLayout, containerPanel);

        containerPanel.add(loginPage, "login");
        containerPanel.add(mainPage, "main");
        containerPanel.add(addGuestPage, "addGuest");
        containerPanel.add(entrancePage, "entrance");
        containerPanel.add(cloakroomPage, "cloakroom");
        containerPanel.add(registerPage, "register"); // Aggiungi la pagina di registrazione

        loginPage.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = loginPage.getUsername();
                String password = loginPage.getPassword();
                if (model.checkLogin(username, password)) {
                    if (model.getStaffFlag(username) == 1) {
                        cardLayout.show(containerPanel, "main");
                    } else {
                        JOptionPane.showMessageDialog(null, "Non sei autorizzato ad accedere come staff.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login fallito!");
                }
            }
        });

        loginPage.getRegisterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "register");
            }
        });

        registerPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = registerPage.getUsername();
                String password = registerPage.getPassword();
                String email = registerPage.getEmail();

                boolean success = true;
                if (success) {
                    JOptionPane.showMessageDialog(null, "Registrazione completata con successo!");
                    cardLayout.show(containerPanel, "login"); // Torna alla pagina di login
                } else {
                    JOptionPane.showMessageDialog(null, "Errore nella registrazione. Riprova.");
                }
            }
        });

        // ActionListener per il bottone "Add Guest"
        mainPage.getAddGuestButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "addGuest");
            }
        });

        // ActionListener per il bottone "Home" (Navbar)
        navbar.getHomeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "main");
            }
        });

        navbar.getLogoutButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "login");
            }
        });

        addGuestPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = addGuestPage.getGuestName();
                String email = addGuestPage.getGuestEmail();
                String phone = addGuestPage.getGuestPhone();
                JOptionPane.showMessageDialog(null, "Invitato aggiunto con successo!");
            }
        });

        mainPage.getEntranceButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "entrance");
            }
        });

        entrancePage.getVerifyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = entrancePage.getVerificationCode();
                JOptionPane.showMessageDialog(null, "Codice di ingresso verificato: " + code);
            }
        });

        mainPage.getCloakroomButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "cloakroom");
            }
        });

        cloakroomPage.getDeliverButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = cloakroomPage.getItemCode();
                JOptionPane.showMessageDialog(null, "Oggetto consegnato: " + code);
            }
        });

        cloakroomPage.getReturnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = cloakroomPage.getItemCode();
                JOptionPane.showMessageDialog(null, "Oggetto restituito: " + code);
            }
        });
    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
