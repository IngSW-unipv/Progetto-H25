package it.unipv.ingsfw.aga.model.biglietto;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.persone.Persona;
import it.unipv.ingsfw.aga.model.evento.Evento;

import java.util.Date;

public class Biglietto {
    private Persona creatoreBiglietto;
    private Persona persona;
    private Evento evento;
    private QrCode codeQR;
    private boolean accessoEffettuato = false;
    private boolean guardarobaEffettuato = false;

    
    public Biglietto(Persona persona, QrCode codeQR, Evento evento,boolean accessoEffettuato, boolean guardarobaEffettuato) {
        this.persona = persona;
        this.evento=evento;
        this.accessoEffettuato=accessoEffettuato;
        this.guardarobaEffettuato=guardarobaEffettuato;
        this.codeQR = codeQR;
    }
    
    public Biglietto(Persona persona, Evento evento) {
        this.persona = persona;
        this.evento=evento;
        this.codeQR = new QrCode();
    }
    
    public String getNome() {
        return persona.getNome();
    }
    
    public String getCognome() {
        return persona.getCognome();
    }
    
    public String getEmail() {
        return persona.getEmail();
    }
    
    public Date getDataEvento() {
        return evento.getData();
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