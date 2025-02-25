package it.unipv.ingsfw.aga.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
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
			String query="SELECT * from banco;";
			rs1=st1.executeQuery(query);
	
			while(rs1.next()){
				dataEvento=new Evento(rs1.getDate(4), null,0,false);
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
	
	
	//MAX GRUCCE DALLA DATA
	public int getMaxGrucce (Evento evento){
		
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		int result=-1;
	
		try{
			st1 = conn.createStatement();
			String query="SELECT * FROM BANCO WHERE DATA_EVENTO='"+evento.getData()+"';";
			rs1=st1.executeQuery(query);
			rs1.next();
			result=rs1.getInt(5);
	
		}catch (Exception e){
			e.printStackTrace();
		}
	
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//GET PROSSIMO NUMERO IDENTIFICATIVO DI BANCO
	public int getMaxtIdentificativoBanco() {
		conn=DBConnection.startConnection(conn);
		Statement st1;
		ResultSet rs1;
		int result=-1;
	
		try{
			st1 = conn.createStatement();
			String query="SELECT MAX(IDENTIFICATIVO) FROM BANCO;";
			rs1=st1.executeQuery(query);
			rs1.next();
			result=rs1.getInt(1);
	
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	//CREA BANCO 
	public boolean addBanco(Evento evento,BancoGuardaroba banco) {
		conn=DBConnection.startConnection(conn);
		PreparedStatement st1;
		boolean result=false;
		int id;
		
		try{
			String data=""+evento.getData();
			id=banco.getNumeroBanco()+1;
			String query="INSERT INTO BANCO VALUES(?, ?, ?, ? ,?);";
			st1 = conn.prepareStatement(query);
			st1.setInt(1, id);
			st1.setBoolean(2, false);
			st1.setBoolean(3, true);
			st1.setString(4, data);
			st1.setInt(5, banco.getMaxGrucce());
			
			st1.executeUpdate();
			result=true;
	
		}catch (Exception e){
			e.printStackTrace();
		}
		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	
	
	
	public static void main(String []args){
		BancoDAO persona=new BancoDAO();
		//persona.selectAll();
		//persona.selectAll();
		//System.out.println(persona.getMaxtIdentificativoBanco());
		/*System.out.println(sql);
		 persona.selectByData(sql);*/
		//Evento e=new Evento(sql,"milano",130);
		//persona.addEvento(e);
		/*int id=persona.getMaxtIdentificativoBanco();
		
		Evento e=new Evento("2012-05-10");
		BancoGuardaroba b=new BancoGuardaroba(id,200,e);
		persona.addBanco(e, b);*/
		
	}

}
