package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.persone.Persona;

import java.sql.SQLException;

public interface IBigliettoDAO {
   // void aggiungiBiglietto(Biglietto biglietto) throws SQLException;
	
	public void creaBiglietto(Biglietto biglietto);
	public Persona getCreatoreBiglietto(Biglietto biglietto);
	public void setStatoBiglietto(Biglietto biglietto);
}
