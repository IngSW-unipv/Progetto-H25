package connection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



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
	
			try{
				st1 = conn.createStatement();
				String query="SELECT * from evento ";
			rs1=st1.executeQuery(query);
	
			while(rs1.next()){
				Evento ev=new Evento(rs1.getDate(1), rs1.getString(2),rs1.getInt(3));
	
				result.add(ev);
				System.out.println(ev.toString());
			}
		}catch (Exception e){e.printStackTrace();}
	
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//PRINT BY LUOGO
	public ArrayList<Evento> selectByLuogo (String luogo){
		
		ArrayList<Evento> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
	
		try{
			st1 = conn.createStatement();
			String query="SELECT * from evento where luogo= '"+luogo+"'";
			rs1=st1.executeQuery(query);
	
			while(rs1.next()){
				Evento ev=new Evento(rs1.getDate(1), rs1.getString(2),rs1.getInt(3));
	
				result.add(ev);
				System.out.println(ev.toString());
			}
		}catch (Exception e){e.printStackTrace();}
	
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//PRINT BY DATA
	public Evento searchByData (Evento data){
					
			conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
		
			try{
				st1 = conn.createStatement();
				String query="SELECT * from evento where data= '"+data.getData()+"'";
			rs1=st1.executeQuery(query);
	
			rs1.next();
			Evento ev=new Evento(rs1.getDate(1), rs1.getString(2),rs1.getInt(3));	
			return ev;
			
		}catch (Exception e){
			e.printStackTrace();
			Evento evento=new Evento(null,null,0);
			DBConnection.closeConnection(conn);
			return evento;			
		}
	
	}
	
	
	//AGGIUNGI EVENTO
	public void addEvento (Evento evento){
				
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
	
		try{
			st1 = conn.createStatement();
			String query="INSERT INTO EVENTO VALUES('"+evento.getData()+"',\""+evento.getLuogo()+"\","+evento.getCapacita()+");";
			st1.executeUpdate(query);
	
		}catch (Exception e){e.printStackTrace();}
	
		DBConnection.closeConnection(conn);
		
	}
	
	
	public static void main(String []args) throws ParseException {
		EventoDAO persona=new EventoDAO();
		//persona.selectAll();
		String string = "2025-03-17";
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date parsed = format.parse(string);
		 java.sql.Date sql = new java.sql.Date(parsed.getTime());
		 Evento e=new Evento(sql,"",0);
		 System.out.println(persona.searchByData(e));

		/*System.out.println(sql);
		 persona.selectByData(sql);*/
		//Evento e=new Evento(sql,"milano",130);
		//persona.addEvento(e);
		
		
		
	}

}
