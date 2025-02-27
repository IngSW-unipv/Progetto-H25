package it.unipv.ingsfw.aga.test;

import connection.DBConnection;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.banco.BancoGuardaroba;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class BancoGuardarobaTest {
    private BancoGuardaroba bancoTest;
    private Evento eventoTest;
    private Connection connection;
    private Biglietto biglietto;

    @Before
    public void setUp() throws MaxExeededException, ParseException {
        eventoTest = new Evento("1998-07-13", "pavia", 150);
        bancoTest = new BancoGuardaroba(2, eventoTest);
        connection = DBConnection.startConnection(connection); // Apertura connessione inizio test
        biglietto = new Biglietto("16c07d34-6bbc-4452-986e-885bb6364ba4");
    }

    @Test
    public void modificaNumeroGrucce() {
        bancoTest.setMaxGrucce(500);
        assertEquals(500, bancoTest.getMaxGrucce());
    }

    @Test
    public void getGrucceAssegnate() {
        assertEquals(0, bancoTest.getGrucceAssegnate());
    }

    @Test
    public void modificaNumeroGrucceAssegnate() {
        bancoTest.setGrucceAssegnate(300);
        assertEquals(300, bancoTest.getGrucceAssegnate());
    }

    @Test
    public void updateMaxGrucceDaDB(){
        bancoTest.updateMaxGrucce(eventoTest);
        assertEquals(1000, bancoTest.getMaxGrucce());
    }
    @Test
    public void updateGrucceAssegnateDaDB(){
        bancoTest.updateGrucceAssegnate(eventoTest);
        assertEquals(13, bancoTest.getGrucceAssegnate());
    }

    @Test
    public void consegnaCapoAssegnatoTest(){
        assertEquals("Gruccia già assegnata",  bancoTest.consegnaCapo(biglietto.getQRCodeId()));
    }


    @After
    public void tearDown() {
        connection = DBConnection.closeConnection(connection); // Chiusura connessione fine test
    }
}