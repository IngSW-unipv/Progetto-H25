package it.unipv.ingsfw.aga.model.persone;


import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.QrReadingStrategyFactory;
import it.unipv.ingsfw.aga.model.banco.Type;
import it.unipv.ingsfw.aga.model.banco.qrReadingStrategy.IQrReadingStrategy;

import it.unipv.ingsfw.aga.exceptions.AuthenticationException;

public class Staffer extends Persona{
    private String password;
    //private int limiteInviti;

    public Staffer(String codiceFiscale, String nome, String cognome, String email) {
        super(codiceFiscale, nome, cognome, email);
        //this.limiteInviti = 5;
        this.password = "changeme";
    }
    
    public Staffer(String codiceFiscale, String nome, String cognome, String email, String password) {
        super(codiceFiscale, nome, cognome, email);
        //this.limiteInviti = 5;
        this.password = password;//per il db se no non riesco a prelevare tutti i dati
    }
    
    /*public Staffer(String email, String password) {
        super(null,null,null,email);
        this.password =password;
    }*/

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