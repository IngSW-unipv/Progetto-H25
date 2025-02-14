package it.unipv.ingsfw.aga.model.biglietto;

import java.util.Date;

public class BigliettoFactory {
    public static Biglietto creaBiglietto(String nome, String cognome, String email, int numBiglietto, Date dataEvento) {
        return new Biglietto(nome, cognome, email, numBiglietto, dataEvento);
    }
}

