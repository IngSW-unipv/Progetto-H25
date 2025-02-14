package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BigliettoDAO implements IBigliettoDAO {

    private Connection conn;

    public BigliettoDAO(Connection conn) {
        this.conn = conn;
    }

    private static final String INSERISCI_BIGLIETTO_SQL =
            "INSERT INTO biglietti (nome, cognome, email, numBiglietto, idEvento)) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void aggiungiBiglietto(Biglietto biglietto) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERISCI_BIGLIETTO_SQL)) {

            stmt.setString(1, biglietto.getEmail());
            stmt.setString(2, biglietto.getNome());
            stmt.setString(3, biglietto.getCognome());
            stmt.setString(4, biglietto.getIdEvento());
            stmt.setString(5, biglietto.getQRCodeId());

            stmt.executeUpdate();
        }
    }
}

