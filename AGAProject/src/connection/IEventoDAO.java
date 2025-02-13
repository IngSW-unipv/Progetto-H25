package connection;

import java.util.ArrayList;

public interface IEventoDAO {
	public ArrayList<Evento> selectAll();
	public ArrayList<Evento> selectByLuogo (String luogo);
	public Evento searchByData (Evento data);
	public void addEvento (Evento evento);
	
}
