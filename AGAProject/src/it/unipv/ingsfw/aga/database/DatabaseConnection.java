package it.unipv.ingsfw.aga.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/databaseaga";
    private static final String USER = "utente";
    private static final String PASSWORD = "password";  // Senza password

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connessione riuscita!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Errore di connessione: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        connect(); // Testa la connessione
    }
}
