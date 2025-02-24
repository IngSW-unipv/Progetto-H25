package it.unipv.ingsfw.aga.test;

import connection.DBConnection;
import it.unipv.ingsfw.aga.database.BigliettoDAO;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.banco.BancoGuardaroba;
import it.unipv.ingsfw.aga.model.banco.BancoIngresso;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class BancoGuardarobaTest {


    private BancoGuardaroba bancoTest;
    private Evento eventoTest;
    private QrCode qrCode;
    private Connection connection;
    private PersistenceFacade persistenceFacadeTest;
    private BigliettoDAO bigliettoDAO;
    private Biglietto biglietto;

    @Before
    public void setUp() throws MaxExeededException, ParseException {
        eventoTest = new Evento("2021-12-31", "MEDA", 1000);
        bancoTest = new BancoGuardaroba(1, eventoTest);
        persistenceFacadeTest = PersistenceFacade.getInstance();
        connection = DBConnection.startConnection(connection); // Apertura connessione inizio test
        bigliettoDAO = new BigliettoDAO();
        biglietto = bigliettoDAO.getBigliettoByQR(new Biglietto("28ff59e6-d76f-472f-aee9-2fb4c13dde0a"));
        qrCode = new QrCode(biglietto.getQRCodeId());
    }
/*
    @Test
    public void getMaxGrucceReturnsCorrectValue() {
        assertEquals(1000, bancoTest.getMaxGrucce());//lo prendo dal db
    }
 */

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
    @After
    public void tearDown() {
        connection = DBConnection.closeConnection(connection); // Chiusura connessione fine test
    }
}