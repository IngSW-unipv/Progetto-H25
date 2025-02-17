package it.unipv.ingsfw.aga.model.banco;
import it.unipv.ingsfw.aga.model.evento.Evento;

public class BancoIngressoFactory {
    private static BancoIngresso instance;

    public static BancoIngresso getInstance(int numeroBanco, Evento evento) {
        if (instance == null) {
            instance = new BancoIngresso(numeroBanco, evento);
        }
        return instance;
    }
}

