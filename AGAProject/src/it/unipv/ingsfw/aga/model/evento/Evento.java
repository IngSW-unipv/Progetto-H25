package it.unipv.ingsfw.aga.model.evento;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.biglietto.BigliettoFactory;
import it.unipv.ingsfw.aga.model.persone.*;
import it.unipv.ingsfw.aga.model.persone.PersonaFactory;
import it.unipv.ingsfw.aga.database.EventoDAO;

import java.util.Date;
import java.util.UUID;

public class Evento {
    //private final String organizzatore;
	//private Organizzatore organizzatore;
    private final Date data;
    private String location;
    private int maxPartecipanti;
    //private String idEvento;
    //private int numBiglietto = 0;
    private boolean venditeAperte; 

    public Evento(Date data, String location, int maxPartecipanti)  {
    	this.data=data;
    	this.location=location;
    	this.maxPartecipanti=maxPartecipanti;
    }
    
    //COSTRUTTORE UTILE PER IL DAO
    public Evento(Date data, String location, int maxPartecipanti, boolean venditeAperte)  {
    	this.data=data;
    	this.location=location;
    	this.maxPartecipanti=maxPartecipanti;
    	this.venditeAperte=venditeAperte;
    }
    
    public Evento(Evento evento) {
    	this.data=evento.getData();
    	this.location=evento.getLocation();
    	this.maxPartecipanti=evento.getMaxPartecipanti();
    	this.venditeAperte=evento.getVenditeAperte();
    }
    
    
    /*public Evento(String organizzatore, Date data, String location, int maxPartecipanti) throws MaxExeededException {
        this.organizzatore = organizzatore;
        this.data = data;
        this.location = location;
        if (maxPartecipanti < 0) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        } else if (maxPartecipanti > 1500) {
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + data + " è stato superato");
        } else {
            this.maxPartecipanti = maxPartecipanti;
        }
        this.idEvento = UUID.randomUUID().toString();  //idee su alternative per avere un identificatore univoco?
        this.venditeAperte = false;
    }
    
    public Organizzatore getOrganizzatore() {
        return organizzatore;
    }*/
    public Date getData() {
        return data;
    }
    
    public void setData(Evento nuovaData) {//NON SI PUO FARE PER CHIAVE ESTERNA
    	//EventoDAO d=new EventoDAO();
    	//d.changeData(this, nuovaData);    	
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
    
    public boolean getVenditeAperte() {
    	return venditeAperte;
    }

    /*public int getNumBiglietto() {
        return numBiglietto;
    }

    public void setNumBiglietto(int numBiglietto) {
        this.numBiglietto = numBiglietto;
    }
    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public boolean isVenditeAperte() {
        return venditeAperte;
    }*/

    /*public void apriVendite() {
        venditeAperte = true;
    }

    public void chiudiVendite() {
        venditeAperte = false;
    }*/

    public void setMaxPartecipanti(int maxPartecipanti) throws MaxExeededException {
        if (maxPartecipanti < 0) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        } else if (maxPartecipanti > 1500) {
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + this.getData() + " è stato superato");
        } else {
            this.maxPartecipanti = maxPartecipanti;
        }
    }

   /* public Biglietto aggiungiBiglietto(String codiceFiscale, String nome, String cognome, String email, Date data, boolean staffer) {
        if (staffer) {
            numBiglietto++;
            PersonaFactory.creaPersona("staffer", codiceFiscale, nome, cognome, email, null);
            return BigliettoFactory.creaBiglietto(nome, cognome, email, numBiglietto, getData());
        } else {
            numBiglietto++;
            return BigliettoFactory.creaBiglietto(nome, cognome, email, numBiglietto, getData());
        }
    }*/
    
    public String toString() {
        String s= "[Evento]\n" +
                "Tipo: Standard\n" +
                "Data: " + getData() + "\n" +
                "Location: " + getLocation() + "\n";
        if(venditeAperte) return s+"Vendite aperte\n";
        else return s+"Vendite chiuse\n";        	
                //"Organizzatore: " + getOrganizzatore() + "\n";
    }
    
    
}
