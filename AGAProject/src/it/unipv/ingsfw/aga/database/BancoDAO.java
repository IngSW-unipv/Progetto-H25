package it.unipv.ingsfw.aga.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;

import it.unipv.ingsfw.aga.model.banco.*;
import it.unipv.ingsfw.aga.model.evento.Evento;
import connection.DBConnection;



public class BancoDAO implements IBancoDAO{
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
		Evento dataEvento;
	
		try{
			st1 = conn.createStatement();
			String query="SELECT * from banco ";
			rs1=st1.executeQuery(query);
	
			while(rs1.next()){
				dataEvento=new Evento(rs1.getDate(4), null,0,false);//farlo col search by data
				boolean ing=rs1.getBoolean(2);
				if(ing)
					b=new BancoIngresso(rs1.getInt(1),dataEvento);
				else b=new BancoGuardaroba(rs1.getInt(1),rs1.getInt(5),dataEvento);
	
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
