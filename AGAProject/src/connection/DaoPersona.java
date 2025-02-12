package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DaoPersona {
	private Connection conn;
	//private String schema; tolto ripetitivo difficile adattivita
	
	
	public DaoPersona() {
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
			String query="SELECT * from persona ";
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
				String query="SELECT * from persona ";
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
			String query="SELECT * from persona ";
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
			String query="SELECT * from persona ";
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
	
	
	
	public static void main(String []args) {
		DaoPersona persona=new DaoPersona();
		persona.selectCFNomeCognome();
		persona.selectStaffCFNomeCognome();
	}
	
}


