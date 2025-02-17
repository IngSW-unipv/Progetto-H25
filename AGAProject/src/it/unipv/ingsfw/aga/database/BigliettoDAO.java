package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DBConnection;

public class BigliettoDAO implements IBigliettoDAO {

    private Connection conn;

    public BigliettoDAO(Connection conn) {
        this.conn = conn;
    }

    private static final String INSERISCI_BIGLIETTO_SQL =
            "INSERT INTO biglietti (nome, cognome, email, numBiglietto, dataEvento)) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void aggiungiBiglietto(Biglietto biglietto) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERISCI_BIGLIETTO_SQL)) {

            stmt.setString(1, biglietto.getEmail());
            stmt.setString(2, biglietto.getNome());
            stmt.setString(3, biglietto.getCognome());
            stmt.setString(4, String.valueOf(biglietto.getDataEvento()));
            stmt.setString(5, biglietto.getQRCodeId());

            stmt.executeUpdate();
        }
    }
    
    //SELECT PERSONA
    public void getPersona(Biglietto biglietto) {
    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Persona persona;
    }
}

