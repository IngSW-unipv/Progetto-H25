package it.unipv.ingsfw.aga.model.persone;


import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.QrReadingStrategyFactory;
import it.unipv.ingsfw.aga.model.banco.Type;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;

import it.unipv.ingsfw.aga.exceptions.AuthenticationException;
/**
 * Classe che rappresenta uno Staffer.
 * Estende la classe Persona.
 * Si occupa di gestire l'accesso e il guardaroba.
 */
public class Staffer extends Persona{
    private String password;

    public Staffer(String codiceFiscale, String nome, String cognome, String email) {
        super(codiceFiscale, nome, cognome, email);
        this.password = "changeme";
    }
    public Staffer(String codiceFiscale, String nome, String cognome, String email, String password) {
        super(codiceFiscale, nome, cognome, email);
        this.password = password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void changePassword(String oldPassword, String newPassword) throws AuthenticationException {
        if(oldPassword.equals(password)){
            password = newPassword;
        }else{
            throw new AuthenticationException("Vecchia Password errata!");
        }
    }
    @Override
    public String getPassword(){
        return password;
    }
    // logica sviluppata nei banchi
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
                "Tipo: Staffer\n" +
                "Nome: " + getNome() +" Cognome: "+ getCognome() + "\n" +
                "Email: " + getEmail() + "\n";
    }
}