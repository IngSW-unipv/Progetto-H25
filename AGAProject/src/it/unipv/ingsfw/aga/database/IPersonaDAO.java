package it.unipv.ingsfw.aga.database;

import java.util.ArrayList;

import it.unipv.ingsfw.aga.model.persone.*;


public interface IPersonaDAO {
	public ArrayList<Persona> selectAll();
	public ArrayList<Persona> selectCFNomeCognome();
	public ArrayList<Persona> selectStaffCFNomeCognome();
	public ArrayList<Persona> selectOrganizzatoreCFNomeCognome();
	public Persona searchByCF(Persona persona);
	public boolean addOrganizzatore(Organizzatore persona);
	public boolean addStaffer(Staffer persona);
	public Persona login (Persona persona);
	public Persona getCodiceFiscaleByEmail (Persona persona);
	
}
