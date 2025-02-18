package it.unipv.ingsfw.aga.database;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
			return ev;
		
		}catch (Exception e){
			//e.printStackTrace();
			Evento evento=new Evento(null,null,0,false);
			DBConnection.closeConnection(conn);
			return evento;			
		}
	
	}
	
	
	//AGGIUNGI EVENTO 
	public void addEvento (Evento evento){
				
		conn=DBConnection.startConnection(conn);
		Statement st1;
	
		try{
			st1 = conn.createStatement();
			String query="INSERT INTO EVENTO VALUES('"+evento.getData()+"',\""+evento.getLocation()+"\","+evento.getMaxPartecipanti()+");";
			st1.executeUpdate(query);
	
		}catch (Exception e){
			//e.printStackTrace();
			System.out.println("Impossibile aggiungerlo data già occupata");
		}
	
		DBConnection.closeConnection(conn);
	}
	
	//NON APPLICABILE PER CHIAVE ESTERNA -> FINAL
	/*public void changeData (Evento evento, Evento nuovaData){
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
	
		try{
			st1 = conn.createStatement();
			String query="UPDATE evento SET data='"+nuovaData.getData()+"' where data='"+evento.getData()+"';";
			//FOREIGN KEY PROBLEM
			st1.executeUpdate(query);
	
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(evento.getData());
			System.out.println(nuovaData.getData());
			System.out.println("Impossibile aggiungerlo data già occupata");
		}
	
		DBConnection.closeConnection(conn);
	}*/
	
	
	
	public static void main(String []args) throws ParseException {
		EventoDAO persona=new EventoDAO();
		//persona.selectAll();
		String string = "2022-06-22";
		String string2 = "2022-06-23";
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date parsed = format.parse(string);
		 java.sql.Date sql = new java.sql.Date(parsed.getTime());
		 //Evento e=new Evento(sql,"",0);
		 //System.out.println(persona.searchByData(e));

		/*System.out.println(sql);
		 persona.selectByData(sql);*/
		Evento e=new Evento(sql,"milano",130,false);
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed2 = format2.parse(string2);
		java.sql.Date sql2 = new java.sql.Date(parsed2.getTime());
		Evento nd=new Evento(sql2,null,0,false);
		//e.setData(nd);
		//Evento a=new Evento (persona.searchByData(nd));
		///System.out.println(a);
		
		//persona.addEvento(e);
	}

}
