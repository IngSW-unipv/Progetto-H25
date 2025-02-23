package it.unipv.ingsfw.aga.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.persone.*;
import connection.DBConnection;


public class PersonaDAO implements IPersonaDAO {
	private Connection conn;
	//private String schema; tolto ripetitivo difficile adattivita
	
	
	public PersonaDAO() {
		super();
	}
	
	
	//SELECT DI TUTTE LE PERSONE CON TUTTI I DATI
	public ArrayList<Persona> selectAll (){
		
		ArrayList<Persona> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Persona p;
		boolean org;

		try{
			st1 = conn.createStatement();
			String query="SELECT * from persona ;";
			rs1=st1.executeQuery(query);

			while(rs1.next()){
				
				org=rs1.getBoolean(6);
				if(org)
					p=new Organizzatore(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5));
				else p=new Staffer(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5));

				result.add(p);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//SELECT CF NOME E COGNOME E TIPO
	public ArrayList<Persona> selectCFNomeCognome (){
			
			ArrayList<Persona> result = new ArrayList<>();
			
			conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
			Persona p;
			boolean org;
			
			try
			{
				st1 = conn.createStatement();
				String query="SELECT * from persona ;";
				rs1=st1.executeQuery(query);
	
				while(rs1.next()){
					
					org=rs1.getBoolean(6);
					if(org)
						p=new Organizzatore(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
							rs1.getString(5));
					else p=new Staffer(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
							rs1.getString(5));
	
					result.add(p);
					//System.out.println(p.toString());
				}
			}catch (Exception e){
				e.printStackTrace();
			}
	
			DBConnection.closeConnection(conn);
			return result;
	}
	
	
	//SELECT LO STAFF CF NOME COGNOME 
	public ArrayList<Persona> selectStaffCFNomeCognome (){
		
		ArrayList<Persona> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try{
			st1 = conn.createStatement();
			String query="SELECT * from persona where organizzatore='0';";
			rs1=st1.executeQuery(query);

			while(rs1.next()){
				Persona p=new Staffer(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5));

				result.add(p);
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//SELECT GLI ORGANIZZATORI CF NOME COGNOME 
	public ArrayList<Persona> selectOrganizzatoreCFNomeCognome (){
		
		ArrayList<Persona> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try{
			st1 = conn.createStatement();
			String query="SELECT * from persona where organizzatore='1';";
			rs1=st1.executeQuery(query);

			while(rs1.next()){
				Persona p=new Organizzatore(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5));

				result.add(p);
				//System.out.println(p.toString());
			}
		}catch (Exception e){
			e.printStackTrace();
		}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//AGGIUNGI ORGANIZZATORE
	public boolean addOrganizzatore (Organizzatore persona){
			
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		boolean result=false;
		
		try{
			String query="INSERT INTO PERSONA VALUES(?,?,?,?,?,?);";
			st1 = conn.prepareStatement(query);
			st1.setString(1, persona.getCodiceFiscale());
			st1.setString(2, persona.getNome());
			st1.setString(3, persona.getCognome());
			st1.setString(4, persona.getEmail());
			st1.setString(5, persona.getPassword());
			st1.setBoolean(6, true);
			st1.executeUpdate();
			result=true;	
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//AGGIUNGI STAFFER
	public boolean addStaffer (Staffer persona){
		
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		boolean result=false;
		
		try{
			
			String query="INSERT INTO PERSONA VALUES(?,?,?,?,?,?);";
			st1 = conn.prepareStatement(query);
			st1.setString(1, persona.getCodiceFiscale());
			st1.setString(2, persona.getNome());
			st1.setString(3, persona.getCognome());
			st1.setString(4, persona.getEmail());
			st1.setString(5, persona.getPassword());
			st1.setBoolean(6, false);
			st1.executeUpdate();
			result=true;	
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//SEARCH BY CF (SIA ORGANIZZATORI CHE STAFFER)
	public Persona searchByCF (Persona persona){
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Persona p;

		try{
			st1 = conn.createStatement();
			String query="SELECT * from persona where CF=\""+persona.getCodiceFiscale()+"\";";
			rs1=st1.executeQuery(query);

			rs1.next();
			if(rs1.getBoolean(6))
				p=new Organizzatore(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5));
			else p=new Staffer(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
					rs1.getString(5));
			DBConnection.closeConnection(conn);
			return p;
							
		}catch (Exception e){
			//e.printStackTrace(); 
			DBConnection.closeConnection(conn);
			return p=new Persona();//nessun cf corrispondente trovato	
		}		
	}

	
	//SEARCH BY EMAIL -> LOGIN
	public Persona login (Persona persona){
			
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		ResultSet rs1;
		Persona p;

		try{
			
			String query="SELECT * from persona where email=?;";
			st1 = conn.prepareStatement(query);
			st1.setString(1, persona.getEmail());

			rs1=st1.executeQuery();
	
			rs1.next();
			if(rs1.getBoolean(6))
				p=new Organizzatore(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5));
			else p=new Staffer(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
					rs1.getString(5));
			DBConnection.closeConnection(conn);
			return p;
							
		}catch (Exception e){
			e.printStackTrace(); 					
			DBConnection.closeConnection(conn);
			return p=new Persona();//nessun cf corrispondente trovato	
		}		
	}
	
	
	//GET CF BY EMAIL -> PER LA CREAZIONE DEL BIGLIETTO
	public Persona getCodiceFiscaleByEmail (Persona persona){
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Persona p;

		try{
			st1 = conn.createStatement();
			String query="SELECT * from persona where EMAIL=\""+persona.getEmail()+"\";";
			rs1=st1.executeQuery(query);
	
			rs1.next();
			p=new Persona(rs1.getString(1),null);
							
		}catch (Exception e){
			//e.printStackTrace(); 
			p=new Persona();//nessun cf corrispondente trovato	
		}	
		DBConnection.closeConnection(conn);
		return p;
	}
	
	
	
	public static void main(String []args) {
		PersonaDAO persona=new PersonaDAO();
		/*ArrayList<Persona> result = persona.selectOrganizzatoreCFNomeCognome();
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i));
		Persona p=new Persona("292-71-9542",null,null,null);
		Persona a;
		try {
			a=(Organizzatore)persona.searchByCF(p);
		}
		catch(Exception e) {
			a=(Staffer)persona.searchByCF(p);
		}
		System.out.println(a.toString());*/
		
	}
	
}


