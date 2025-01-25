
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


class MysqlCon{  
	public static Connection starConnection(Connection conn, String database){  
		String DbDriver=null;
		String DbURL=null;
		String username=null;
		String password=null;
	
		DbDriver="com.mysql.jdbc.Driver";
		DbURL="jdbc:mysql://localhost:3306/dbproject";
		username="root";
		password="tessore";
		
		if(isOpen(conn))
			closeConnection(conn);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(DbURL, username, password);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
		
	}
	
	
	public static Connection closeConnection(Connection conn) {
		if(!isOpen(conn))
			return null;
		try {
			conn.close();
			conn=null;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;	
		}
		return conn;	
	}
	
	
	public static boolean isOpen(Connection conn) {
		if(conn==null)
			return false;
		else return true;
	}
	
	
	//problema ping del db risolto con nomedb?serverTimezone=UTC
	
	
	
}
	
	
	
	
		
		