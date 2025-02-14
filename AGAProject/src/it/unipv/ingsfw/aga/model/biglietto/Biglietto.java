package it.unipv.ingsfw.aga.model.biglietto;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.persone.Persona;

import java.util.Date;

public class Biglietto {
    private Persona creatoreBiglietto;
    private final String nome;
    private final String cognome;
    private final String email;
    private final int numBiglietto;
    private final Date dataEvento;
    private final QrCode codeQR;
    private boolean accessoEffettuato = false;
    private boolean guardarobaEffettuato = false;

    // Costruttore accessibile solo al package
    Biglietto(String nome, String cognome, String email, int numBiglietto, Date dataEvento) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numBiglietto = numBiglietto;
        this.dataEvento = dataEvento;
        this.codeQR = new QrCode();
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
    public int getNumBiglietto() {
        return numBiglietto;
    }
    public Date getDataEvento() {
        return dataEvento;
    }
    public QrCode getQRcode() {
        return codeQR;
    }
    public String getQRCodeId() {
        return codeQR.getId();
    }
    public boolean isAccessoEffettuato() {
        return accessoEffettuato;
    }

    public void setAccessoEffettuato(boolean accessoEffettuato) {
        this.accessoEffettuato = accessoEffettuato;
    }

    public boolean isGuardarobaEffettuato() {
        return guardarobaEffettuato;
    }

    public void setGuardarobaEffettuato(boolean guardarobaEffettuato) {
        this.guardarobaEffettuato = guardarobaEffettuato;
    }
    @Override
    public String toString() {
        return "[Biglietto]\n" +
                "Tipo: Standard\n" +
                "Proprietario: " + getNome() + " " + getCognome() + " " + getEmail() + "\n" +
                "QrCode: " + getQRCodeId() + "\n";
    }
}