package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.persone.Invitato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvitatoDAO {
    public static void aggiungiInvitato(Invitato invitato) throws SQLException {
        // Query SQL per l'inserimento
        String sql = "INSERT INTO invitati (email, nome, cognome) VALUES (?, ?, ?)";

        // Connessione al database e preparazione dello statement
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Imposta i parametri dello statement
            pstmt.setString(1, invitato.getEmail());
            pstmt.setString(2, invitato.getNome());
            pstmt.setString(3, invitato.getCognome());

            // Esegui l'aggiornamento
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Invitato aggiunto con successo!");
            } else {
                System.out.println("Nessun invitato aggiunto.");
            }
        } catch (SQLException e) {
            // Rilancia l'eccezione con un messaggio più descrittivo
            throw new SQLException("Errore durante l'inserimento dell'invitato: " + e.getMessage(), e);
        }
    }
}