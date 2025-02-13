package connection;

import java.util.ArrayList;


public interface IPersonaDao {
	public ArrayList<Persona> selectAll();
	public ArrayList<Persona> selectCFNomeCognome();
	public ArrayList<Persona> selectStaffCFNomeCognome();
	public ArrayList<Persona> selectOrganizzatoreCFNomeCognome();
	
}
