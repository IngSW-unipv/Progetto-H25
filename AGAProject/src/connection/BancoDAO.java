package connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class BancoDAO {
	private Connection conn;
	

	public BancoDAO() {
		super();
		
	}
	
	
	//PRINT TUTTI I BANCHI
	public ArrayList<Banco> selectAll (){
		
		ArrayList<Banco> result = new ArrayList<>();
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		Banco b;
	
		try{
			st1 = conn.createStatement();
			String query="SELECT * from banco ";
			rs1=st1.executeQuery(query);
	
			while(rs1.next()){
				Evento evento=new Evento(rs1.getDate(4), null,0);//farlo col search by data
				boolean ing=rs1.getBoolean(2);
				if(ing)
					b=new BancoIngresso(rs1.getInt(1),evento);
				else b=new BancoGuardaroba(rs1.getInt(1),evento,10,10);
				
	
				result.add(b);
				System.out.println(b.toString());
			}
		}catch (Exception e){e.printStackTrace();}
	
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	public static void main(String []args)  {
		BancoDAO persona=new BancoDAO();
		//persona.selectAll();
		persona.selectAll();

		/*System.out.println(sql);
		 persona.selectByData(sql);*/
		//Evento e=new Evento(sql,"milano",130);
		//persona.addEvento(e);
		
		
		
	}

}
