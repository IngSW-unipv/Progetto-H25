package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBigliettoDAO {
	
	/**CREA BIGLIETTO**
	 * Mi permette di creare un biglietto per un invitato avendo tutti i dati del biglietto.
	 * 
	 * @param biglietto: oggetto contenente tutti i dati del biglietto.
	 * @return boolean: se l'operazione è andata a buon fine ritorna 'true' altrimenti 'false'.
	 */
	public boolean creaBiglietto(Biglietto biglietto);
	
	/**SET STATO BIGLIETTO**
	 * Mi permette di cambiare l'accesso alla festa del biglietto.
	 * 
	 * @param biglietto: oggetto fittizio di Biglietto contenete il Qr.
	 */
	public void setStatoBiglietto(Biglietto biglietto);
	
	/**GET STATO BIGLIETTO**
	 * Mi permette di controllare se l'invitato ha già effettuato l'accesso all'evento.
	 * 
	 * @param biglietto: oggetto fittizio di Biglietto contenete il Qr.
	 * @return int: ritorna '0' se l'eccesso non è stato effettuato, '1' se è stato effettuato, 
	 * 				'2' in caso di errore.
	 */
	public int getStatoBiglietto(Biglietto biglietto);
	
	/**SET GRUCCIA**
	 * Mi permette di assegnare una gruccia al biglietto.
	 * 
	 * @param biglietto: oggetto fittizio di Biglietto contente il Qr.
	 * @return boolean: se l'operazione è andata a buon fine ritorna 'true' altrimenti 'false'.
	 */
	public boolean setGruccia(Biglietto biglietto);
	
	/**GET GRUCCIA**
	 * Mi permette di avere il numero di gruccia assegnato al biglietto.
	 * 
	 * @param biglietto: oggetto fittizio di Biglietto contenete il Qr.
	 * @return int: se l'operazione è andata a buon fine avrò il numero di gruccia dell'evento, 
	 * 			se al biglietto non è stata assegnata la guccia o ci sono errori '-1'.
	 */
	public int getGruccia(Biglietto biglietto);
	
	/**GET NUMERO GRUCCE ASSEGNATE**
	 * Mi permette di avere l'ultima gruccia assegnata (max) di quell'evento.
	 * 
	 * @param evento: oggetto fittizio di Evento contenete la data.
	 * @return int: se l'operazione è andata a buon fine avrò il numero di gruccia dell'evento altrimenti '-1'.
	 */
	public int getNumeroGrucceAssegnate (Evento evento);
	
	/**GET NUMERO DI BIGLIETTI CREATI DALLO STESSO CREATORE (BY CF)**
	 * Mi permette di avere il numero di biglietti creati dello stesso creatore.
	 * 
	 * @param persona: oggetto fittizio di Persona contenete il codiceFiscale.
	 * @param evento: oggetto fittizio di Evento contenete la data dell'evento.
	 * @return int: se l'operazione è andata a buon fine avrò il numero di biglietti dell'evento altrimenti '-1'.
	 */
	public int getNumeroBigliettiByCodiceFiscale(Persona persona,Evento evento);
	
	/**BIGLIETTI TOTALI NELL'EVENTO**
	 * Mi permette di trovare il numero di biglietti totali di ogni evento.
	 * 
	 * @param evento: oggetto fittizio di Evento contente la data.
	 * @return int: se l'operazione è andata a buon fine avrò il numero di biglietti dell'evento altrimenti '-1'.
	 */
	public int getBigliettiTotoaliByEvento(Evento evento);
	
	/**GET TUTTI I QRCODE -> CONTROLLO CREAZIONE EVENTO**
	 * Mi permette di avere tutti i Qr di tutti i biglietti.
	 * 
	 * @return ArrayList <String>: lista di String contenenti tutti i Qr.
	 */
	public ArrayList<String> getTuttiQRBiglietti();
	
	/**GET TUTTI I BIGLIETTI**
	 * Mi permette di avere tutti gli invitati (biglietti).
	 * 
	 * @param eventoData: oggetto fittizio Evento contenente la data.
	 * @return ArrayLista <Biglietto>: array di Biglietto.
	 */
	public ArrayList<Biglietto> getInvitati(Evento eventoData);
	
	/**GET BIGLIETTO DA QR**
	 * Mi permette di avere il biglietto attraverso il Qr.
	 * 
	 * @param biglietto: oggetto fittizio di biglietto contente il Qr.
	 * @return Biglietto: oggetto Biglietto.
	 */
	public Biglietto getBigliettoByQR(Biglietto biglietto);
	
	/**TROVA CREATORE DAL BIGLIETTO-> INUTILIZZATO**
	 * Mi permette di trovare il creatore del biglietto.
	 * 
	 * @param biglietto: oggetto fittizio di Biglietto contente il Qr.
	 * @return Persona: oggetto fittizio di Persona contente il codiceFiscale.
	 */
	public Persona getCreatoreBiglietto(Biglietto biglietto);
}
