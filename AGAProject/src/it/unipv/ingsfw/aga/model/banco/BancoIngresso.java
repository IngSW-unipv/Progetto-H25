package it.unipv.ingsfw.aga.model.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import it.unipv.ingsfw.aga.model.evento.Evento;


public class BancoIngresso extends Banco {
    public BancoIngresso(int numeroBanco, Evento evento) {
        super(numeroBanco, evento);
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
	            if (rs.getBoolean("Stato")) {invalidateQr(qr); System.out.println("Biglietto valido"); break;} else { qrCodeinvalido(); break;}
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
	    System.out.println("Validating QR code at entrance banco: " + getNumeroBanco());
	    return true;
	}

    public boolean invalidateQr(QrCode qr){
        String url = "jdbc:mysql://localhost:3306/nome_database";
        String username = "utente";
        String password = "password";
        String updateQuery = "UPDATE biglietti SET Stato = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            // Imposta i parametri della query
            stmt.setBoolean(1, false); // Imposta il valore del booleano a false
            stmt.setString(2, qr.getId());

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
        return true; //TODO: inserire l'esito della verifica
    }

    public boolean qrCodeinvalido(){
        System.out.println("Biglietto già convalidato");
        return false;
    }

    public boolean accesso(){
        QrCode qr = readQR();
        return validateQr(qr);
    }

    public boolean accesso(String code){
        QrCode qr = readQR(code);
        return validateQr(qr);
    }
    
    @Override
    public String toString() {
    	return "[Banco]\n" +
                "Tipo: Ingresso\n" +
                "Numero banco: " + getNumeroBanco() +"\n" +
                "Evento: " + getDataEvento() + "\n";
    }

}




