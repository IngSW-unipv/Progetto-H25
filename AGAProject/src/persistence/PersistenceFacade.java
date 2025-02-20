package persistence;

import connection.DBConnection;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

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
	}///noi la connessione la facciamo singola 
	
	
	//SET STATO DEL BIGLIETTO (STRING E BOOL)
	public void setStatoBiglietto (String codeQR, boolean stato) {
		try {
			Biglietto biglietto=new Biglietto(codeQR, stato);
			iBigliettoDAO.setStatoBiglietto(biglietto);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	//GET STATO DEL BIGLIETTO (STRING E BOOL)
	public int getStatoBiglietto (String codeQR) {
		int result=2;
		try {
			Biglietto biglietto=new Biglietto(codeQR);
			result=iBigliettoDAO.getStatoBiglietto(biglietto);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
		
	
	//VERIFICA LOGIN
	public int login (String email, String password){
		int result=2;
		//RETURN 0=STAFFER
		//RETURN 1=ORGANIZZATORE
		//RETURN 2=ERRORE
		try {
			Persona persona=new Persona(email);
			Persona personaDB;
			
			try {
				personaDB=(Organizzatore)iPersonaDAO.login(persona);
				if(personaDB.getPassword().equals(password)) 
					result=1;//E' UN ORGANIZZATORE LOGIN GIUSTO
			}catch(Exception e) {
				personaDB=(Staffer)iPersonaDAO.login(persona);
				if(personaDB.getPassword().equals(password)) 
					result=0;//E' UNO STAFFER LOGIN GIUSTO
			}			
		}catch(Exception e) {
			//e.printStackTrace(); 
			result=2;//LOGIN CON CREDENZIALI VUOTE
		}
		return result;//=0 NON E' STAFFER/ORGANIZZATORE/CREDENZIALI SBAGLIATE	
	}
	
	
	//REGISTRAZIONE ORGANIZZATORE
	public void registazioneOrganizzatore (String codiceFiscale, String nome, String cognome, String email, String password) {
		try {
			Organizzatore organizzatore=new Organizzatore(codiceFiscale, nome, cognome, email, password);
			iPersonaDAO.addOrganizzatore(organizzatore);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	
	//GET EVENTI
	public ArrayList<String> getEventi() {
		ArrayList<String> result;
		try {
			result=iEventoDAO.getAllDate();
		} catch (Exception e) {
			e.printStackTrace();
			result=null;
		}
		return result;
	}
	
	
	//SET STATO DEL EVENTO 
	public void setStatoEvento (Date Data, boolean stato) {
		try {
			Evento evento=new Evento(Data,stato);
			iEventoDAO.setVenditeAperte(evento);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

		
		// TODO: CAMBIO STATO VENDITA BIGLIETTI
		// TODO: GETSTATOVENDITE
		// TODO: GETGRUCCIA
		// TODO: SETGRUCCIA
		// TODO:
		// TODO:
	
	
	
	
	public static void main(String []args) throws ParseException {
		PersistenceFacade a=new PersistenceFacade();
		//a.setStatoBiglietto("1b4b76e0-3c14-46b9-9685-e11b6c12e084",true);
		//System.out.println(a.getStatoBiglietto("1b4b76e0-3c14-46b9-9685-e11b6c12e084"));
		String dataName="2024-01-13";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(dataName);
		java.sql.Date data= new java.sql.Date(parsed.getTime());
		a.setStatoEvento(data, false);
	}
}
