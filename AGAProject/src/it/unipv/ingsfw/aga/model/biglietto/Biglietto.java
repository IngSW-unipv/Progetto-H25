package it.unipv.ingsfw.aga.model.biglietto;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.persone.Persona;
import it.unipv.ingsfw.aga.model.evento.Evento;

import java.util.Date;

public class Biglietto {
    private Persona creatoreBiglietto;
    private String nome;
    private String cognome;
    private String email;
    private Evento evento;
    private QrCode codeQR;
    private boolean accessoEffettuato = false;
    private int numeroGruccia=0;
    private String descrizioneGruccia="";

    
    public Biglietto(Persona creatoreBiglietto, String nome, String cognome, String email, QrCode codeQR, Evento evento,
    		boolean accessoEffettuato, int numeroGruccia) {
        this.creatoreBiglietto = creatoreBiglietto;
        this.evento=evento;
        this.accessoEffettuato=accessoEffettuato;
        this.numeroGruccia=numeroGruccia;
        this.codeQR = codeQR;
        this.nome=nome;
        this.cognome=cognome;
        this.email=email;
    }
    
    public Biglietto(Persona creatoreBiglietto, String nome, String cognome, String email, Evento evento) {
        this.creatoreBiglietto = creatoreBiglietto;
        this.nome=nome;
        this.cognome=cognome;
        this.email=email;
        this.evento=evento;
        this.codeQR = new QrCode();
    }
    
    public Biglietto(String codeQR) {
        this.creatoreBiglietto= null;
        this.evento=null;
        QrCode code=new QrCode(codeQR);
        this.codeQR = code;
    }
    
    public Biglietto(String codeQR, boolean accessoEffettuato) {
        this.creatoreBiglietto= null;
        this.evento=null;
        this.accessoEffettuato=accessoEffettuato;
        QrCode code=new QrCode(codeQR);
        this.codeQR = code;
    }
    
    public Persona getCreatoreBiglietto() {
    	return creatoreBiglietto;
    }
    
    public String getNomeCreatore() {
        return creatoreBiglietto.getNome();
    }
    
    public String getCognomeCreatore() {
        return creatoreBiglietto.getCognome();
    }
    
    public String getEmailCreatore() {
        return creatoreBiglietto.getEmail();
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getCognome() {
        return cognome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Date getDataEvento() {
        return evento.getData();
    }
    
    public String getQRCodeId() {
        return codeQR.getId();
    }
    
    public boolean getAccessoEffettuato() {
        return accessoEffettuato;
    }
    
    public String getCodiceFiscaleCreatore() {
    	return creatoreBiglietto.getCodiceFiscale();
    }

    public void setAccessoEffettuato(boolean accessoEffettuato) {
        this.accessoEffettuato = accessoEffettuato;
    }

    public int getNumeroGruccia() {
        return numeroGruccia;
    }

    public void setNumeroGruccia(int numeroGruccia) {
        this.numeroGruccia=numeroGruccia;
    }
    
    public String getDescrizioneGruccia() {
    	return descrizioneGruccia;
    }
    
    @Override
    public String toString() {
        return "[Biglietto]\n" +
                "Tipo: Standard\n" +
                "Nome: " + getNome() + " Cognome: " + getCognome() + " Email: " + getEmail() + "\n" +
                "QrCode: " + getQRCodeId() + "\n"+
                "Proprietario: " +getNomeCreatore() + " Cognome: " + getCognomeCreatore() + " Email: " + getEmailCreatore() + "\n" ;
    }
}