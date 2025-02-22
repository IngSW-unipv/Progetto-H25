package it.unipv.ingsfw.aga.model.evento;


import it.unipv.ingsfw.aga.exceptions.MaxExeededException;


import java.text.ParseException;

public class EventoFactory {
    public static Evento creaEvento(String tipoEvento, String organizzatore, String dataString, String location, int maxPartecipanti) throws MaxExeededException, ParseException {
        return new Evento(dataString, location, maxPartecipanti);
    }
}
