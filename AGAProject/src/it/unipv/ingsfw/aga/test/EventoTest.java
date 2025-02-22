package it.unipv.ingsfw.aga.test;

import static org.junit.Assert.*;
import org.junit.Test;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import java.text.ParseException;

public class EventoTest {

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
        assertThrows(MaxExeededException.class, () -> new Evento("2023-12-31", "MEDA", 2000));
    }

    @Test
    public void setMaxPartecipantiWithValidValueUpdatesField() throws ParseException, MaxExeededException {
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        evento.setMaxPartecipanti(500);
        assertEquals(500, evento.getMaxPartecipanti());
    }

    @Test
    public void setMaxPartecipantiWithNegativeValueThrowsException() throws ParseException, MaxExeededException {
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        assertThrows(IllegalArgumentException.class, () -> evento.setMaxPartecipanti(-1));
    }

    @Test
    public void setMaxPartecipantiWithExceedingValueThrowsException() throws ParseException, MaxExeededException {
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        assertThrows(MaxExeededException.class, () -> evento.setMaxPartecipanti(2000));
    }

    @Test
    public void toStringMethodReturnsCorrectString() throws ParseException, MaxExeededException {
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        String expected = "[Evento]\nTipo: Standard\nData: 2023-12-31\nLocation: MEDA\nVendite chiuse\n";
        assertEquals(expected, evento.toString());
    }
}