package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.banco.Banco;
import it.unipv.ingsfw.aga.model.evento.Evento;



public interface IBancoDAO {
	public ArrayList<Banco> selectAll ();
	public int getMaxGrucce (Evento evento);

}
