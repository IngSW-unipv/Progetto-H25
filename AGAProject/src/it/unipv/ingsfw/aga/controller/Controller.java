package it.unipv.ingsfw.aga.controller;

import it.unipv.ingsfw.aga.persistence.PersistenceFacade;
import it.unipv.ingsfw.aga.model.Model;
import it.unipv.ingsfw.aga.view.*;
import it.unipv.ingsfw.aga.model.banco.*;
import it.unipv.ingsfw.aga.model.evento.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Controller implements EventSelectionListener {
	private Evento evento;//USATO PER TENER TRACCIA DELL'EVENTO
	private PersistenceFacade persistence;
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
    	persistence= PersistenceFacade.getInstance();
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
        eventsPage = new EventsPage(cardLayout, containerPanel);
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
            /*if (model.checkLogin(username, password)) {
                cardLayout.show(containerPanel, "events");
            } else {
                JOptionPane.showMessageDialog(null, "Login fallito!");
            }*/
            if(persistence.login(username, password)==0) {
            	cardLayout.show(containerPanel, "events");
            	mainPage.setRolePermissions((model.getStaffFlag(username) == 1));
            	}
            else if(persistence.login(username, password)==1) {
            	cardLayout.show(containerPanel, "events");
            	mainPage.setRolePermissions((model.getStaffFlag(username) == 0));
            	}
            else JOptionPane.showMessageDialog(null, "Login fallito!");
        });
    }

    // Metodo per caricare gli eventi e aggiungerli alla EventsPage
    /*private void loadEvents() {
        String[] eventNames = {"Evento 1", "Evento 2", "Evento 3"}; // Eventi fittizi
        for (String eventName : eventNames) {
            eventsPage.addEventButton(eventName);
        }
    }*/
    
    private void loadEvents() {
    	ArrayList<String> result=persistence.getEventi();
        for (String eventName : result) {
            eventsPage.addEventButton(eventName);
        }
    }

    // Metodo per gestire la selezione di un evento
    @Override
    public void onEventSelected(String eventName) {
        System.out.println("Hai selezionato: " + eventName);
        // Mostra la pagina principale
        cardLayout.show(containerPanel, "main");
        try {
    		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    		Date parsed = format.parse(eventName);
    		java.sql.Date data= new java.sql.Date(parsed.getTime());
			evento= new Evento(data);
		} catch (ParseException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Evento non riuscito");
		}
        loginPage.getLoginButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = loginPage.getUsername();
                String password = loginPage.getPassword();
                if(persistence.login(username, password)==0) {
                	cardLayout.show(containerPanel, "main");
                	mainPage.setRolePermissions((model.getStaffFlag(username) == 1));
                	}
                else if(persistence.login(username, password)==1) {
                	cardLayout.show(containerPanel, "main");
                	mainPage.setRolePermissions((model.getStaffFlag(username) == 0));
                }
                else JOptionPane.showMessageDialog(null, "Login fallito!");
                	
                /*if (model.checkLogin(username, password)) {
                    if (model.getStaffFlag(username) == 1) {
                        cardLayout.show(containerPanel, "main");
                        mainPage.setRolePermissions((model.getStaffFlag(username) == 1)); //TODO: TO BE ADAPTED true if organizer false if staff
                    } else {
                        JOptionPane.showMessageDialog(null, "Non sei autorizzato ad accedere come staff.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Login fallito!");
                }*/
            }
        }); loginPage.getRegisterButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = loginPage.getUsername();
                String password = loginPage.getPassword();
                //TODO: registra utente
                cardLayout.show(containerPanel, "register");
            }
        });


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
                String surname = addGuestPage.getGuestSurname();
                JOptionPane.showMessageDialog(null, "Invitato aggiunto con successo!");
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

        // Listener per il pulsante "Modifica Vendite"
        mainPage.getModificaVenditeButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int result=2;
            	try {
            		result=persistence.searchEventoByData(evento.getData());
            	}
            	catch(Exception e1){
            		e1.printStackTrace();
            	}
            	if(result==0) {
            		persistence.setStatoEvento(evento.getData(),true);
            		JOptionPane.showMessageDialog(null, "Vendite Modificate: aperte");
            	}
            	else if(result==1) {
            		persistence.setStatoEvento(evento.getData(),false);
            		JOptionPane.showMessageDialog(null, "Vendite Modificate: chiuse");
            	}
            	else JOptionPane.showMessageDialog(null, "Errore: stato vendite non modificate");
                cardLayout.show(containerPanel, "modificaVendite"); // Esempio: mostra il pannello dedicato
            }
        });

        // Listener per il pulsante "Aggiungi Staff"
        mainPage.getAggiungiStaffButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(containerPanel, "aggiungiStaff");
            }
        });

        // Listener per il pulsante "Vedi Invitati"
        mainPage.getVediInvitatiButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Azioni per visualizzare gli invitati
                cardLayout.show(containerPanel, "listaInvitati"); // Esempio: mostra il pannello con la lista degli invitati
            }
        });


        // TODO: aggiungere la navigazione alle pagine "ListaInvitatiPage" "AggiungiStaff"

        // ActionListener per il bottone "Verifica" nella pagina Entrata
        entrancePage.getVerifyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Recupera il codice inserito dall'utente
                String code = entrancePage.getVerificationCode();
                if (BancoIngressoFactory.getInstance(0).accesso(code)) { //todo: così è associato ad evento o devo modificare qualcosa altro?
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
                boolean isValid = BancoGuardarobaFactory.getInstance(0,evento).consegnaCapo(code);
                JOptionPane.showMessageDialog(null, "Oggetto consegnato: " + code);
            }
        });

        // ActionListener per il bottone "Restituzione" nella pagina Guardaroba
        cloakroomPage.getReturnButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = cloakroomPage.getItemCode();
                int gruccia = BancoGuardarobaFactory.getInstance(0,evento).restituzioneCapo(code);
                JOptionPane.showMessageDialog(null, "Numero grcuccia: " + gruccia);
            }
        });

        aggiungiStaffPage.getSubmitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = aggiungiStaffPage.getUsername();
                String password = aggiungiStaffPage.getPassword();
                String email = aggiungiStaffPage.getEmail();
                // TODO: Logica per aggiungere lo staff al modello
                JOptionPane.showMessageDialog(null, "Staff aggiunto con successo!");
                cardLayout.show(containerPanel, "main");
            }
        });

        // Puoi fare altre azioni specifiche per ciascun evento
        // Ad esempio, mostrare una pagina di dettagli dell'evento
    }

    
    	// TODO: REGISTRATI 
 		
    public JPanel getContainerPanel() {
        return containerPanel;
    }
}
