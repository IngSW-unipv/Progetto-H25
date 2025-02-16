package it.unipv.ingsfw.aga.model.banco;

public class BancoIngressoFactory {
    private static BancoIngresso instance;

    public static BancoIngresso getInstance(int numeroBanco) {
        if (instance == null) {
            instance = new BancoIngresso(numeroBanco);
        }
        return instance;
    }
}

