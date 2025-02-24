package it.unipv.ingsfw.aga.test;

import static org.junit.Assert.*;
import org.junit.Test;

import it.unipv.ingsfw.aga.controller.Controller;
import org.junit.Before;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;
import it.unipv.ingsfw.aga.view.*;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;

public class ControllerTest {

    private Evento eventoTest;
    private Persona personaTest;
    private PersistenceFacade persistenceTest;
    private LoginPage loginPageTest;
    private MainPage mainPageTest;
    private AddGuestPage addGuestPageTest;
    private AddEventPage addEventPageTest;
    private EntrancePage entrancePageTest;
    private CloakroomPage cloakroomPageTest;
    private RegisterPage registerPageTest;
    private ListaInvitatiPage listaInvitatiPageTest;
    private EventsPage eventsPageTest;
    private AggiungiStaffPage aggiungiStaffPageTest;

    private Controller controller;

    @Before
    public void setUp() {
        controller = new Controller();
    }

    @Test
    public void loginCredenzialiCorrette() {
        // TODO: implementare i test per il login con credenziali valide
    }

    @Test
    public void loginCredezialiSbagliate() {
        //TODO: implementare i test per il login con credenziali non valide
    }

    @Test
    public void caricamentoCorrettoEvento() {
        //TODO: implementare i test per il caricamento degli eventi
    }

    @Test
    public void aggiuntaInvitatoCorretto() {
        //TODO: implementare i test per l'aggiunta di un invitato
    }

    @Test
    public void aggiuntaInvitatoScorretta() {
        //TODO: implementare i test per l'aggiunta di un invitato quando la capienza massima è stata raggiunta
    }
}