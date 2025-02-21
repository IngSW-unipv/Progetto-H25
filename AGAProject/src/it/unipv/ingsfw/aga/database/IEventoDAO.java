package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.evento.Evento;

public interface IEventoDAO {
	public ArrayList<Evento> selectAll();
	public ArrayList<Evento> selectByLuogo (String luogo);
	public Evento searchByData (Evento data);
	public boolean addEvento (Evento evento);
	public void setVenditeAperte (Evento evento);
	public boolean getVenditeAperte (Evento evento);
	public ArrayList<String> getAllDate ();
	public int getCapacitaByEvento (Evento data);
	
		
	
}
