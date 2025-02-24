package it.unipv.ingsfw.aga.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;

import connection.DBConnection;
import it.unipv.ingsfw.aga.model.evento.Evento;



public class EventoDAO implements IEventoDAO {
	private Connection conn;
	

	public EventoDAO() {
		super();
	}
	
	
	//PRINT TUTTI I DATI DELL'EVENTO
	public ArrayList<Evento> selectAll (){
			
		ArrayList<Evento> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Evento ev;

		try{
			st1 = conn.createStatement();
			String query="SELECT * from evento ";
			rs1=st1.executeQuery(query);
	
			while(rs1.next()){
				ev=new Evento(rs1.getDate(1), rs1.getString(2),rs1.getInt(3),rs1.getBoolean(4));
	
				result.add(ev);
					//System.out.println(ev.toString());
			}
		}catch (Exception e){
			e.printStackTrace();}
	
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//PRENDI TUTTE LE DATE
	public ArrayList<String> getAllDate (){
			
		ArrayList<String> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		String ev;

		try{
			st1 = conn.createStatement();
			String query="SELECT * from evento ";
			rs1=st1.executeQuery(query);
	
			while(rs1.next()){
				ev=""+rs1.getDate(1);
	
				result.add(ev);
			}
		}catch (Exception e){
			e.printStackTrace();}
	
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//PRINT BY LUOGO
	public ArrayList<Evento> selectByLuogo (String luogo){
		
		ArrayList<Evento> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Evento ev;
		
	
		try{
			st1 = conn.createStatement();
			String query="SELECT * from evento where luogo= '"+luogo+"'";
			rs1=st1.executeQuery(query);
	
			while(rs1.next()){
				ev=new Evento(rs1.getDate(1), rs1.getString(2),rs1.getInt(3),rs1.getBoolean(4));
	
				result.add(ev);
				//System.out.println(ev.toString());
			}
		}catch (Exception e){
			e.printStackTrace();}
	
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//SERCH BY DATA
	public Evento searchByData (Evento data){
					
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Evento ev;
		
		try{
			st1 = conn.createStatement();
			String query="SELECT * from evento where data= '"+data.getData()+"'";
			rs1=st1.executeQuery(query);
		
			rs1.next();
			ev=new Evento(rs1.getDate(1), rs1.getString(2),rs1.getInt(3),rs1.getBoolean(4));	
			DBConnection.closeConnection(conn);
			return ev;
		
		}catch (Exception e){
			//e.printStackTrace();
			Evento evento=new Evento(null,null,0,false);
			DBConnection.closeConnection(conn);
			return evento;			
		}
	}
	
	
	//GET CAPACITA BY EVENTO
	public int getCapacitaByEvento (Evento data){
					
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		int capacita=-1;
		
		try{
			st1 = conn.createStatement();
			String query="SELECT * from evento where data= '"+data.getData()+"'";
			rs1=st1.executeQuery(query);
		
			rs1.next();
			capacita=rs1.getInt(3);	
			
		}catch (Exception e){
			//e.printStackTrace();			
		}
		DBConnection.closeConnection(conn);
		return capacita;
	}

	
	//AGGIUNGI EVENTO 
	public boolean addEvento (Evento evento){
				
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		boolean result=false;
		String data;
		
		try{
			data=""+evento.getData();
			String query="INSERT INTO EVENTO VALUES(?,?,?,?);";
			st1 = conn.prepareStatement(query);
			st1.setString(1, data);
			st1.setString(2, evento.getLocation());
			st1.setInt(3, evento.getMaxPartecipanti());
			st1.setBoolean(4, evento.getVenditeAperte());
			
			st1.executeUpdate();
			result=true;
	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Impossibile aggiungerlo data già occupata");
			DBConnection.closeConnection(conn);
		}
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//SET VENDITE APERTE BY DATA
	public void setVenditeAperte (Evento evento){
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		int b;//nel db boolean =1 o 0
		if(evento.getVenditeAperte()==true)b=1;
		else b=0;
	
		try{
			st1 = conn.createStatement();
			String query="UPDATE EVENTO SET VENDITEAPERTE='"+b +"' WHERE DATA='"+evento.getData()+"';";
			st1.executeUpdate(query);
	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Impossibile: evento non trovato");
			DBConnection.closeConnection(conn);
		}
	}
	
	
	//GET VENDITE APERTE BY DATA
	public boolean getVenditeAperte (Evento evento){
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		boolean b;
	
		try{
			st1 = conn.createStatement();
			String query="SELECT * FROM EVENTO WHERE DATA='"+evento.getData()+"'";
			rs1=st1.executeQuery(query);
			
			rs1.next();
			b=rs1.getBoolean(4);
			DBConnection.closeConnection(conn);
			return b;
	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Impossibile: evento non trovato");
			DBConnection.closeConnection(conn);
			return false;
		}
	}
	
	
	
	public static void main(String []args) throws ParseException {
		EventoDAO persona=new EventoDAO();
		//persona.selectAll();
		String string = "2022-06-22";
		
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date parsed = format.parse(string);
		 java.sql.Date sql = new java.sql.Date(parsed.getTime());
		 //Evento e=new Evento(sql,"",0);
		 //System.out.println(persona.searchByData(e));

		/*System.out.println(sql);
		 persona.selectByData(sql);*/
		Evento e=new Evento(sql,"milano",130,false);
		boolean b=persona.getVenditeAperte(e);
		System.out.println(b);
		Evento e1=new Evento(sql,"milano",130,true);
		persona.setVenditeAperte(e1);
		
		
	}

}
