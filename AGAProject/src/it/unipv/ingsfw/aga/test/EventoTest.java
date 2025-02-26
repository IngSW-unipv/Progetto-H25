package it.unipv.ingsfw.aga.test;

import static org.junit.Assert.*;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.persone.Organizzatore;
import org.junit.Before;
import org.junit.Test;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import java.text.ParseException;

public class EventoTest {
    private Evento eventoTest1; // Per i test che usano dati corretti
    private Evento eventoTest2; // Per i test che usano dati scorretti
    private Evento eventoTest3; // Per i test che usano dati scorretti
    private Organizzatore organizzatoreTest;
    private Biglietto bigliettoTest;    //Per aggiungi biglietto

    @Before
    public void setUp() throws ParseException, MaxExeededException {
        eventoTest1 = new Evento("2023-12-31", "MEDA", 1000);
        eventoTest2 = new Evento("2023-12-31", "MEDA", 1000);
        eventoTest3 = new Evento("2023-12-31", "MEDA", 1000);
        organizzatoreTest = new Organizzatore("MARROS123", "Mario", "Rossi", "mario@gmail.com", "psw123");
        bigliettoTest = new Biglietto (organizzatoreTest, eventoTest1, "Francesco", "Geppetto", "Fragep@gmail.com");
    }

    @Test
    public void costruttoreCorretto() throws ParseException, MaxExeededException {
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        assertEquals("2023-12-31", evento.getData().toString());
        assertEquals("MEDA", evento.getLocation());
        assertEquals(1000, evento.getMaxPartecipanti());
        assertFalse(evento.getVenditeAperte());
    }

    @Test
    public void costruttorePartecipantiNegativi() {
        assertThrows(IllegalArgumentException.class, () -> new Evento("2023-12-31", "MEDA", -1));
    }

    @Test
    public void costruttoreEccessoPartecipanti() {
        assertThrows(MaxExeededException.class, () -> new Evento("2023-12-31", "MEDA", 20000));
    }

    @Test
    public void setMaxPartecipantiCorretto() throws ParseException, MaxExeededException {
        eventoTest1.setMaxPartecipanti(500);
        assertEquals(500, eventoTest1.getMaxPartecipanti());
    }

    @Test
    public void setMaxPartecipantiScorrettoNegativo() throws ParseException, MaxExeededException {
        assertThrows(IllegalArgumentException.class, () -> eventoTest2.setMaxPartecipanti(-1));
    }

    @Test
    public void setMaxPartecipantiScorrettoEccesso() throws ParseException, MaxExeededException {
        assertThrows(MaxExeededException.class, () -> eventoTest3.setMaxPartecipanti(20000));
    }

    @Test
    public void aggiuntaBiglietto() throws ParseException, MaxExeededException {
        Biglietto biglietto = eventoTest1.aggiugiBiglietto(organizzatoreTest, eventoTest1, "Francesco", "Geppetto", "Fragep@gmail.com");
        assertEquals(bigliettoTest.getCreatoreBiglietto(), biglietto.getCreatoreBiglietto());
        assertEquals(bigliettoTest.getDataEvento(), biglietto.getDataEvento());
        assertEquals(bigliettoTest.getNome(), biglietto.getNome());
        assertEquals(bigliettoTest.getCognome(), biglietto.getCognome());
        assertEquals(bigliettoTest.getEmail(), biglietto.getEmail());
    }
    @Test
    public void toStringTest() throws ParseException, MaxExeededException {
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        String expected = "[Evento]\nTipo: Standard\nData: 2023-12-31\nLocation: MEDA\nVendite chiuse\n";
        assertEquals(expected, evento.toString());
    }
}