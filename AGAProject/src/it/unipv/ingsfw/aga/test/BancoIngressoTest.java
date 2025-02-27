package it.unipv.ingsfw.aga.test;

import connection.DBConnection;
import it.unipv.ingsfw.aga.database.BigliettoDAO;
import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
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
import static org.junit.Assert.assertTrue;

public class BancoIngressoTest {
    private BancoIngresso bancoTest;
    private Evento eventoTest;
    private QrCode qrCode;
    private Connection connection;
    private PersistenceFacade persistenceFacadeTest;
    private BigliettoDAO bigliettoDAO;
    private Biglietto biglietto;

    @Before
    public void setUp() throws MaxExeededException, ParseException {
        eventoTest = new Evento("2022-06-22", "pavia", 600);
        bancoTest = new BancoIngresso(1, eventoTest);
        persistenceFacadeTest = PersistenceFacade.getInstance();
        connection = DBConnection.startConnection(connection); // Apertura connessione inizio test
        bigliettoDAO = new BigliettoDAO();
        biglietto = bigliettoDAO.getBigliettoByQR(new Biglietto("28ff59e6-d76f-472f-aee9-2fb4c13dde0a"));
        qrCode = new QrCode(biglietto.getQRCodeId());
    }

    // Considero: 	il biglietto: 28ff59e6-d76f-472f-aee9-2fb4c13dde0a	0	460-61-8110	2022-06-22	0

    @Test
    public void testInvalidateQr() {
        assertTrue(bancoTest.invalidateQr(qrCode));     //Provo solo la invalidate perchè è quella fondamentale, le altre funzioni richiamano la invalidate
    }

    @Test
    public void testToString() {
        String expected = "[Banco]\nTipo: Ingresso\nNumero banco: 1\nEvento: 2022-06-22\n";
        assertEquals(expected, bancoTest.toString());
    }

    @After
    public void tearDown() {
        connection = DBConnection.closeConnection(connection); // Chiusura connessione fine test
    }
}
