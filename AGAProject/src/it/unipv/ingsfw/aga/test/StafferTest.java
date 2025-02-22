package it.unipv.ingsfw.aga.test;

import static org.junit.Assert.*;

import it.unipv.ingsfw.aga.model.persone.Staffer;
import org.junit.Test;
import it.unipv.ingsfw.aga.exceptions.AuthenticationException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.Type;

public class StafferTest {

    @Test
    void constructorAllFields() {
        Staffer staffer = new Staffer("ALIVER123", "Alice", "Verdi", "aliceverdi@gmail.com", "password123");
        assertEquals("ALIVER123", staffer.getCodiceFiscale());
        assertEquals("Alice", staffer.getNome());
        assertEquals("Verdi", staffer.getCognome());
        assertEquals("aliceverdi@gmail.com", staffer.getEmail());
        assertEquals("password123", staffer.getPassword());
    }

    @Test
    void changePasswordCorrettamente() throws javax.naming.AuthenticationException {
        Staffer staffer = new Staffer("ALIVER123", "Alice", "Verdi", "aliceverdi@gmail.com", "password123");
        staffer.changePassword("password123", "newpassword456");
        assertEquals("newpassword456", staffer.getPassword());
    }

    @Test
    void changePasswordScorrettamente() {
        Staffer staffer = new Staffer("ALIVER123", "Alice", "Verdi", "aliceverdi@gmail.com", "password123");
        assertThrows(AuthenticationException.class, () -> staffer.changePassword("wrongpassword", "newpassword456"));
    }
    // LOGICA SVILUPPATA IN BANCO
/*
    @Test
    void checkIngressoWithValidQrCode() {
        Staffer staffer = new Staffer("ALIVER123", "Alice", "Verdi", "aliceverdi@gmail.com", "password123");
        QrCode qrCode = new QrCode();
        assertDoesNotThrow(() -> staffer.checkIngresso(Type.GUI, qrCode));
    }

    @Test
    void checkGuardarobaWithValidQrCode() {
        Staffer staffer = new Staffer("ALIVER123", "Alice", "Verdi", "aliceverdi@gmail.com", "password123");
        QrCode qrCode = new QrCode();
        assertDoesNotThrow(() -> staffer.checkGuardaroba(Type.KEYBOARD, qrCode));
    }
*/

    @Test
    void toStringTest() {
        Staffer staffer = new Staffer("ALIVER123", "Alice", "Verdi", "aliceverdi@gmail.com", "password123");
        String expected = "[Persona]\nTipo: Staffer\nNome: Alice Cognome: Verdi\nEmail: aliceverdi@gmail.com\n";
        assertEquals(expected, staffer.toString());
    }
}