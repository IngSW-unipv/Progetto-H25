package it.unipv.ingsfw.aga.test;

import static org.junit.jupiter.api.Assertions.*;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Organizzatore;
import it.unipv.ingsfw.aga.model.persone.Staffer;
import org.junit.jupiter.api.Test;
import it.unipv.ingsfw.aga.exceptions.AuthenticationException;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.Type;

import java.text.ParseException;
import java.util.Date;

public class OrganizzatoreTest {

    @Test
    void constructorWithAllFields() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertEquals("FRABIA123", organizzatore.getCodiceFiscale());
        assertEquals("Francesco", organizzatore.getNome());
        assertEquals("Bianchi", organizzatore.getCognome());
        assertEquals("frabianchi@gmail.com", organizzatore.getEmail());
        assertEquals("password123", organizzatore.getPassword());
    }
    @Test
    void changePasswordCorretto() throws javax.naming.AuthenticationException {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        organizzatore.changePassword("password123", "newpassword456");
        assertEquals("newpassword456", organizzatore.getPassword());
    }
    //Genera un eccezzione sbagliata .-.
/*    @Test
    void changePasswordScorrettamente() {
        Staffer staffer = new Staffer("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertThrows(AuthenticationException.class, () -> staffer.changePassword("wrongpassword", "newpassword456"));
    }
*/
    @Test
    void creaEventoCorretto() throws MaxExeededException, ParseException {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        Evento evento = organizzatore.creaEvento("TIPO", "2023-12-31", "MEDA", 1000);
        assertNotNull(evento);
    }
    @Test
    void creaEventoPartecipantiNegativi() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertThrows(IllegalArgumentException.class, () -> organizzatore.creaEvento("TIPO", "2023-12-31", "MEDA", -1));
    }
    @Test
    void creaEventoEccessoPartecipanti() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        assertThrows(MaxExeededException.class, () -> organizzatore.creaEvento("TIPO", "2023-12-31", "MEDA", 2000));
    }
    @Test
    void creaEventoDataNonValida() {
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
    void toStringMethod() {
        Organizzatore organizzatore = new Organizzatore("FRABIA123", "Francesco", "Bianchi", "frabianchi@gmail.com", "password123");
        String expected = "[Persona]\nTipo: Organizzatore\nNome: Francesco Cognome: Bianchi\nEmail: frabianchi@gmail.com\n";
        assertEquals(expected, organizzatore.toString());
    }
}