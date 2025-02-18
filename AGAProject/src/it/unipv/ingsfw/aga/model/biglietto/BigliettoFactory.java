package it.unipv.ingsfw.aga.model.biglietto;

import java.util.Date;

import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;

public class BigliettoFactory {
    public static Biglietto creaBiglietto(Persona persona, QrCode codeQR, Evento evento,boolean accessoEffettuato, boolean guardarobaEffettuato) {
        return new Biglietto(persona,codeQR, evento,accessoEffettuato, guardarobaEffettuato);
    }
}

