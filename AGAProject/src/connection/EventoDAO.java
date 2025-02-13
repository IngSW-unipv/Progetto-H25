package connection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


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
	public ArrayList<Evento> selectByData (Date data){
			
			ArrayList<Evento> result = new ArrayList<>();
			
			conn=DBConnection.startConnection(conn);
			Statement st1;
			ResultSet rs1;
		
			try{
				st1 = conn.createStatement();
				String query="SELECT * from evento where data= '"+data+"'";
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
	
	
	
	public static void main(String []args) throws ParseException {
		EventoDAO persona=new EventoDAO();
		//persona.selectAll();
		String string = "2024-01-12";
		 
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date parsed = format.parse(string);
		 java.sql.Date sql = new java.sql.Date(parsed.getTime());

		System.out.println(sql);
		 persona.selectByData(sql);
		
		
		
	}

}
