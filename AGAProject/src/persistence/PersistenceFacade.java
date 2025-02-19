package persistence;

import connection.DBConnection;
import java.sql.Connection;

import it.unipv.ingsfw.aga.database.IEventoDAO;
import it.unipv.ingsfw.aga.database.IBancoDAO;
import it.unipv.ingsfw.aga.database.IBigliettoDAO;
import it.unipv.ingsfw.aga.database.IPersonaDAO;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;

import it.unipv.ingsfw.aga.database.BigliettoDAO;
import it.unipv.ingsfw.aga.database.EventoDAO;
import it.unipv.ingsfw.aga.database.BancoDAO;
import it.unipv.ingsfw.aga.database.PersonaDAO;

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
	
	
	
	public static void main(String []args) {
		PersistenceFacade a=new PersistenceFacade();
		//a.setStatoBiglietto("1b4b76e0-3c14-46b9-9685-e11b6c12e084",true);
	}
}
