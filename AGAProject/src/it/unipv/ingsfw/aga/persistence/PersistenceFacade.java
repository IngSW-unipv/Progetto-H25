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
import it.unipv.ingsfw.aga.model.banco.BancoGuardaroba;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.database.BigliettoDAO;
import it.unipv.ingsfw.aga.database.PersonaDAO;
import it.unipv.ingsfw.aga.database.EventoDAO;
import it.unipv.ingsfw.aga.database.BancoDAO;


public class PersistenceFacade {
	
	private static PersistenceFacade instance;
	
	IBigliettoDAO iBigliettoDAO;	//Interfaccia con la persistenza del biglietto
	IPersonaDAO iPersonaDAO;		//Interfaccia con la persistenza della persona
	IEventoDAO iEventoDAO;			//Interfaccia con la persistenza dell'evento
	IBancoDAO iBancoDAO;			//Interfaccia con la persistenza di banco
	
	private PersistenceFacade()  {
		iBigliettoDAO=new BigliettoDAO();
		iPersonaDAO=new PersonaDAO();
		iEventoDAO=new EventoDAO();
		iBancoDAO=new BancoDAO();
	}

	// Public static method to get the instance of the Singleton
	public static PersistenceFacade getInstance() {
		if (instance == null) {
					instance = new PersistenceFacade();
					System.out.println("WLF");
		
		}
		return instance;
	}


	/**SET STATO DEL BIGLIETTO**
	 * Esegue il cambiamento dello stato del biglietto, cioè se l'accesso alla festa è stato effettuato.
	 * 
	 * @param codeQR: identificativo del biglietto sui cui si vogliono ottenere le informazioni.
	 * @param stato: identificativo che si usa per cambiare l'accesso di un biglietto.
	 * @return boolean: se l'accesso alla festa è già stato effettuato mi da un 'false' altrimenti 'true'.
	 */
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
	
	
	/**GET STATO DEL BIGLIETTO**
	 * Mi permette di vedere lo stato del biglietto.
	 * 
	 * @param codeQR: identificativo del biglietto sui cui si vogliono ottenere le informazioni.
	 * @param stato: identificativo che si usa per cambiare l'accesso di un biglietto.
	 * @return int: se l'accesso alla festa è già stato effettuato mi da un '1' altrimenti '0' e se è presente 
	 * 				un errore mi rida '2'.
	 */
	public int getStatoBiglietto (String codeQR) {
		int result=2;
		//RESULT=1 ACCESSO EFFETTUATO
		//RESULT=0 ACCESSO NON EFFETTUATO
		//RESULT=2 ERRORE 
		try {
			Biglietto biglietto=new Biglietto(codeQR);
			result=iBigliettoDAO.getStatoBiglietto(biglietto);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	
	/**VERIFICA LOGIN**
	 * Mi permette di effettuare il login attraverso email e password, permettendo di sapere se l'accesso è 
	 * effettuato da uno staffer o un organizzatore.
	 * 
	 * @param email: attributo di persona che permette di effetuare l'accesso.
	 * @param password: attributo di staffer/organizzatore per accedere.
	 * @return int: se l'accesso alla festa è stato effettuato da uno staffer ricevo '0', da un organizzatore 
	 * 				ricevo '1' se è presente un errore ricevo '2'.
	 */
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
			e.printStackTrace(); 
			result=2;
		}
		return result;	
	}
	
	
	/**REGISTRAZIONE ORGANIZZATORE**
	 * Mi permette di registrare un organizzatore.
	 * 
	 * @param codiceFiscale: identificativo della persona che si vuole aggiungere.
	 * @param nome: attributo della persona che si vuole aggiungere.
	 * @param cognome: attributo della persona che si vuole aggiungere.
	 * @param email: attributo della persona che si vuole aggiungere.
	 * @param password: attributo dell'organizzatore per accedere.
	 * @return boolean: se la registazione è stata effettuata con sucesso riceerò 'true' altrimenti 'false'.
	 */
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

	
	/**GET EVENTI**
	 * Mi permette di visualizzare tutti gli eventi indifferentemente dalla data.
	 * 
	 * @return Array<String>: ritorna la lista di tutti gli eventi.
	 */
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
	
	
	/**SET STATO DELL'EVENTO **
	 * Mi permette cambiare le vendite dell'evento da aperte a chiuse e viceversa.
	 * 
	 * @param data: identificativo dell'evento.
	 * @param stato: attributo dell'evento che mi identifica se è possibile comprare un biglietto dell'evento.
	 * @return void
	 */
	public void setStatoEvento (Date data, boolean stato) {
		try {
			Evento evento=new Evento(data,stato);
			iEventoDAO.setVenditeAperte(evento);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	/**SEARCH BY DATA EVENTO PER VENDITE APERTE**
	 * Mi permette di cercare l'evento data la sua data e controllare se le vendite sono aperte o chiuse.
	 * 
	 * @param data: identificativo dell'evento.
	 * @return int: se ho errori nell'operzione ricevero '2', se le vendite sono apete ritorna '1' altrimenti '0'. 
	 */
	public int searchEventoByData (Date data) {
		int result=2;
		//RESULT=2 ERRORE
		//RESULT=1 VENDITE APERTE
		//RESULT=0 VENDITE CHIUSE
		Evento eventoData;
		try {
			Evento evento=new Evento(data);
			eventoData=iEventoDAO.searchByData(evento);
			if(eventoData.getVenditeAperte()==true) 
				result=1;
			else result=0;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}

	
	/**SET GRUCCIA **
	 * Mi permette di aggiungere al biglietto una gruccia.
	 * 
	 * @param codeQR: identificativo del biglietto.
	 * @param numeroGruccia: attributo dei bancoGuardaroba corrispondente al numero di gruccia.
	 * @return boolean: se la gruccia è stata assegnata correttamente è uguale a 'true' altrimenti è 'false'.
	 */
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
	
	
	/**GET GRUCCIA**
	 * Mi permette di avere il numero della gruccia del biglietto.
	 * 
	 * @param codeQR: identificativo del biglietto.
	 * @return int: è il numero di gruccia, se è presente qualche errore è uguale a '-1'.
	 */
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
	
	
	/**MAX GRUCCE EVENTO**
	 * Mi permette di avere il numero massimo di grucce di quell'evento.
	 * 
	 * @param evento: oggetto evento.
	 * @return int: è il numero di gruccia, se è presente qualche errore è uguale a '-1'.
	 */
	public int getMaxGrucce (Evento evento) {
		int result=-1;
		try {
			result=iBancoDAO.getMaxGrucce(evento);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
		}	
		return result;
	}
	
	
	/**GET NUMERO GRUCCE ASSEGNATE**
	 * Mi permette di avere il numero dell'ultima gruccia assegnata a quell'evento.
	 * 
	 * @param evento: oggetto Evento.
	 * @return int: è il numero di gruccia, se è presente qualche errore è uguale a '-1'.
	 */
	public int getNumeroGrucceAssegnate(Evento evento) {
		int result=-1;
		try {
			result=iBigliettoDAO.getNumeroGrucceAssegnate(evento);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}

	
	/**GET CF BY EMAIL**
	 * Mi permette di avere il codiceFiscale dall'email. E' usata durante il login per tener conto del 
	 * codiceFiscale di chi si è loggato.
	 * 
	 * @param email: attributo della persona usato durante il login.
	 * @return Persona: oggetto Persona .
	 */
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
	
	
	/**AGGIUNGI INVITATO**
	 * Mi permette di aggiungere un invitato all'evento.
	 * 
	 * @param persona: oggetto Persona (contente il codiceFiscale del creatore dell'evento).
	 * @param evento: oggetto evento (contenete la data dell'evento).
	 * @param nome: attributo del biglietto.
	 * @return int: è il numero di gruccia, se è presente qualche errore è uguale a '-1'.
	 */
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
	public boolean addEvento(String data, String luogo, String capacita) {
		boolean result=false;
		Evento evento;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date parsed = format.parse(data);
			java.sql.Date dataEvento = new java.sql.Date(parsed.getTime());
			int capacitaMax= Integer.parseInt(capacita);			
			evento=new Evento(dataEvento,luogo,capacitaMax);
			result=iEventoDAO.addEvento(evento);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	
	//GET DATA BY QR
	public Date getDataByBiglietto(String QR) {
		Biglietto data;
		Date result;
		
		try { 
			Biglietto bigliettoQR=new Biglietto(QR);
			data=iBigliettoDAO.getBigliettoByQR(bigliettoQR);
			result=data.getDataEvento();			
		} catch (Exception e) {
			e.printStackTrace();
			result=null;
		}	
		return result;
	}
	
	
	//CREA BANCO
	public boolean addBanco(Evento evento, int maxGrucce) {
		boolean result=false;
		int id;
		
		try { 
			id=iBancoDAO.getMaxtIdentificativoBanco();
			BancoGuardaroba banco=new BancoGuardaroba(id,maxGrucce, evento);
			result=	iBancoDAO.addBanco(evento, banco);	
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	
	
	
	//TODO RIGA 213
	
	
	public static void main(String []args) throws ParseException {
		PersistenceFacade a=new PersistenceFacade();
		//a.setStatoBiglietto("1b4b76e0-3c14-46b9-9685-e11b6c12e084",true);
		//System.out.println(a.getStatoBiglietto("1b4b76e0-3c14-46b9-9685-e11b6c12e084"));
		/*String dataName="1998-07-13";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(dataName);
		java.sql.Date data= new java.sql.Date(parsed.getTime());
		//a.setStatoEvento(data, false);
		//System.out.println(a.getCodiceFiscaleByEmail("alice@"));
		Persona persona=new Persona("001-51-9829", null);
		Evento evento=new Evento(data);*/
		//System.out.println("torta".hashCode());
		//System.out.println(a.aggiungiInvitato(persona, evento, "Andrea","Esposito","andrea.esposito@unipv.it"));
	}
}
