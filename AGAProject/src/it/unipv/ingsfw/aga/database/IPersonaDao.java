package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.persone.*;


public interface IPersonaDao {
	public ArrayList<Persona> selectAll();
	public ArrayList<Persona> selectCFNomeCognome();
	public ArrayList<Persona> selectStaffCFNomeCognome();
	public ArrayList<Persona> selectOrganizzatoreCFNomeCognome();
	public Persona searchByCF(Persona persona);
	public void addOrganizzatore(Organizzatore persona);
	public void addStaffer(Staffer persona);
	
}
