package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.persone.*;


public interface IPersonaDAO {
	
	/**GET DI TUTTE LE PERSONE CON TUTTI I DATI**
	 * Mi permette di avere tutte le persone con tutti i dati
	 * 
	 * @return ArrayList <Persona>: elenco di Persona.
	 */
	public ArrayList<Persona> selectAll();
	
	/**SEARCH BY CF (SIA ORGANIZZATORI CHE STAFFER)**
	 * Mi permette di trovare l'organizzatore/staffer attraverso il suo cofice fiscale.
	 * 
	 * @param persona: oggetto fittizio di Persona contenente il codiceFiscale.
	 * @return Persona: la persona (parent di Staffer/Organizzatore) con quel codiceFiscale.
	 */
	public Persona searchByCF(Persona persona);
	
	/**AGGIUNGI ORGANIZZATORE**
	 * Mi permette di aggiungere un organizzatore.
	 * 
	 * @param persona: oggetto Organizzatore da aggiungere.
	 * @return boolean: se l'operazione è andata a buon fine ritorna 'true' altrimenti 'false'.
	 */
	public boolean addOrganizzatore(Organizzatore persona);
	
	/**AGGIUNGI STAFFER**
	 * Mi permette di aggiungere uno staffer.
	 * 
	 * @param persona: oggetto Staffer da aggiungere.
	 * @return boolean: se l'operazione è andata a buon fine ritorna 'true' altrimenti 'false'.
	 */
	public boolean addStaffer(Staffer persona);
	
	/**SEARCH BY EMAIL -> LOGIN
	 * Mi permette di trovare dall'email la password per il login.
	 * 
	 * @param persona: oggetto fittizio contenete l'email.
	 * @return Persona: oggetto Persona con i dati dell'email.
	 */
	public Persona login (Persona persona);
	
	/**GET CF BY EMAIL -> PER LA CREAZIONE DEL BIGLIETTO**
	 * Mi permette di avere il codice fiscale del loggato attraverso la sua email.
	 * 
	 * @param persona: oggetto fittizio di Persona contente l'email.
	 * @return Persona: oggetto fittizio contenente il codiceFiscale.
	 */
	public Persona getCodiceFiscaleByEmail (Persona persona);
	
	/**GET CF NOME E COGNOME E TIPO -> INUTILIZZATO**
	 * Mi permette di avere il codice fiscale, il nome, il cognome di tutte le persone.
	 * 
	 * @return ArrayList <Persona>: elenco di Persona.
	 */
	public ArrayList<Persona> selectCFNomeCognome();
	
	/**GET LO STAFF CF NOME COGNOME -> INUTILIZZATO**
	 * 
	 * @return ArrayList <Persona>: elenco di Persona.
	 */
	public ArrayList<Persona> selectStaffCFNomeCognome();
	
	/**GET GLI ORGANIZZATORI CF NOME COGNOME -> INUTILIZZATO**
	 * 
	 * @return ArrayList <Persona>: elenco di Persona.
	 */
	public ArrayList<Persona> selectOrganizzatoreCFNomeCognome();
	
}
