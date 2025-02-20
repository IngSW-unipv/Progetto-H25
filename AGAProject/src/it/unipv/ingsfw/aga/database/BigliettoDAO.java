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

    /*private static final String INSERISCI_BIGLIETTO_SQL =
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
    }*/
    
    
    //TROVA CREATORE DAL BIGLIETTO
    public Persona getCreatoreBiglietto(Biglietto biglietto) {
    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Persona persona;
		
		try{
			st1 = conn.createStatement();
			String query="SELECT * from biglietto where id= \""+biglietto.getQRCodeId()+"\";";
			rs1=st1.executeQuery(query);
	
			rs1.next();
			persona=new Persona(rs1.getString(3), null, null, null);
			
			PersonaDAO p=new PersonaDAO();
			persona=p.searchByCF(persona);
			
			DBConnection.closeConnection(conn);
			return persona;
			
		}catch (Exception e){
			e.printStackTrace();
			persona=new Persona();
			DBConnection.closeConnection(conn);
			return persona;
		}
    }
    
    
    //CREA BIGLIETTO
	 public void creaBiglietto(Biglietto biglietto) {
	    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		
		try{
			st1 = conn.createStatement();
			String query="INSERT INTO BIGLIETTO VALUES(\""+biglietto.getQRCodeId()+"\", '"+biglietto.getAccessoEffettuato()+
					"', \""+biglietto.getCodiceFiscaleCreatore()+"\", '"+biglietto.getDataEvento()+"' ,"+biglietto.getNumeroGruccia()+
					", \""+biglietto.getDescrizioneGruccia()+"\", \""+biglietto.getNome()+"\", \""+	biglietto.getCognome()+
					"\", \""+biglietto.getEmail()+"\");";
			st1.executeUpdate(query);
		
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);			
	}
	 
	 
	 //SET STATO BIGLIETTO
	 public void setStatoBiglietto(Biglietto biglietto) {
	    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		int b;//nel db boolean =1 o 0
		if(biglietto.getAccessoEffettuato())b=1;
		else b=0;
		
		try{
			st1 = conn.createStatement();
			String query="UPDATE BIGLIETTO SET STATO='"+b+"' WHERE ID=\""+biglietto.getQRCodeId()+"\";";
			st1.executeUpdate(query);
		
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);			
	}
	 
	 //GET STATO BIGLIETTO
	 public int getStatoBiglietto(Biglietto biglietto) {
	    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		int b=2;//nel db boolean =1 o 0
		//B=0 NON EFFETTUATO
		//B=1 EFFETTUATO
		//B=2 ERRORE
		
		try{
			st1 = conn.createStatement();
			String query="SELECT * FROM BIGLIETTO WHERE ID=\""+biglietto.getQRCodeId()+"\";";
			rs1=st1.executeQuery(query);
			
			rs1.next();
			if(rs1.getBoolean(2))
				b=1;
			else b=0;
		
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);			
		return b;
	}
	 
	 
	 //SET GRUCCIA
	 public boolean setGruccia(Biglietto biglietto) {
    	boolean result=false;
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		
		try{
			st1 = conn.createStatement();
			String query="UPDATE BIGLIETTO SET GRUCCIA="+biglietto.getNumeroGruccia()+" WHERE ID=\""+biglietto.getQRCodeId()+"\";";
			st1.executeUpdate(query);
			result=true;
		
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);	
		return result;
	}
	 
	 
	 
	 //GET GRUCCIA
	 public int getGruccia(Biglietto biglietto) {
	    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		int result=-1;
		try{
			st1 = conn.createStatement();
			String query="SELECT * FROM BIGLIETTO WHERE ID=\""+biglietto.getQRCodeId()+"\";";
			rs1=st1.executeQuery(query);
			
			rs1.next();
			result=rs1.getInt(5);
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);			
		return result;
	}
    
	 
	//GET NUMERO GRUCCE ASSEGNATE
	 public int getNumeroGrucciaAssegnate (Evento evento) {
	    	
	    	conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
			int result=-1;
			try{
				st1 = conn.createStatement();
				String query="SELECT MAX(GRUCCIA) FROM BIGLIETTO WHERE DATA_EVENTO='"+evento.getData()+";";
				rs1=st1.executeQuery(query);
				
				rs1.next();
				result=rs1.getInt(0);
			}catch (Exception e){
				e.printStackTrace();
			}
			DBConnection.closeConnection(conn);			
			return result;
		}
	 
    
    public static void main(String []args) {
		BigliettoDAO b=new BigliettoDAO();
		Persona a;
		String code="1b4b76e0-3c14-46b9-9685-e11b6c12e084";
		Biglietto i=new Biglietto(code,false);
		b.setStatoBiglietto(i);
		//a=b.getCreatoreBiglietto(i);
		//System.out.println(a.toString());
	}
	

    
}

