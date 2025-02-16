package it.unipv.ingsfw.aga.model.banco;

public class BancoGuardarobaFactory {
    private static BancoGuardaroba instance;

    public static BancoGuardaroba getInstance(int numeroBanco) {
        if (instance == null) {
            instance = new BancoGuardaroba(numeroBanco);
        }
        return instance;
    }
}
