package it.unipv.ingsfw.aga.controller;

import it.unipv.ingsfw.aga.model.Model;
import it.unipv.ingsfw.aga.view.*;

import javax.swing.*;
import java.awt.*;

public class Controller implements EventSelectionListener {
    private Model model;
    private LoginPage loginPage;
    private MainPage mainPage;
    private AddGuestPage addGuestPage;
    private AddEventPage addEventPage;
    private EntrancePage entrancePage;
    private CloakroomPage cloakroomPage;
    private RegisterPage registerPage;
    private ListaInvitatiPage listaInvitatiPage;
    private EventsPage eventsPage;
    private AggiungiStaffPage aggiungiStaffPage;

    private CardLayout cardLayout;
    private JPanel containerPanel;
    private Navbar navbar;
    private JFrame frame;

    public Controller() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        model = new Model();
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        navbar = new Navbar(cardLayout, containerPanel);
        loginPage = new LoginPage();
        registerPage = new RegisterPage(cardLayout, containerPanel);
        mainPage = new MainPage(cardLayout, containerPanel);
        addGuestPage = new AddGuestPage(cardLayout, containerPanel);
        addEventPage = new AddEventPage(cardLayout, containerPanel);
        entrancePage = new EntrancePage(cardLayout, containerPanel);
        cloakroomPage = new CloakroomPage(cardLayout, containerPanel);
        listaInvitatiPage = new ListaInvitatiPage(cardLayout, containerPanel);
        eventsPage = new EventsPage(cardLayout, containerPanel);  // Senza il controller come argomento
        aggiungiStaffPage = new AggiungiStaffPage(cardLayout, containerPanel);

        containerPanel.add(loginPage, "login");
        containerPanel.add(mainPage, "main");
        containerPanel.add(addGuestPage, "addGuest");
        containerPanel.add(addEventPage, "addEvent");
        containerPanel.add(entrancePage, "entrance");
        containerPanel.add(cloakroomPage, "cloakroom");
        containerPanel.add(registerPage, "register");
        containerPanel.add(listaInvitatiPage, "listaInvitati");
        containerPanel.add(eventsPage, "events");
        containerPanel.add(aggiungiStaffPage, "aggiungiStaff");

        // Carica gli eventi fittizi o veri dal modello
        loadEvents();

        // Aggiungi l'ActionListener per il bottone "Crea Evento"
        eventsPage.getCreateEventButton().addActionListener(e -> {
            // Logica per andare alla pagina per creare un evento
            cardLayout.show(containerPanel, "addEvent");
        });

        // Settiamo il controller come listener della EventsPage
        eventsPage.setEventSelectionListener(this);

        // ActionListener per il login
        loginPage.getLoginButton().addActionListener(e -> {
            String username = loginPage.getUsername();
            String password = loginPage.getPassword();
            if (model.checkLogin(username, password)) {
                cardLayout.show(containerPanel, "events");
            } else {
                JOptionPane.showMessageDialog(null, "Login fallito!");
            }
        });
    }

    // Metodo per caricare gli eventi e aggiungerli alla EventsPage
    private void loadEvents() {
        String[] eventNames = {"Evento 1", "Evento 2", "Evento 3"}; // Eventi fittizi
        for (String eventName : eventNames) {
            eventsPage.addEventButton(eventName);
        }
    }

    // Metodo per gestire la selezione di un evento
    @Override
    public void onEventSelected(String eventName) {
        System.out.println("Hai selezionato: " + eventName);
        // Mostra la pagina principale
        cardLayout.show(containerPanel, "main");

        // Puoi fare altre azioni specifiche per ciascun evento
        // Ad esempio, mostrare una pagina di dettagli dell'evento
    }

    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
