package it.unipv.ingsfw.aga.model.evento;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.biglietto.BigliettoFactory;
import it.unipv.ingsfw.aga.model.persone.*;
import it.unipv.ingsfw.aga.model.persone.PersonaFactory;
import it.unipv.ingsfw.aga.database.EventoDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Evento {
    private final Date data;
    private String location;
    private int maxPartecipanti;
    private boolean venditeAperte;

    //Costruttore Standard
    public Evento(String dataString, String location, int maxPartecipanti) throws ParseException, MaxExeededException {
        this.data = changeStringToDate(dataString);
    	this.location = location;
        if (maxPartecipanti < 0) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        } else if (maxPartecipanti > 1500) {
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + data + " è stato superato");
        } else {
            this.maxPartecipanti = maxPartecipanti;
        }
        this.venditeAperte = false;
    }

    //Serve per la persistenza(?) SORRY SE HO FATTO CASINO :'(
    public Evento(Date data, String location, int capacita){
        this.data = data;
		this.location = location;
		this.maxPartecipanti = capacita;
		this.venditeAperte = false;
    }
    
    //COSTRUTTORE UTILE PER IL DAO
    public Evento(Date data, String location, int maxPartecipanti, boolean venditeAperte)  {
    	this.data = data;
    	this.location = location;
    	this.maxPartecipanti = maxPartecipanti;
    	this.venditeAperte = venditeAperte;
    }
    
    public Evento(String dataName) throws ParseException  {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(dataName);
		java.sql.Date data = new java.sql.Date(parsed.getTime());
    	this.data = data;
    	this.location = null;
    	this.maxPartecipanti = 0;
    	this.venditeAperte = false;
    }
    
    public Evento(Date data)  {
    	this.data = data;
    	this.location = null;
    	this.maxPartecipanti = 0;
    	this.venditeAperte = false;
    }
    
    public Evento(Date data, boolean stato)  {
    	this.data = data;
    	this.location = null;
    	this.maxPartecipanti = 0;
    	this.venditeAperte = stato;
    }
    
    public Evento(Evento evento) {
    	this.data = evento.getData();
    	this.location = evento.getLocation();
    	this.maxPartecipanti = evento.getMaxPartecipanti();
    	this.venditeAperte = evento.getVenditeAperte();
    }

    //Questo metodo probabilmente dovrebbe essere in una classe di utility? Perchè non appartiene a un evento e potrebbe essere utili anche in altre classi
    public Date changeStringToDate(String dataName) throws ParseException {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(dataName);
		java.sql.Date data= new java.sql.Date(parsed.getTime());
		return data;
    }

    public Date getData() {
        return data;
    }

    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
    	this.location=location;
    }

    public int getMaxPartecipanti() {
        return maxPartecipanti;
    }
    
    //DAL DAO
    public boolean getVenditeAperte() {
    	return venditeAperte;
    }

    public void setMaxPartecipanti(int maxPartecipanti) throws MaxExeededException {
        if (maxPartecipanti < 0) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        } else if (maxPartecipanti > 1500) {
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + this.getData() + " è stato superato");
        } else {
            this.maxPartecipanti = maxPartecipanti;
        }
    }

    public Biglietto aggiugiBiglietto(Persona creatoreBiglietto, Evento evento, String nome, String cognome, String email){
        return BigliettoFactory.creaBiglietto(creatoreBiglietto, evento, nome, cognome, email);
    }
    
    public String toString() {
        String s= "[Evento]\n" +
                "Tipo: Standard\n" +
                "Data: " + getData() + "\n" +
                "Location: " + getLocation() + "\n";
        		if(venditeAperte) return s + "Vendite aperte\n";
        		else return s + "Vendite chiuse\n";
    }
}
