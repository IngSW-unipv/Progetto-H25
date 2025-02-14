package it.unipv.ingsfw.aga.model.evento;


import it.unipv.ingsfw.aga.exceptions.MaxExeededException;


import java.util.Date;

public class EventoFactory {
    public static Evento creaEvento(String tipoEvento, String organizzatore, Date data, String location, int maxPartecipanti) throws MaxExeededException {
//        Evento evento = new Evento(data, location, maxPartecipanti);
//        EventoDAO.addEvento(Evento evento);
        return new Evento(organizzatore, data, location, maxPartecipanti);
    }
}
