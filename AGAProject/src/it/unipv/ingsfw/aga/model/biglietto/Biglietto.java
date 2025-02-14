package it.unipv.ingsfw.aga.model.biglietto;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.evento.Evento;

public class Biglietto {
    private final String nome;
    private final String cognome;
    private final String email;
    private final int numBiglietto;
    private final String idEvento;
    private final QrCode codeQR;
    private boolean accessoEffettuato = false;
    private boolean guardarobaEffettuato = false;

    // Costruttore accessibile solo al package
    Biglietto(String nome, String cognome, String email, int numBiglietto, String idEvento) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numBiglietto = numBiglietto;
        this.idEvento = idEvento;
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
    public String getIdEvento() {
        return idEvento;
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