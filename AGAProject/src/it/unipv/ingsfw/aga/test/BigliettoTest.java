//package organizzazioneEventi.model.test;

package it.unipv.ingsfw.aga.test;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BigliettoTest {

    @Test
    public void aggiornamentoQrCode() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");
        String oldQRCode = biglietto.getQRCodeId();

        biglietto.changeQRCode();

        assertNotEquals(oldQRCode, biglietto.getQRCodeId());
    }

    @Test
    public void getQRCodeId() throws MaxExeededException, ParseException {
        QrCode qrCode = new QrCode("1b4b76e0-3c14-46b9-9685-e11b6c12e084");
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, "Alice", "Verdi", "aliceverdi@gmail.com", qrCode, evento, false, 0);

        assertEquals("1b4b76e0-3c14-46b9-9685-e11b6c12e084", biglietto.getQRCodeId());
    }

    @Test
    public void getCreatoreBiglietto() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals(persona, biglietto.getCreatoreBiglietto());
    }

    @Test
    public void getNomeCreatore() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals("Luca", biglietto.getNomeCreatore());
    }

    @Test
    public void getCognomeCreatore() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals("Rossi", biglietto.getCognomeCreatore());
    }

    @Test
    public void getEmailCreatore() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals("lucarossi@gmail.com", biglietto.getEmailCreatore());
    }

    @Test
    public void getCodiceFiscaleCreatore() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals("LUCROS123", biglietto.getCodiceFiscaleCreatore());
    }

    @Test
    public void getNomeInvitato() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals("Alice", biglietto.getNome());
    }

    @Test
    public void getCognomeInvitato() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals("Verdi", biglietto.getCognome());
    }

    @Test
    public void getEmailInvitato() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals("aliceverdi@gmail.com", biglietto.getEmail());
    }

    @Test
    public void getDataEvento() throws MaxExeededException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse("2023-12-31");
		java.sql.Date date= new java.sql.Date(parsed.getTime());
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals(date, biglietto.getDataEvento());
    }


    @Test
    public void getAccessoEffettuato() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertFalse(biglietto.getAccessoEffettuato());
    }

    @Test
    public void setAccessoEffettuato() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        biglietto.setAccessoEffettuato(true);

        assertTrue(biglietto.getAccessoEffettuato());
    }

    @Test
    public void getNumeroGruccia() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals(0, biglietto.getNumeroGruccia());
    }

    @Test
    public void setNumeroGruccia() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        biglietto.setNumeroGruccia(5);

        assertEquals(5, biglietto.getNumeroGruccia());
    }

    @Test
    public void getDescrizioneGruccia() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        assertEquals("", biglietto.getDescrizioneGruccia());
    }

    @Test
    public void toStringTest() throws MaxExeededException, ParseException {
        Persona persona = new Persona("LUCROS123", "Luca", "Rossi", "lucarossi@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Alice", "Verdi", "aliceverdi@gmail.com");

        String expected = "[Biglietto]\nTipo: Standard\nNome: Alice\nCognome: Verdi\nEmail: aliceverdi@gmail.com\nQrCode: " + biglietto.getQRCodeId() + "\nCreatore: Luca Rossi - Email: lucarossi@gmail.com\n";

        assertEquals(expected, biglietto.toString());
    }
}