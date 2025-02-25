package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.evento.Evento;

public interface IEventoDAO {
	
	/**GET TUTTI I DATI DELL'EVENTO**
	 * Mi permette di avere tutti i dati di tutti gli eventi.
	 * 
	 * @return ArrayList<Evento>: mi dà l'array di Evento.
	 */
	public ArrayList<Evento> selectAll();
	
	/**SERCH BY DATA**
	 * Mi permette di trovare un evento data la sua data.
	 * 
	 * @param data: identificativo di Evento.
	 * @return Evento: se l'operazione è andata a buon fine avrò l'Evento con la data cercata altrimenti null.
	 */
	public Evento searchByData (Evento data);
	
	/**AGGIUNGI EVENTO**
	 * Mi permetti di aggiungere un evento.
	 * 
	 * @param evento: Evento da aggiungere.
	 * @return boolean: se l'operazione è andata a buon fine ritorna 'true' altrimenti 'false'.
	 */
	public boolean addEvento (Evento evento);
	
	/**GET VENDITE APERTE BY DATA**
	 * 
	 * @param evento: oggetto fittizio di Evento contenente la data.
	 */
	public void setVenditeAperte (Evento evento);
	
	/**SET VENDITE APERTE BY DATA**
	 * 
	 * @param evento: oggetto fittizio di Evento contenente la data.
	 * @return boolean: se l'operazione è andata a buon fine ritorna 'true' altrimenti 'false'.
	 */
	public boolean getVenditeAperte (Evento evento);
	
	/**GET TUTTE LE DATE**
	 * Mi permette di avere tutte le date di tutti gli eventi.
	 * 
	 * @return ArrayList<String>: mi ritorna tutte le date dei Evento sotto forma di array di String.
	 */
	public ArrayList<String> getAllDate ();
	
	/**GET CAPACITA BY EVENTO**
	 * 
	 * @param data: oggetto fittizio di Evento contente la data
	 * @return int: se l'operazione è andata a buon fine avrò la capacità dell'evento altrimenti '-1'.
	 */
	public int getCapacitaByEvento (Evento data);
	
	/**GET BY LUOGO -> INUTILIZZATO**
	 * Mi permette di trovare gli eventi/o che avvengono in un luogo
	 * 
	 * @param luogo: attributo di Evento.
	 * @return ArrayList<Evento>: lista di Evento.
	 */
	public ArrayList<Evento> selectByLuogo (String luogo);
	
		
	
}
