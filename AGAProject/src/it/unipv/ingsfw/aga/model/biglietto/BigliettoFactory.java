package it.unipv.ingsfw.aga.model.biglietto;

public class BigliettoFactory {
    public static Biglietto creaBiglietto(String nome, String cognome, String email, int numBiglietto, String idEvento) {
        return new Biglietto(nome, cognome, email, numBiglietto, idEvento);
    }
}

