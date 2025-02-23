package it.unipv.ingsfw.aga.model.biglietto;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.persone.Persona;
import it.unipv.ingsfw.aga.model.evento.Evento;

import java.util.Date;

public class Biglietto implements Stampabile {
    private final Persona creatoreBiglietto;
    private final Evento evento;
    private String nome;
    private String cognome;
    private String email;
    private QrCode codeQR;
    private boolean accessoEffettuato = false;
    private int numeroGruccia=0;
    private String descrizioneGruccia = "";

    public Biglietto(Persona creatoreBiglietto, Evento evento, String nome, String cognome, String email) {
        this.creatoreBiglietto = creatoreBiglietto;
        this.evento = evento;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.codeQR = new QrCode();
        this.accessoEffettuato = false;
    }
    
    public Biglietto(Persona creatoreBiglietto, String nome, String cognome, String email, QrCode codeQR, Evento evento, boolean accessoEffettuato, int numeroGruccia) {
        this.creatoreBiglietto = creatoreBiglietto;
        this.evento = evento;
        this.accessoEffettuato = accessoEffettuato;
        this.numeroGruccia = numeroGruccia;
        this.codeQR = codeQR;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
    
    //COSTRUTTORE PER BIGLIETTO CON LO STESSO QR E PER LA LISTA DEGLI INVITATI    
    public Biglietto(Persona creatoreBiglietto, String QR, String nome, String cognome, String email, Evento evento) {
        this.creatoreBiglietto = creatoreBiglietto;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.evento = evento;
        this.codeQR = new QrCode(QR);
    }
    
    public Biglietto(Persona creatoreBiglietto, String nome, String cognome, String email, Evento evento) {
        this.creatoreBiglietto = creatoreBiglietto;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.evento = evento;
        this.codeQR = new QrCode();
    }
    
    public Biglietto(String codeQR) {
        this.creatoreBiglietto= null;
        this.evento = null;
        this.codeQR = new QrCode(codeQR);
    }
    
    public Biglietto(String codeQR, boolean accessoEffettuato) {
        this.creatoreBiglietto= null;
        this.evento=null;
        this.accessoEffettuato=accessoEffettuato;
        this.codeQR = new QrCode(codeQR);
    }
    public void changeQRCode() {
    	this.codeQR=new QrCode();
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
                "Nome: " + getNome() + "\n" +
                "Cognome: " + getCognome() + "\n" +
                "Email: " + getEmail() + "\n" +
                "QrCode: " + getQRCodeId() + "\n" +
                "Creatore: " +getNomeCreatore() + " " + getCognomeCreatore() + " - Email: " + getEmailCreatore() + "\n" ;
    }

    @Override
    public String stampaBiglietto() {
        return toString();
    }
}