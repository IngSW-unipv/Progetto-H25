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
        eventoTest = new Evento("1998-07-13", "pavia", 150);
        bancoTest = new BancoGuardaroba(1, 20, eventoTest);
        persistenceFacadeTest = PersistenceFacade.getInstance();
        connection = DBConnection.startConnection(connection); // Apertura connessione inizio test
        bigliettoDAO = new BigliettoDAO();
        biglietto = bigliettoDAO.getBigliettoByQR(new Biglietto("236d90de-b4df-420a-8a0c-270aaace7640"));
        qrCode = new QrCode(biglietto.getQRCodeId());
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
        assertEquals(300, bancoTest.getMaxGrucce());
    }
    @Test
    public void updateGrucceAssegnateDaDB(){
        bancoTest.updateGrucceAssegnate(eventoTest);
        assertEquals(8, bancoTest.getGrucceAssegnate());
    }

    @Test
    public void consegnaCapoTest(){
        bancoTest.consegnaCapo(biglietto.getQRCodeId());
        assertEquals(0, bancoTest.getGrucceAssegnate());
    }


    @After
    public void tearDown() {
        connection = DBConnection.closeConnection(connection); // Chiusura connessione fine test
    }
}