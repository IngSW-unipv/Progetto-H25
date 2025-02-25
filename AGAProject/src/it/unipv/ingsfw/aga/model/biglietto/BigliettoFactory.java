package it.unipv.ingsfw.aga.model.biglietto;


import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;

/**
 * Classe per la creazione di biglietti.
 */
public class BigliettoFactory {
    /**
     * Metodo per aggiungere biglietti all'evento.
     * @param creatoreBiglietto persona che crea il biglietto
     * @param evento evento a cui si vuole aggiungere il biglietto
     * @param nome nome del partecipante
     * @param cognome cognome del partecipante
     * @param email email del partecipante
     * @return Biglietto
     */
    public static Biglietto creaBiglietto(Persona creatoreBiglietto, Evento evento, String nome, String cognome, String email) {
        return new Biglietto(creatoreBiglietto, nome, cognome, email, evento);
    }
}

