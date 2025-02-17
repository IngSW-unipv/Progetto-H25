package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DBConnection;

public class BigliettoDAO implements IBigliettoDAO {

    private Connection conn;

    public BigliettoDAO() {
        super();
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
    public Persona getPersona(Biglietto biglietto) {
    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Persona persona;
		
		try{
			st1 = conn.createStatement();
			String query="SELECT * from biglietto where id= \""+biglietto.getQRCodeId()+"\";";
			rs1=st1.executeQuery(query);
	
			rs1.next();
			persona=new Persona(rs1.getString(1), null, null, null);
			PersonaDAO p=new PersonaDAO();
			persona=p.searchByCF(persona);
			//System.out.println(ev.toString());
			DBConnection.closeConnection(conn);
			return persona;
		}catch (Exception e){
			e.printStackTrace();
			persona=new Persona();
			DBConnection.closeConnection(conn);
			return persona;
		}
    }
    
    public static void main(String []args) {
		BigliettoDAO b=new BigliettoDAO();
		Persona a;
		//QrCode codeQR= new QrCode();
		//Biglietto i=new Biglietto()
		//a=b.getPersona();
		//System.out.println(a.toString());
		//COME PASSO DA QR A STRING?
	}
	

    
}

