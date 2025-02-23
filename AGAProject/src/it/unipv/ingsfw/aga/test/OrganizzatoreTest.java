package it.unipv.ingsfw.aga.test;

import static org.junit.Assert.*;
import org.junit.Test;


import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Organizzatore;
import it.unipv.ingsfw.aga.exceptions.AuthenticationException;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;



import java.text.ParseException;

public class OrganizzatoreTest {

    @Test
    public void constructorWithAllFields() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertEquals("FRABIA123", organizzatore.getCodiceFiscale());
        assertEquals("Francesco", organizzatore.getNome());
        assertEquals("Bianchi", organizzatore.getCognome());
        assertEquals("frabianchi@gmail.com", organizzatore.getEmail());
        assertEquals("password123", organizzatore.getPassword());
    }
    @Test
    public void changePasswordCorretto() throws AuthenticationException {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        organizzatore.changePassword("password123", "newpassword456");
        assertEquals("newpassword456", organizzatore.getPassword());
    }
    @Test
    public void changePasswordScorrettamente() throws AuthenticationException{
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertThrows(AuthenticationException.class, () -> organizzatore.changePassword("password456", "newpassword456"));
    }

    @Test
    public void creaEventoCorretto() throws MaxExeededException, ParseException {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        Evento evento = organizzatore.creaEvento("TIPO", "2023-12-31", "MEDA", 1000);
        assertNotNull(evento);
    }
    
    @Test
    public void creaEventoPartecipantiNegativi() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertThrows(IllegalArgumentException.class, () -> organizzatore.creaEvento("TIPO", "2023-12-31", "MEDA", -1));
    }
    @Test
    public void creaEventoEccessoPartecipanti() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertThrows(MaxExeededException.class, () -> organizzatore.creaEvento("TIPO", "2023-12-31", "MEDA", 2000));
    }
    @Test
    public void creaEventoDataNonValida() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertThrows(ParseException.class, () -> organizzatore.creaEvento("TIPO", "invalid-date", "MEDA", 1000));
    }
    // LOGICA SVILUPPATA IN BANCO
/*
    @Test
    void checkIngressoWithValidQrCode() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        QrCode qrCode = new QrCode();
        assertDoesNotThrow(() -> organizzatore.checkIngresso(Type.GUI, qrCode));
    }

    @Test
    void checkGuardarobaWithValidQrCode() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        QrCode qrCode = new QrCode();
        assertDoesNotThrow(() -> organizzatore.checkGuardaroba(Type.KEYBOARD, qrCode));
    }
*/
    @Test
    public void toStringMethod() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        String expected = "[Persona]\nTipo: Organizzatore\nNome: Francesco Cognome: Bianchi\nEmail: frabianchi@gmail.com\n";
        assertEquals(expected, organizzatore.toString());
    }
}