package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class PersonaDAO implements IPersonaDao {
	private Connection conn;
	//private String schema; tolto ripetitivo difficile adattivita
	
	
	public PersonaDAO() {
		super();
		//this.schema = "PROVA";
	}
	
	
	//PRINT DI TUTTE LE PERSONE CON TUTTI I DATI
	public ArrayList<Persona> selectAll (){
		
		ArrayList<Persona> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from persona ;";
			rs1=st1.executeQuery(query);

			while(rs1.next()){
				Persona p=new Persona(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5),rs1.getBoolean(6),rs1.getBoolean(7));

				result.add(p);
				System.out.println("CF: "+rs1.getString(1)+", Nome: "+ rs1.getString(2)+", Cognome: "+rs1.getString(3)
						+", Email: "+rs1.getString(4)+", Password: "+rs1.getString(5)+"Staff: "+rs1.getBoolean(6)
						+", Organizzatore: "+rs1.getBoolean(7));
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//PRINT CF NOME E COGNOME
	public ArrayList<Persona> selectCFNomeCognome (){
			
			ArrayList<Persona> result = new ArrayList<>();
			
			conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
	
			try
			{
				st1 = conn.createStatement();
				String query="SELECT * from persona ;";
				rs1=st1.executeQuery(query);
	
				while(rs1.next()){
					Persona p=new Persona(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
							rs1.getString(5),rs1.getBoolean(6),rs1.getBoolean(7));
	
					result.add(p);
					System.out.println(p.toString());
				}
			}catch (Exception e){e.printStackTrace();}
	
			DBConnection.closeConnection(conn);
			return result;
	}
	
	
	//PRINT LO STAFF CF NOME COGNOME 
	public ArrayList<Persona> selectStaffCFNomeCognome (){
		
		ArrayList<Persona> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from persona ;";
			rs1=st1.executeQuery(query);

			while(rs1.next()){
				Persona p=new Persona(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5),rs1.getBoolean(6),rs1.getBoolean(7));

				result.add(p);
				if(rs1.getBoolean(6))
					System.out.println(p.toString());
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//PRINT GLI ORGANIZZATORI CF NOME COGNOME 
	public ArrayList<Persona> selectOrganizzatoreCFNomeCognome (){
		
		ArrayList<Persona> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from persona where organizzatore='1';";
			rs1=st1.executeQuery(query);

			while(rs1.next()){
				Persona p=new Persona(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5),rs1.getBoolean(6),rs1.getBoolean(7));

				result.add(p);
				if(rs1.getBoolean(7))
					System.out.println(p.toString());
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//AGGIUNGI PERSONA
	public void addPersona (Persona persona){
			
			conn=DBConnection.startConnection(conn);
			Statement st1;
			
			int staff=persona.getStaff()? 1 : 0;
			int organizzatore=persona.getOrganizzatore()? 1 : 0;
	
			try
			{
				st1 = conn.createStatement();
				String query="INSERT INTO PERSONA VALUES(\""+persona.getCF()+"\","+"\""+persona.getNome()+"\","+
						"\""+persona.getCognome()+"\","+"\""+persona.getEmail()+"\","+"\""+persona.getPassword()+"\","
						+"'"+staff+"','"+organizzatore+"');";
			st1.executeUpdate(query);
	
		}catch (Exception e){e.printStackTrace();}
	
		DBConnection.closeConnection(conn);
	}
	
	
	//SEARCH BY CF
	public Persona searchByCF (Persona persona){
		
		Persona p=new Persona("0000",null,null,null,null,false,false);
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from persona where CF=\""+persona.getCF()+"\";";
			rs1=st1.executeQuery(query);

			rs1.next();
			Persona p1=new Persona(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5),rs1.getBoolean(6),rs1.getBoolean(7));
			DBConnection.closeConnection(conn);
			return p1;
				
			
		}catch (Exception e){
			e.printStackTrace(); 
			
			DBConnection.closeConnection(conn);
			return p;
			
		}		
	}

	
	
	public static void main(String []args) {
		PersonaDAO persona=new PersonaDAO();
		persona.selectOrganizzatoreCFNomeCognome();
		//persona.selectStaffCFNomeCognome();
		//Persona persona1=new Persona("256-26-5863","fabrizio","amicis","unipv@","",false,false);
		//persona.addPersona(persona1);
		Persona p=new Persona("256-26-5863",null,null,null,null,false,false);
		Persona a=new Persona(persona.searchByCF(p));
		System.out.println(a.toString());
	}
	
}


