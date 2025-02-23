package it.unipv.ingsfw.aga.test;

import connection.DBConnection;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.banco.BancoIngresso;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.text.ParseException;

public class BancoIngressoTest {
    private BancoIngresso bancoTest;
    private Evento eventoTest;
    private Connection connection;
    private PersistenceFacade persistenceFacade;

    // Considero: 	28ff59e6-d76f-472f-aee9-2fb4c13dde0a	0	460-61-8110	2022-06-22	0
    @Before
    public void setUp() throws MaxExeededException, ParseException {
        eventoTest = new Evento("2021-12-31", "MEDA", 1000);
        bancoTest = new BancoIngresso(1, eventoTest);
        persistenceFacade = PersistenceFacade.getInstance();
        connection = DBConnection.startConnection(connection); //Apertura connessione inizio test
    }
//
//    @Test
//    public void validateQr() {
//        assert(bancoTest.validateQr(persistenceFacade.getStatoBiglietto("28ff59e6-d76f-472f-aee9-2fb4c13dde0a")));
//    }

    @Test
    public void invalidateQr() {
    }

    @Test
    public void accesso() {
    }

    @Test
    public void testAccesso() {
    }

    @Test
    public void testToString() {
    }
    @After
    public void tearDown() {
        connection = DBConnection.closeConnection(connection); //Chiusura connessione fine test
    }
}
