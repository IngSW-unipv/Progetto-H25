package it.unipv.ingsfw.aga.test;

import static org.junit.jupiter.api.Assertions.*;

import it.unipv.ingsfw.aga.model.persone.Persona;
import org.junit.jupiter.api.Test;
import it.unipv.ingsfw.aga.exceptions.PermissionDeniedException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.Type;
import java.util.Date;

public class PersonaTest {

    @Test
    void constructorAllFields() {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        assertEquals("LUCROS123", persona.getCodiceFiscale());
        assertEquals("Luca", persona.getNome());
        assertEquals("Rossi", persona.getCognome());
        assertEquals("lucarossi@gmail.com", persona.getEmail());
    }

    @Test
    void constructorNoFields() {
        Persona persona = new Persona();
        assertEquals("nullo", persona.getCodiceFiscale());
        assertNull(persona.getNome());
        assertNull(persona.getCognome());
        assertNull(persona.getEmail());
    }

    @Test
    void setNomeTest() {
        Persona persona = new Persona();
        persona.setNome("Luca");
        assertEquals("Luca", persona.getNome());
    }

    @Test
    void setCognomeTest() {
        Persona persona = new Persona();
        persona.setCognome("Rossi");
        assertEquals("Rossi", persona.getCognome());
    }

    @Test
    void setEmailTest() {
        Persona persona = new Persona();
        persona.setEmail("lucarossi@gmail.com");
        assertEquals("lucarossi@gmail.com", persona.getEmail());
    }

    @Test
    void creaEventoNonAutorizzato() {
        Persona persona = new Persona();
        assertThrows(PermissionDeniedException.class, () -> {
            persona.creaEvento("Tipo", "CF", new Date(), "Location", 100);
        });
    }

    @Test
    void checkIngressoNonAutorizzato() {
        Persona persona = new Persona();
        assertThrows(PermissionDeniedException.class, () -> {
            persona.checkIngresso(Type.GUI, new QrCode());
        });
    }

    @Test
    void checkGuardarobaNonAutorizzato() {
        Persona persona = new Persona();
        assertThrows(PermissionDeniedException.class, () -> {
            persona.checkGuardaroba(Type.KEYBOARD, new QrCode());
        });
    }
    @Test
    void toStringTest() {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        String expected = "[Persona]\nTipo: Nessuno\nCodice Fiscale: LUCROS123\nNome: Luca Cognome: Rossi\nEmail: lucarossi@gmail.com\n";
        assertEquals(expected, persona.toString());
    }
}