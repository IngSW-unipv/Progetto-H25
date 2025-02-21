package it.unipv.ingsfw.aga.model.banco;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.persistence.PersistenceFacade;

public class BancoGuardarobaFactory {
    private static BancoGuardaroba instance;

    public static BancoGuardaroba getInstance(int numeroBanco, Evento evento) {
        if (instance == null) {
            instance = new BancoGuardaroba(numeroBanco);
            instance.updateGrucceAssegnate(evento);
            instance.updateMaxGrucce(evento);
        }
        return instance;
    }
}
