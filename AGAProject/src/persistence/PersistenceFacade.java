package persistence;

import connection.DBConnection;
import java.sql.Connection;

import it.unipv.ingsfw.aga.database.IBigliettoDAO;
import it.unipv.ingsfw.aga.database.IPersonaDAO;
import it.unipv.ingsfw.aga.database.IEventoDAO;
import it.unipv.ingsfw.aga.database.IBancoDAO;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.persone.Persona;
import it.unipv.ingsfw.aga.model.persone.Staffer;
import it.unipv.ingsfw.aga.model.persone.Organizzatore;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.banco.Banco;

import it.unipv.ingsfw.aga.database.BigliettoDAO;
import it.unipv.ingsfw.aga.database.PersonaDAO;
import it.unipv.ingsfw.aga.database.EventoDAO;
import it.unipv.ingsfw.aga.database.BancoDAO;


public class PersistenceFacade {
	//private Connection conn;
	
	IBigliettoDAO iBigliettoDAO;
	IPersonaDAO iPersonaDAO;
	IEventoDAO iEventoDAO;
	IBancoDAO iBancoDAO;
	
	public PersistenceFacade()  {
		iBigliettoDAO=new BigliettoDAO();
		iPersonaDAO=new PersonaDAO();
		iEventoDAO=new EventoDAO();
		iBancoDAO=new BancoDAO();
		/*conn=DBConnection.startConnection(conn);
		iMovieDao = new MovieRdbDao(connection);
		iRoomDao = new RoomRdbDao(connection);
		iProjectionDao = new ProjectionRdbDao(connection);
		iCouponDao = new CouponRdbDao(connection);
		iDiscountDao = new DiscountRdbDao(connection);
		iOccupiedSeatDao = new OccupiedSeatRdbDao(connection);
		iReservationDao = new ReservationRdbDao(connection);
		iCinemaDao = new CinemaRdbDao(connection);*/
	}///noi la connessione la facciamo singola 
	
	//SET STATO DEL BIGLIETTO (STRING E BOOL)
	public void setStatoBiglietto (String codeQR, boolean stato) {
		try {
			Biglietto biglietto=new Biglietto(codeQR, stato);
			iBigliettoDAO.setStatoBiglietto(biglietto);//iMovieDao.getMovie(id);se
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//VERIFICA LOGIN
	public int login (String email, String password){
		int result=0;
		//RETURN 1=STAFFER
		//RETURN 2=ORGANIZZATORE
		//RETURN NULL=ERRORE
		try {
			Persona persona=new Persona(email);
			Persona personaDB;
			
			try {
				personaDB=(Organizzatore)iPersonaDAO.login(persona);
				if(personaDB.getPassword().equals(password)) 
					result=2;//E' UN ORGANIZZATORE LOGIN GIUSTO
			}catch(Exception e) {
				personaDB=(Staffer)iPersonaDAO.login(persona);
				if(personaDB.getPassword().equals(password)) 
					result=1;//E' UNO STAFFER LOGIN GIUSTO
			}
			
		}catch(Exception e) {
			//e.printStackTrace(); 
			result=0;//LOGIN CON CREDENZIALI VUOTE
		}
		return result;//=0 NON E' STAFFER/ORGANIZZATORE/CREDENZIALI SBAGLIATE	
	}
	
	//REGISTRAZIONE ? COSA REGISTRO CON REGISTAZIONE STAFF O ORGANIZZATORE
	
	
	
	public static void main(String []args) {
		PersistenceFacade a=new PersistenceFacade();
		//a.setStatoBiglietto("1b4b76e0-3c14-46b9-9685-e11b6c12e084",true);
		//System.out.println(a.login("alice@", "w"));
	}
}
