package it.unipv.ingsfw.aga.database;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IBigliettoDAO {
   // void aggiungiBiglietto(Biglietto biglietto) throws SQLException;
	
	public boolean creaBiglietto(Biglietto biglietto);
	public Persona getCreatoreBiglietto(Biglietto biglietto);
	public void setStatoBiglietto(Biglietto biglietto);
	public int getStatoBiglietto(Biglietto biglietto);
	public boolean setGruccia(Biglietto biglietto);
	public int getGruccia(Biglietto biglietto);
	public int getNumeroGrucceAssegnate (Evento evento);
	public int getNumeroBigliettiByCodiceFiscale(Persona persona,Evento evento);
	public int getBigliettiTotoaliByEvento(Evento evento);
	public ArrayList<String> getTuttiQRBiglietti();
	public ArrayList<Biglietto> getInvitati(Evento eventoData);
	public Biglietto getBigliettoByQR(Biglietto biglietto);
}
