package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.evento.Evento;

public interface IEventoDAO {
	public ArrayList<Evento> selectAll();
	public ArrayList<Evento> selectByLuogo (String luogo);
	public Evento searchByData (Evento data);
	public void addEvento (Evento evento);
	
		
	
}
