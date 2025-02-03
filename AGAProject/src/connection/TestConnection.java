package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connection.MysqlCon;


public class TestConnection {
	private String schema;
	private Connection conn;
	
	public TestConnection() {
		super();
		this.schema = "PROVA";
//		conn=DBConnection.startConnection(conn,schema);
	}
	
	
	public ArrayList<Persona> selectAll (){
		
		ArrayList<Persona> result = new ArrayList<>();
		
		conn=MysqlCon.startConnection(conn,schema);
		Statement st1;
		ResultSet rs1;
		System.out.println("aaa");

		try
		{
			st1 = conn.createStatement();
			String query="SELECT * from persona ";
			rs1=st1.executeQuery(query);

			while(rs1.next())
			{
				Persona f=new Persona(rs1.getString(1), rs1.getString(2),rs1.getString(3),rs1.getString(4), 
						rs1.getString(5),rs1.getBoolean(6),rs1.getBoolean(7));

				result.add(f);
				System.out.println(rs1.getString(1)+""+ rs1.getString(2)+""+rs1.getString(3));
			}
		}catch (Exception e){e.printStackTrace();}

		DBConnection.closeConnection(conn);
		return result;
	}
	
	
	public static void main(String []args) {
		TestConnection af=new TestConnection();
		af.selectAll();
	}
	
}


