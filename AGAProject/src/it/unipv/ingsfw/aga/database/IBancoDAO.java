package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.banco.Banco;
import it.unipv.ingsfw.aga.model.banco.BancoGuardaroba;
import it.unipv.ingsfw.aga.model.evento.Evento;



public interface IBancoDAO {
	public ArrayList<Banco> selectAll ();
	public int getMaxGrucce (Evento evento);
	public int getMaxtIdentificativoBanco();
	public boolean addBanco(Evento evento,BancoGuardaroba banco);

}
