package it.unipv.ingsfw.aga.model.persone;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.QrReadingStrategyFactory;
import it.unipv.ingsfw.aga.model.banco.Type;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.evento.EventoFactory;

import javax.naming.AuthenticationException;
import java.text.ParseException;

public class Organizzatore extends Persona{
    private String password;

    public Organizzatore(String codiceFiscale, String nome, String cognome, String email, String password) {
        super(codiceFiscale, nome, cognome, email);
        this.password = password;
    }
    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void changePassword(String oldPassword, String newPassword) throws AuthenticationException {
        if(oldPassword.equals(password)){
            password = newPassword;
        }else{
            throw new AuthenticationException();
        }
    }
    public Evento creaEvento(String tipoEvento, String data, String location, int maxPartecipanti) throws MaxExeededException, ParseException {
        return EventoFactory.creaEvento(tipoEvento, getCodiceFiscale(), data, location, maxPartecipanti);
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
    
    @Override
    public String toString() {
        return "[Persona]\n" +
                "Tipo: Organizzatore\n" +
                "Nome: " + getNome() + " Cognome: " + getCognome() + "\n" +
                "Email: " + getEmail() + "\n";
    }
}
