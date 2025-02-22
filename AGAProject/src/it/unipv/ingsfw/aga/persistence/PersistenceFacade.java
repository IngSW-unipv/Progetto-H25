package it.unipv.ingsfw.aga.persistence;

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
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.database.BigliettoDAO;
import it.unipv.ingsfw.aga.database.PersonaDAO;
import it.unipv.ingsfw.aga.database.EventoDAO;
import it.unipv.ingsfw.aga.database.BancoDAO;


public class PersistenceFacade {
	//private Connection conn;
	private static PersistenceFacade instance;
	
	IBigliettoDAO iBigliettoDAO;
	IPersonaDAO iPersonaDAO;
	IEventoDAO iEventoDAO;
	IBancoDAO iBancoDAO;
	
	private PersistenceFacade()  {
		iBigliettoDAO=new BigliettoDAO();
		iPersonaDAO=new PersonaDAO();
		iEventoDAO=new EventoDAO();
		iBancoDAO=new BancoDAO();
	}///noi la connessione la facciamo singola 

	// Public static method to get the instance of the Singleton
	public static PersistenceFacade getInstance() {
		if (instance == null) {
					instance = new PersistenceFacade();
					System.out.println("WLF");
		
		}
		return instance;
	}


	//SET STATO DEL BIGLIETTO (STRING E BOOL)
	public boolean setStatoBiglietto (String codeQR, boolean stato) {
		try {
			Biglietto biglietto=new Biglietto(codeQR, stato);
			iBigliettoDAO.setStatoBiglietto(biglietto);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
		//RETURN=0 STAFFER
		//RETURN=1 ORGANIZZATORE
		//RETURN=2 ERRORE
		try {
			Persona persona=new Persona(null,email);
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
	public boolean registazioneOrganizzatore (String codiceFiscale, String nome, String cognome, String email, String password) {
		boolean result=false;
		try {
			Organizzatore organizzatore=new Organizzatore(codiceFiscale, nome, cognome, email, password);
			result=iPersonaDAO.addOrganizzatore(organizzatore);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
	
	
	//SEARCH BY DATA EVENTO 
	public int searchEventoByData (Date Data) {
		int result=2;
		Evento eventoData;
		try {
			Evento evento=new Evento(Data);
			eventoData=iEventoDAO.searchByData(evento);
			if(eventoData.getVenditeAperte()==true) {result=1;}
			else {result=0;}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}

	
	//SET GRUCCIA
	public boolean setGruccia (String codeQR, int numeroGruccia) {
		boolean result=false;
		try {
			Biglietto biglietto=new Biglietto (codeQR);
			biglietto.setNumeroGruccia(numeroGruccia);
			result=iBigliettoDAO.setGruccia(biglietto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("fff");
		}	
		return result;
	}
	
	
	//GET GRUCCIA
	public int getGruccia (String codeQR) {
		int result=-1;
		try {
			Biglietto biglietto=new Biglietto (codeQR);
			result=iBigliettoDAO.getGruccia(biglietto);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("f");
		}	
		return result;
	}
	
	
	//MAX GRUCCE EVENTO
	public int getMaxGrucce (Evento evento) {
		int result=-1;
		try {
			result=iBancoDAO.getMaxGrucce(evento);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ff");
		}	
		return result;
	}
	
	
	//GET NUMERO GRUCCE ASSEGNATE
	public int getNumeroGrucceAssegnate(Evento evento) {
		int result=-1;
		try {
			result=iBigliettoDAO.getNumeroGrucceAssegnate(evento);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}

	
	//GET CF BY EMAIL
	public Persona getCodiceFiscaleByEmail(String email) {
		Persona persona;
		try {
			Persona personaEmail=new Persona(null,email);
			persona=iPersonaDAO.getCodiceFiscaleByEmail(personaEmail);
		} catch (Exception e) {
			e.printStackTrace();
			persona= new Persona();
		}	
		return persona;
	}
	
	
	//AGGIUNGI INVITATO
	public int aggiungiInvitato(Persona persona, Evento evento, String nome, String cognome, String email) {
		int result=2;
		//RESULT=2 ERRORE NELL'INSERIMENTO
		//RESULT=1 BIGLIETTO AGGIUNTO CORRETTAMENTE
		//RESULT=0 MASSIMO BIGLIETTI RAGGIUNTI PER IL CREATORE
		//RESULT=-1 MASSIMA CAPACITA' DELL'EVENTO RAGGIUNTA
		Biglietto biglietto;
		int numeroBiglietti=-1;
		int capacitaEvento=-1;
		ArrayList <String> bigliettiQR= new ArrayList<>();
		QrCode code=new QrCode();
		boolean rs; 
		
		try {
			numeroBiglietti=iBigliettoDAO.getBigliettiTotoaliByEvento(evento);
			capacitaEvento=iEventoDAO.getCapacitaByEvento(evento);
			//controllo massima capacita'
			if(numeroBiglietti>=capacitaEvento)
				result=-1;
			else {
				int numero= iBigliettoDAO.getNumeroBigliettiByCodiceFiscale(persona,evento);
				//controllo massimo biglietto per creatore
				if(numero>4) 
					result=0;
				else {
					//biglietto= new Biglietto(persona, nome, cognome, email, evento);
					bigliettiQR=iBigliettoDAO.getTuttiQRBiglietti();
					
					//controllo che i biglietti abbiano QR diversi
					bigliettiQR.add("");//ciclo in piu per eventuali catastrofi
					for (String n: bigliettiQR) {
						for (String i: bigliettiQR) {
				           if((code.getId()).equals(i)){
				        	   code=new QrCode();
				        	   break;			        	   
				           }
				        }
					}
					//creazione biglietto
					biglietto= new Biglietto(persona,code.getId(), nome, cognome, email, evento);
					rs=iBigliettoDAO.creaBiglietto(biglietto);				
					
					if(rs==true) result=1;
					else result=2;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	
	//GET INVITATI
	public ArrayList <Biglietto> getInvitati(Evento evento) {
		ArrayList<Biglietto> biglietto;
		try {
			biglietto=iBigliettoDAO.getInvitati(evento);
			
		} catch (Exception e) {
			e.printStackTrace();
			biglietto=null;
		}	
		return biglietto;
	}
	
	
	//AGGIUNGI STAFFER
	public boolean addStaffer(String codiceFiscale, String nome, String cognome, String email, String password) {
		boolean result=false;
		Staffer staffer;
		try {
			staffer=new Staffer(codiceFiscale, nome, cognome, email, password);
			result=iPersonaDAO.addStaffer(staffer);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	

	//AGGIUNGI EVENTO
	// MI SA CHE HO GENERATO UN ERRORE NELLA CREAZIONE LAVORANDO SULLA CLASSE EVENTO SORRY :'( che credo di aver sistemato, FAMMI SAPERE
	public boolean addEvento(String data, String luogo, String capacita) {
		boolean result=false;
		Evento evento=null;
		try {
			int capacitaMax= Integer.parseInt(capacita);
			Date dataEvento= evento.changeStringToDate(data);
			evento=new Evento(dataEvento,luogo,capacitaMax);
			result=iEventoDAO.addEvento(evento);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	

	
	
	public static void main(String []args) throws ParseException {
		PersistenceFacade a=new PersistenceFacade();
		//a.setStatoBiglietto("1b4b76e0-3c14-46b9-9685-e11b6c12e084",true);
		//System.out.println(a.getStatoBiglietto("1b4b76e0-3c14-46b9-9685-e11b6c12e084"));
		String dataName="1998-07-13";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(dataName);
		java.sql.Date data= new java.sql.Date(parsed.getTime());
		//a.setStatoEvento(data, false);
		//System.out.println(a.getCodiceFiscaleByEmail("alice@"));
		Persona persona=new Persona("001-51-9829", null);
		Evento evento=new Evento(data);
		System.out.println(a.aggiungiInvitato(persona, evento, "Andrea","Esposito","andrea.esposito@unipv.it"));
	}
}
