package it.unipv.ingsfw.aga.model.biglietto;

import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;

public class BigliettoFactory {//DA CONTROLLARE ALE
    public static Biglietto creaBiglietto(Persona creatoreBiglietto, Evento evento, String nome, String cognome, String email) {
        return new Biglietto(creatoreBiglietto, nome, cognome, email, evento);
    }
}

