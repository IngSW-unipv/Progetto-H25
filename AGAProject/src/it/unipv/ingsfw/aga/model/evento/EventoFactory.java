package it.unipv.ingsfw.aga.model.evento;


import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.exceptions.PermissionDeniedException;


import java.text.ParseException;
/**
 * Classe factory per la creazione di eventi.
 */
public class EventoFactory {
    /**
     * Metodo per la creazione di eventi
     * @param tipoEvento garantisce la possibilità di creare eventi di diverso tipo per future implementazioni
     * @param organizzatore codice fiscale dell'organizzatore
     * @param dataString data dell'evento identifica in modo unico l'evento
     * @param location luogo dell'evento
     * @param maxPartecipanti numero massimo di partecipanti all'evento
     * @return l'evento creato
     * @throws MaxExeededException se il numero massimo di partecipanti è superato
     * @throws PermissionDeniedException se l'utente non ha i permessi per creare un evento
     */
    public static Evento creaEvento(String tipoEvento, String organizzatore, String dataString, String location, int maxPartecipanti) throws MaxExeededException, ParseException {
        return new Evento(dataString, location, maxPartecipanti);
    }
}
