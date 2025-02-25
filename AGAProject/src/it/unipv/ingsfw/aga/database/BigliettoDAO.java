	package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import connection.DBConnection;

public class BigliettoDAO implements IBigliettoDAO {

    private Connection conn;

    public BigliettoDAO() {
        super();
    }
   
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
	 public boolean creaBiglietto(Biglietto biglietto) {
	    	
		 conn=DBConnection.startConnection(conn);
			PreparedStatement st1;
			boolean result=false;
			String data=""+biglietto.getDataEvento();
			
			try{
				String query="INSERT INTO BIGLIETTO VALUES(?, ?, ?, ? ,?, ?, ?, ?, ?);";
				st1 = conn.prepareStatement(query);
				st1.setString(1, biglietto.getQRCodeId());
				st1.setBoolean(2, biglietto.getAccessoEffettuato());
				st1.setString(3,biglietto.getCodiceFiscaleCreatore());
				st1.setString(4, data);
				st1.setInt(5,biglietto.getNumeroGruccia());
				st1.setString(6,biglietto.getDescrizioneGruccia());
				st1.setString(7,biglietto.getNome());
				st1.setString(8,biglietto.getCognome());
				st1.setString(9,biglietto.getEmail());
				
				st1.executeUpdate();
				result=true;
			}catch (Exception e){
				System.out.println("Errore: potrebbe esserci un errore nei dati selezionati");
				e.printStackTrace();
			}
			DBConnection.closeConnection(conn);	
			return result;
	 }
	 
	 
	//BIGLIETTI TOTALI NELL'EVENTO
	 public int getBigliettiTotoaliByEvento(Evento evento) {
	    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		int result=-1;
		
		try{
			st1 = conn.createStatement();
			String query="SELECT COUNT(*) FROM BIGLIETTO WHERE DATA_EVENTO='"+evento.getData()+"'";
			rs1=st1.executeQuery(query);
			
			rs1.next();
			result=rs1.getInt(1);
		}catch (Exception e){
			System.out.println("Errore: potrebbe esserci un errore nei dati selezionati");
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);	
		return result;
	}
	 
	 
	//GET BIGLIETTO DA QR
	 public Biglietto getBigliettoByQR(Biglietto biglietto) {
	    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Biglietto result;
		Persona persona;
		Evento evento;
		
		try{
			st1 = conn.createStatement();
			String query="SELECT * FROM BIGLIETTO WHERE ID=\""+biglietto.getQRCodeId()+"\";";
			rs1=st1.executeQuery(query);
			
			rs1.next();
			persona=new Persona(rs1.getString(3),null);
			evento=new Evento(rs1.getDate(4));
			result=new Biglietto(persona,rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(1),evento);
			
		}catch (Exception e){
			e.printStackTrace();
			result=null;
		}
		DBConnection.closeConnection(conn);	
		return result;
	}
	 
	 
	//GET TUTTI I QRCODE -> CONTROLLO CREAZIONE EVENTO
	 public ArrayList<String> getTuttiQRBiglietti() {
	    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		ArrayList <String> bigliettiQR= new ArrayList<>();
		
		try{
			st1 = conn.createStatement();
			String query="SELECT * FROM BIGLIETTO ;";
			rs1=st1.executeQuery(query);
			
			while(rs1.next()){
				String biglietto=rs1.getString(1);
				bigliettiQR.add(biglietto);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);	
		return bigliettiQR;
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
		//b=0 NON EFFETTUATO
		//b=1 EFFETTUATO
		//b=2 ERRORE
		
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
	 public int getNumeroGrucceAssegnate (Evento evento) {
	    	
	    	conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
			int result=-1;
			try{
				st1 = conn.createStatement();
				System.out.println(""+evento.getData());
				String query="SELECT MAX(GRUCCIA) FROM BIGLIETTO WHERE DATA_EVENTO='"+evento.getData()+"';";
				rs1=st1.executeQuery(query);
				
				rs1.next();
				result=rs1.getInt(1);
				
			}
			catch (Exception e){
				e.printStackTrace();
			}
			DBConnection.closeConnection(conn);			
			return result;
		}
	 
	 
	//GET NUMERO DI BIGLIETTI CREATI DALLO STESSO CREATORE (BY CF)
	 public int getNumeroBigliettiByCodiceFiscale(Persona persona,Evento evento) {
	    	
    	conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		int result=-1;
		try{
			st1 = conn.createStatement();
			String query="SELECT COUNT(*) FROM BIGLIETTO WHERE DATA_EVENTO='"+evento.getData()+"' AND CF_PERSONA=\""+
			persona.getCodiceFiscale()+"\";";
			rs1=st1.executeQuery(query);
			
			rs1.next();
			result=rs1.getInt(1);
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);			
		return result;
	}
	 
	 
	 //GET TUTTI I BIGLIETTI
	 public ArrayList<Biglietto> getInvitati(Evento eventoData) {
	    	
	    	conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
			ArrayList<Biglietto> biglietti=new ArrayList <>();
			Biglietto b;
			Persona persona;
			Evento evento;
			
			try{
				st1 = conn.createStatement();
				String query="SELECT * FROM BIGLIETTO WHERE DATA_EVENTO='"+eventoData.getData()+"';";
				rs1=st1.executeQuery(query);
				
				while(rs1.next()) {
					persona=new Persona(rs1.getString(3),null);
					evento=new Evento(rs1.getDate(4));
					b=new Biglietto(persona,rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(1),evento);
					biglietti.add(b);
				}
				
			}catch (Exception e){
				e.printStackTrace();
			}
			DBConnection.closeConnection(conn);			
			return biglietti;
	}
	//Metodo per aggiungere un biglietto al database tramite il Model (Ale)
	public boolean addBiglietto(Biglietto biglietto) {
		conn = DBConnection.startConnection(conn);
		boolean result = false;
		String query = "INSERT INTO BIGLIETTO (ID, STATO, CF_PERSONA, DATA_EVENTO, GRUCCIA, DESCRIZIONEGRUCCIA, NOME, COGNOME, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, biglietto.getQRCodeId());
			stmt.setBoolean(2, biglietto.getAccessoEffettuato());
			stmt.setString(3, biglietto.getCodiceFiscaleCreatore());
			stmt.setDate(4, new java.sql.Date(biglietto.getDataEvento().getTime()));
			stmt.setInt(5, biglietto.getNumeroGruccia());
			stmt.setString(6, biglietto.getDescrizioneGruccia());
			stmt.setString(7, biglietto.getNome());
			stmt.setString(8, biglietto.getCognome());
			stmt.setString(9, biglietto.getEmail());
			stmt.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnection(conn);
		}
		return result;
	}

    public static void main(String []args) throws ParseException {
		BigliettoDAO b=new BigliettoDAO();
		Persona a;
		//String code="1b4b76e0-3c14-46b9-9685-e11b6c12e084";
		///Biglietto i=new Biglietto(code,false);
		//b.setStatoBiglietto(i);
		//a=b.getCreatoreBiglietto(i);
		String c="9bde25f5-c9c1-40dc-98ea-7f5eb300a272";
		String dataName="1998-07-13";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(dataName);
		java.sql.Date data= new java.sql.Date(parsed.getTime());
		Evento evento=new Evento(data);
		
		System.out.println(""+b.getNumeroGrucceAssegnate(evento));
	}
	

    
}

