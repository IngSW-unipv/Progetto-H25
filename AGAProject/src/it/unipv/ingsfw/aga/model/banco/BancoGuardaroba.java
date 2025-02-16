package it.unipv.ingsfw.aga.model.banco;
import java.sql.*;


public class BancoGuardaroba extends Banco {
    private int maxGrucce;
    private int grucceAssegnate;

    public BancoGuardaroba(int numeroBanco) {
        super(numeroBanco);
        this.maxGrucce = 1000;
        this.grucceAssegnate = 0;
    }

    public BancoGuardaroba(int numeroBanco, int maxGrucce) {
        super(numeroBanco);
        this.maxGrucce = maxGrucce;
        this.grucceAssegnate = 0;
    }

    public int getMaxGrucce() {
        return maxGrucce;
    }

    public void setMaxGrucce(int maxGrucce) {
        this.maxGrucce = maxGrucce;
    }

    public int getGrucceAssegnate() {
        return grucceAssegnate;
    }

    public void setGrucceAssegnate(int grucceAssegnate) {
        this.grucceAssegnate = grucceAssegnate;
    }

    public boolean consegnaCapo() {
        QrCode qr = readQR();
        this. grucceAssegnate += 1;
        if (this.grucceAssegnate < this.maxGrucce) {
           return assegnaGruccia(qr, this.grucceAssegnate);
        }else {
            System.out.println("Grucce terminate");
            return false;
        }
    }

    public boolean consegnaCapo(String code) {
        QrCode qr = readQR(code);
        this. grucceAssegnate += 1;
        if (this.grucceAssegnate < this.maxGrucce) {
            return assegnaGruccia(qr, this.grucceAssegnate);
        }else {
            System.out.println("Grucce terminate");
            return false;
        }
    }

    public boolean restituzioneCapo(){
        QrCode qr = readQR();
        return validateQr(qr);
    }

    public boolean restituzioneCapo(String code){
        QrCode qr = readQR(code);
        return validateQr(qr);
    }

    public boolean assegnaGruccia(QrCode qr, int gruccia){
        String url = "jdbc:mysql://localhost:3306/nome_database";
        String username = "utente";
        String password = "password";
        String updateQuery = "UPDATE biglietti SET Gruccia = ?, NBanco = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            // Imposta i parametri della query
            stmt.setInt(1, gruccia);
            stmt.setInt(2, this.getNumeroBanco());// Imposta il valore del booleano a false
            stmt.setString(3, readQR().getId());

            // Esegue l'aggiornamento
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Il valore booleano è stato aggiornato con successo.");
            } else {
                System.out.println("Nessuna tupla è stata trovata con l'ID specificato e colonna_bool = true.");
            }

        } catch (SQLException e) {
            System.err.println("Errore SQL: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
             //chiusura connessione
        }
        return true;
    }
    @Override
    public boolean validateQr(QrCode qr){
        String url = "jdbc:mysql://localhost:3306/nome_database"; // Cambia "nome_database"
        String username = "utente"; // Cambia "utente"
        String password = "password"; // Cambia "password"
        String query = "SELECT * FROM biglietti WHERE id = ?";  //migliora per SQL INJECTIONS

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Imposta il valore del parametro nella query
            stmt.setString(1, readQR().getId());

            // Esegue la query
            ResultSet rs = stmt.executeQuery();

            // Elabora i risultati
            while (rs.next()) {
                if (rs.getInt("Gruccia") != 0) {System.out.println("Gruccia n°" + rs.getInt("Gruccia") ); break;} else { System.out.println("Nessuna gruccia è stata assegnata");};
            }

        } catch (SQLException e) {
            System.err.println("Errore SQL: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
            e.printStackTrace();
        }finally {
            //chiusura connessione
            }
        System.out.println("Reading QR code at entrance banco guardaroba: " + getNumeroBanco());
        return true;

    }
    }
