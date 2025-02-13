package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.util.Properties;



public class DBConnection {
	
	private static final String PROPERTYDBDRIVER = "DBDRIVER";
	private static final String PROPERTYDBURL = "DBURL";
	private static final String PROPERTYNAME = "db_usn"; 
	private static final String PROPERTYPSW = "db_psw"; 
	private static final String PROPERTYSCHEMA = "schema"; 
	private static String username;
	private static String password;
	private static String dbDriver;
	private static String dbURL;
	private static String schema;
	private static DBConnection conn;
	
	private static void init() {
		Properties p = new Properties(System.getProperties());
		try {
			p.load(new FileInputStream("src/properties/properties"));
			username=p.getProperty(PROPERTYNAME);
			password=p.getProperty(PROPERTYPSW);
			dbDriver =p.getProperty(PROPERTYDBDRIVER);
			dbURL =p.getProperty(PROPERTYDBURL);
			schema =p.getProperty(PROPERTYSCHEMA);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//APERTURA CONNESSIONE
	public static Connection startConnection(Connection conn)
	{
		init();
		
		if ( isOpen(conn) )
			closeConnection(conn);
	
		try 
		{
			//schema è il nome del mio db -> scelto di mettere in properties 
			dbURL=String.format(dbURL,schema); 
			//sistema il file properties 
			Class.forName(dbDriver);
			
			//APERTURA CONNESSIONE
			conn = DriverManager.getConnection(dbURL, username, password);
			//System.out.println("Connessione al DB: "+dbURL);

		}
		catch (Exception e) 
		{
			System.out.println("Errore connessione al DB");
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	
	//CONTROLLO CONNESSIONE APERTA
	public static boolean isOpen(Connection conn){
		if (conn == null)
			return false;
		else
			return true;
	}

	
	//CHIUSURA CONNESSIONE
	public static Connection closeConnection(Connection conn){
		if ( !isOpen(conn) )
			return null;
		try {
			conn.close();
			conn = null;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
}

	


