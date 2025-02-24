package it.unipv.ingsfw.aga.controller;

import it.unipv.ingsfw.aga.persistence.PersistenceFacade;
import it.unipv.ingsfw.aga.view.*;
import it.unipv.ingsfw.aga.model.banco.*;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Controller per la gestione dell'interfaccia e della logica applicativa.
 * <p>
 * Questa classe si occupa di inizializzare le varie pagine dell'applicazione,
 * di impostare la navigazione tramite {@link CardLayout} e di collegare le azioni dell'utente con le operazioni
 * sul modello e sulla persistenza dei dati.
 * </p>
 * <p>
 * Implementa l'interfaccia {@code EventSelectionListener} per gestire la selezione degli eventi.
 * </p>
 */
public class Controller implements EventSelectionListener {

    /**
     * Evento corrente selezionato.
     */
    private Evento evento;

    /**
     * Persona che sta creando il biglietto o gestendo l'evento.
     */
    private Persona persona;

    /**
     * Facade per l'accesso e la gestione della persistenza.
     */
    private PersistenceFacade persistence;

    /**
     * Pagina di login.
     */
    private LoginPage loginPage;

    /**
     * Pagina principale.
     */
    private MainPage mainPage;

    /**
     * Pagina per aggiungere un invitato.
     */
    private AddGuestPage addGuestPage;

    /**
     * Pagina per aggiungere un evento.
     */
    private AddEventPage addEventPage;

    /**
     * Pagina per la gestione dell'entrata all'evento.
     */
    private EntrancePage entrancePage;

    /**
     * Pagina del guardaroba.
     */
    private CloakroomPage cloakroomPage;

    /**
     * Pagina di registrazione.
     */
    private RegisterPage registerPage;

    /**
     * Pagina per visualizzare la lista degli invitati.
     */
    private ListaInvitatiPage listaInvitatiPage;

    /**
     * Pagina che mostra gli eventi.
     */
    private EventsPage eventsPage;

    /**
     * Pagina per aggiungere un membro dello staff.
     */
    private AggiungiStaffPage aggiungiStaffPage;

    /**
     * Layout a schede per la navigazione tra le diverse pagine.
     */
    private CardLayout cardLayout;

    /**
     * Pannello contenitore che ospita le pagine.
     */
    private JPanel containerPanel;

    /**
     * Barra di navigazione.
     */
    private Navbar navbar;

    /**
     * Finestra principale dell'applicazione.
     */
    private JFrame frame;

    /**
     * Costruttore del Controller.
     * <p>
     * Inizializza le pagine dell'applicazione, il layout, il container, la persistenza e
     * configura gli {@code ActionListener} per gestire le azioni dell'utente.
     * </p>
     */
    public Controller() {
        persistence = PersistenceFacade.getInstance();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        navbar = new Navbar(cardLayout, containerPanel);

        // Inizializzazione delle pagine dell'applicazione
        loginPage = new LoginPage();
        registerPage = new RegisterPage(cardLayout, containerPanel);
        mainPage = new MainPage(cardLayout, containerPanel);
        addGuestPage = new AddGuestPage(cardLayout, containerPanel);
        addEventPage = new AddEventPage(cardLayout, containerPanel);
        entrancePage = new EntrancePage(cardLayout, containerPanel);
        cloakroomPage = new CloakroomPage(cardLayout, containerPanel);
        listaInvitatiPage = new ListaInvitatiPage(cardLayout, containerPanel);
        eventsPage = new EventsPage(cardLayout, containerPanel);
        aggiungiStaffPage = new AggiungiStaffPage(cardLayout, containerPanel);

        // Aggiunta delle pagine al container
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

        // Listener per il bottone "Crea Evento" in EventsPage
        eventsPage.getCreateEventButton().addActionListener(e -> {
            cardLayout.show(containerPanel, "addEvent");
        });

        // ActionListener per il login
        loginPage.getLoginButton().addActionListener(e -> {
            String username = loginPage.getUsername();
            int intPassword = loginPage.getPassword().hashCode();
            String password = Integer.toString(intPassword);
            if (persistence.login(username, password) == 0) {
                cardLayout.show(containerPanel, "events");
                persona = persistence.getCodiceFiscaleByEmail(username);
                mainPage.setRolePermissions(false);
            } else if (persistence.login(username, password) == 1) {
                cardLayout.show(containerPanel, "events");
                persona = persistence.getCodiceFiscaleByEmail(username);
                mainPage.setRolePermissions(true);
            } else {
                JOptionPane.showMessageDialog(null, "Login fallito!");
            }
        });


        // Listener per il bottone "Register" nella pagina di login
        loginPage.getRegisterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "register");
            }
        });

        // Impostazione del listener per la selezione degli eventi
        eventsPage.setEventSelectionListener(this);
        loadEvents();

        // Listener per il bottone "Aggiungi Invitato" in MainPage
        mainPage.getAddGuestButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "addGuest");
            }
        });

        // Listener per il bottone "Submit" in AddEventPage per aggiungere un evento
        addEventPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String date = addEventPage.getEventDate();
                String location = addEventPage.getEventLocation();
                String capacity = addEventPage.getEventCapacity();
                boolean result = persistence.addEvento(date, location, capacity);
                if (result)
                    JOptionPane.showMessageDialog(null, "Evento aggiunto");
                else
                    JOptionPane.showMessageDialog(null, "Errore nell'aggiunta dell'evento");
            }
        });

        // Listener per il bottone "Submit" nella pagina AddGuest per aggiungere un invitato
        addGuestPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = addGuestPage.getGuestName();
                String email = addGuestPage.getGuestEmail();
                String surname = addGuestPage.getGuestSurname();
                int result = persistence.aggiungiInvitato(persona, evento, name, surname, email);
                if (result == 1)
                    JOptionPane.showMessageDialog(null, "Invitato aggiunto con successo!");
                else if (result == -1)
                    JOptionPane.showMessageDialog(null, "E' stata raggiunta la massima capacita' dell'evento");
                else if (result == 0)
                    JOptionPane.showMessageDialog(null, "Hai raggiunto il massimo degli inviti");
                else
                    JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati");
            }
        });

        // Listener per il bottone "Entrata" in MainPage che mostra la pagina Entrata
        mainPage.getEntranceButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "entrance");
            }
        });

        // Listener per il bottone "Guardaroba" in MainPage che mostra la pagina del guardaroba
        mainPage.getCloakroomButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "cloakroom");
            }
        });

        // Listener per il pulsante "Modifica Vendite" in MainPage per modificare lo stato delle vendite
        mainPage.getModificaVenditeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = 2;
                try {
                    result = persistence.searchEventoByData(evento.getData());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                if (result == 0) {
                    persistence.setStatoEvento(evento.getData(), true);
                    JOptionPane.showMessageDialog(null, "Vendite Modificate: aperte");
                } else if (result == 1) {
                    persistence.setStatoEvento(evento.getData(), false);
                    JOptionPane.showMessageDialog(null, "Vendite Modificate: chiuse");
                } else {
                    JOptionPane.showMessageDialog(null, "Errore: stato vendite non modificate");
                }
                cardLayout.show(containerPanel, "modificaVendite");
            }
        });

        // Listener per il pulsante "Aggiungi Staff" in MainPage
        mainPage.getAggiungiStaffButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "aggiungiStaff");
            }
        });

        // Listener per il pulsante "Vedi Invitati" in MainPage per visualizzare la lista degli invitati
        mainPage.getVediInvitatiButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<Biglietto> invitati;
                invitati = persistence.getInvitati(evento);
                listaInvitatiPage.updateGuestsList(invitati);
                cardLayout.show(containerPanel, "listaInvitati");
            }
        });

        // Listener per il bottone "Verifica" nella pagina Entrata per verificare il biglietto
        entrancePage.getVerifyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = entrancePage.getVerificationCode();
                if (BancoIngressoFactory.getInstance(0).accesso(code)) {
                    JOptionPane.showMessageDialog(null, "Biglietto valido!");
                } else {
                    JOptionPane.showMessageDialog(null, "Biglietto non valido o già convalidato!");
                }
            }
        });

        // Listener per il bottone "Consegna" nella pagina Guardaroba per consegnare un capo
        cloakroomPage.getDeliverButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = cloakroomPage.getItemCode();
                String message = BancoGuardarobaFactory.getInstance(0, evento).consegnaCapo(code);
                JOptionPane.showMessageDialog(null, message);
            }
        });

        // Listener per il bottone "Restituzione" nella pagina Guardaroba per restituire un capo
        cloakroomPage.getReturnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = cloakroomPage.getItemCode();
                int gruccia = BancoGuardarobaFactory.getInstance(0, evento).restituzioneCapo(code);
                JOptionPane.showMessageDialog(null, "Numero gruccia: " + gruccia);
            }
        });

        // Listener per il bottone "Submit" in AggiungiStaffPage per aggiungere un membro dello staff
        aggiungiStaffPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codiceFiscale = aggiungiStaffPage.getCf();
                String email = aggiungiStaffPage.getEmail();
                int intPassword = aggiungiStaffPage.getPassword().hashCode();
                String password = Integer.toString(intPassword);
                String name = aggiungiStaffPage.getName();
                String surname = aggiungiStaffPage.getSurname();
                boolean result = persistence.addStaffer(codiceFiscale, name, surname, email, password);

                if (result)
                    JOptionPane.showMessageDialog(null, "Staff aggiunto con successo!");
                else
                    JOptionPane.showMessageDialog(null, "Errore nell'aggiunta dello staffer");
                cardLayout.show(containerPanel, "main");
            }
        });

        // Listener per il bottone "Submit" in RegisterPage per registrare un organizzatore
        registerPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codiceFiscale = registerPage.getCf();
                String email = registerPage.getEmail();
                int intPassword = registerPage.getPassword().hashCode();
                String password = Integer.toString(intPassword);
                String nome = registerPage.getName();
                String cognome = registerPage.getSurname();
                boolean result = persistence.registazioneOrganizzatore(codiceFiscale, nome, cognome, email, password);
                if (result)
                    JOptionPane.showMessageDialog(null, "Aggiunto con successo!");
                else
                    JOptionPane.showMessageDialog(null, "Errore nell'aggiunta ");
                cardLayout.show(containerPanel, "events");
            }
        });
    }

    /**
     * Carica gli eventi dal database e aggiunge un pulsante per ciascun evento nella pagina degli eventi.
     */
    public void loadEvents() {
        ArrayList<String> result = persistence.getEventi();
        for (String eventName : result) {
            eventsPage.addEventButton(eventName);
        }
    }

    /**
     * Metodo chiamato quando viene selezionato un evento.
     * <p>
     * Converte il nome(data) dell'evento (formattato come data in "yyyy-mm-dd") in un oggetto {@code Evento}.
     * Mostra la pagina principale e gestisce eventuali errori di conversione.
     * </p>
     *
     * @param eventName il nome dell'evento selezionato
     */
    public void onEventSelected(String eventName) {
        System.out.println("Hai selezionato: " + eventName);
        // Mostra la pagina principale
        cardLayout.show(containerPanel, "main");
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = format.parse(eventName);
            java.sql.Date data = new java.sql.Date(parsed.getTime());
            evento = new Evento(data);
        } catch (ParseException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Evento non riuscito");
        }
    }

    /**
     * Restituisce il pannello contenitore che ospita tutte le pagine dell'interfaccia.
     *
     * @return il {@code JPanel} contenitore
     */
    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
