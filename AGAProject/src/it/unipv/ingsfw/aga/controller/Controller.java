package it.unipv.ingsfw.aga.controller;

import it.unipv.ingsfw.aga.model.Model;
import it.unipv.ingsfw.aga.model.banco.BancoIngresso;
import it.unipv.ingsfw.aga.model.banco.BancoIngressoFactory;
import it.unipv.ingsfw.aga.model.banco.BancoGuardarobaFactory;
import it.unipv.ingsfw.aga.view.LoginPage;
import it.unipv.ingsfw.aga.view.MainPage;
import it.unipv.ingsfw.aga.view.Navbar;
import it.unipv.ingsfw.aga.view.AddGuestPage;
import it.unipv.ingsfw.aga.view.AddEventPage;
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
    private AddEventPage addEventPage;
    private EntrancePage entrancePage;
    private CloakroomPage cloakroomPage;
    private RegisterPage registerPage;
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
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);

        registerPage = new RegisterPage(cardLayout, containerPanel);
        mainPage = new MainPage(cardLayout, containerPanel);
        addGuestPage = new AddGuestPage(cardLayout, containerPanel);
        addEventPage = new AddEventPage(cardLayout, containerPanel);
        entrancePage = new EntrancePage(cardLayout, containerPanel);
        cloakroomPage = new CloakroomPage(cardLayout, containerPanel);
        navbar = new Navbar(cardLayout, containerPanel);

        containerPanel.add(loginPage, "login");
        containerPanel.add(mainPage, "main");
        containerPanel.add(addGuestPage, "addGuest");
        containerPanel.add(addEventPage, "addEvent");
        containerPanel.add(entrancePage, "entrance");
        containerPanel.add(cloakroomPage, "cloakroom");
        containerPanel.add(registerPage, "register");

        // ActionListener per il login
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
        }); loginPage.getRegisterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = loginPage.getUsername();
                String password = loginPage.getPassword();
                cardLayout.show(containerPanel, "register");
            }
        });

        /*// ActionListener per il bottone "Aggiungi Evento" (pagina principale)
       mainPage.getAddEventButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "addEvent");
            }
        });*/ //TODO: Gestire l'accesso alla pagina aggiungi evento

        // ActionListener per il bottone "Aggiungi Invitato"
        mainPage.getAddGuestButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "addGuest");
            }
        });

        // ActionListener per il bottone "Invia" nella pagina AddEvent
        addEventPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String date = addEventPage.getEventDate();
                String location = addEventPage.getEventLocation();
                String capacityStr = addEventPage.getEventCapacity();

                try {
                    int capacity = Integer.parseInt(capacityStr); // Converte la capienza in int
                    // Fai qualcosa con i dati (ad esempio salva l'evento nel modello)
                    JOptionPane.showMessageDialog(null, "Evento aggiunto con successo!");
                    // Torna alla pagina principale dopo aver aggiunto l'evento
                    cardLayout.show(containerPanel, "main");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Capienza non valida. Inserisci un numero intero.");
                }
            }
        });

        // ActionListener per il bottone "Invia" nella pagina AddGuest
        addGuestPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = addGuestPage.getGuestName();
                String email = addGuestPage.getGuestEmail();
                String phone = addGuestPage.getGuestPhone();
                JOptionPane.showMessageDialog(null, "Invitato aggiunto con successo!");
            }
        });

        // ActionListener per il bottone "Home" nella navbar
        navbar.getHomeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "main");
            }
        });

        // ActionListener per il bottone "Logout" nella navbar
        navbar.getLogoutButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "login");
            }
        });

        // ActionListener per il bottone "Entrata" nella pagina principale
        mainPage.getEntranceButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "entrance");
            }
        });

        // ActionListener per il bottone "Guardaroba" nella pagina principale
        mainPage.getCloakroomButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "cloakroom");
            }
        });

        // ActionListener per il bottone "Verifica" nella pagina Entrata
        entrancePage.getVerifyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recupera il codice inserito dall'utente
                String code = entrancePage.getVerificationCode();

                // Supponendo che il model abbia già un'istanza di BancoIngresso,
                // chiama il metodo accesso() passando il codice
                boolean isValid = BancoIngressoFactory.getInstance(0).accesso(code); // chi crea questo banco??

                if (isValid) {
                    JOptionPane.showMessageDialog(null, "Biglietto valido!");
                } else {
                    JOptionPane.showMessageDialog(null, "Biglietto non valido o già convalidato!");
                }
            }
        });

        // ActionListener per il bottone "Consegna" nella pagina Guardaroba
        cloakroomPage.getDeliverButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = cloakroomPage.getItemCode();
                boolean isValid = BancoGuardarobaFactory.getInstance(0).consegnaCapo(code);
                JOptionPane.showMessageDialog(null, "Oggetto consegnato: " + code);
            }
        });

        // ActionListener per il bottone "Restituzione" nella pagina Guardaroba
        cloakroomPage.getReturnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = cloakroomPage.getItemCode();
                boolean isValid = BancoGuardarobaFactory.getInstance(0).restituzioneCapo(code);
                JOptionPane.showMessageDialog(null, "Oggetto restituito: " + code);
            }
        });
    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
