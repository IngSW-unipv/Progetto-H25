package it.unipv.ingsfw.aga.model.persone;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.evento.Evento;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Organizzatore extends Persona{
    private Map<LocalDate, Evento> eventi = new HashMap<>();    //non necessario
    private String password;

    public Organizzatore(String nome, String cognome, String email) {
        super(nome, cognome, email);
        this.password = "changeme";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void creaEvento(Organizzatore organizzatore, LocalDate data, String location, int maxPartecipanti) throws MaxExeededException {
        if (eventi.containsKey(data)) {
            throw new IllegalArgumentException("Esiste già un evento per questa data");
        }else if (maxPartecipanti < 0) {
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        }else if (maxPartecipanti > 1500) {
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + data + " è stato superato");
        }else{
            eventi.put(data, new Evento(organizzatore, data, location, maxPartecipanti));
        }
    }
}
