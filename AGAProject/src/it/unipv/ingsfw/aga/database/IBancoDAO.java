package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.banco.Banco;
import it.unipv.ingsfw.aga.model.banco.BancoGuardaroba;
import it.unipv.ingsfw.aga.model.evento.Evento;



public interface IBancoDAO {
	
	/**PRINT TUTTI I BANCHI**
	 * Mi permette di avere tutti i banchi.
	 * 
	 * @return ArrayList <Banco>: array di Banco.
	 */
	public ArrayList<Banco> selectAll ();
	
	/**MAX GRUCCE DALLA DATA**
	 * Mi permette di avere il massimo numero di grucce per quell'evento.
	 * 
	 * @param evento: oggetto fittizio di Evento contenente la data.
	 * @return int: se l'operazione è andata a buon fine avrò il numero di grucce dell'evento altrimenti '-1'.
	 */
	public int getMaxGrucce (Evento evento);
	
	/**GET PROSSIMO NUMERO IDENTIFICATIVO DI BANCO**
	 * Mi permette di avere il max id di banco per la futura creazione di un nuovo banco.
	 * 
	 * @return int: se l'operazione è andata a buon fine avrò il numero di id di banco altrimenti '-1'.
	 */
	public int getMaxtIdentificativoBanco();
	
	/**CREA BANCO**
	 * Mi permette di creare banco quando creo un nuovo evento.
	 * 
	 * @param evento: oggetto fittizio di Evento, contenete la data.
	 * @param banco: oggetto fittizio di BancoGuardaroba contente l'id e il numeroMaxGrucce.
	 * @return boolean: se l'operazione è andata a buon fine ritorna 'true' altrimenti 'false'.
	 */
	public boolean addBanco(Evento evento,BancoGuardaroba banco);

}
