package it.unipv.ingsfw.aga.model.persone;

import it.unipv.ingsfw.aga.exceptions.PermissionDeniedException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.QrReadingStrategyFactory;
import it.unipv.ingsfw.aga.model.banco.Type;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;

public class Staffer extends Persona{
    private String password;

    public Staffer(String nome, String cognome, String email) {
        super(nome, cognome, email);
        this.password = "changeme";
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    @Override
    public void checkIngresso(Type type, QrCode qrCode){
        IQrReadingStrategy readingStrategy = QrReadingStrategyFactory.getQrReadingStrategy(type);
        readingStrategy.readQR();
    }
    @Override
    public void checkGuardaroba(Type type, QrCode qrCode){
        IQrReadingStrategy readingStrategy = QrReadingStrategyFactory.getQrReadingStrategy(type);
        readingStrategy.readQR();
    }
    public String toString() {
        return "[Persona]\n" +
                "Tipo: Staffer\n" +
                "Nome" + getNome() + getCognome() + "\n" +
                "Email: " + getEmail() + "\n";
    }
}