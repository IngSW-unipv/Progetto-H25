package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.banco.Banco;



public interface IBancoDAO {
	public ArrayList<Banco> selectAll ();

}
