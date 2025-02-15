package it.unipv.ingsfw.aga.model.persone;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.QrReadingStrategyFactory;
import it.unipv.ingsfw.aga.model.banco.Type;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.evento.EventoFactory;

import java.util.Date;

public class Organizzatore extends Persona{
    private String password;

    public Organizzatore(String codiceFiscale, String nome, String cognome, String email, String password) {
        super(codiceFiscale, nome, cognome, email);
        this.password = password;
    }
   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Evento creaEvento(String organizzatoreCF, String tipoEvento, Date data, String location, int maxPartecipanti) throws MaxExeededException {
        if(maxPartecipanti < 0){
            throw new IllegalArgumentException("Il numero massimo di partecipanti non può essere negativo");
        }else if(maxPartecipanti > 1500){
            throw new MaxExeededException("Il numero massimo di partecipanti per l'evento in data " + data + " è stato superato");
        }else{
            return EventoFactory.creaEvento(tipoEvento, getCodiceFiscale(), data, location, maxPartecipanti);
        }
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
                "Nome: " + getNome() + " " + getCognome() + "\n" +
                "Email: " + getEmail() + "\n";
    }
}
