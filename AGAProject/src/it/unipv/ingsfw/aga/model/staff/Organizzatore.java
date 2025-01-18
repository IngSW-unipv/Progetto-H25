package it.unipv.ingsfw.aga.model.staff;

import it.unipv.ingsfw.aga.model.biglietto.Biglietto;

public class Organizzatore extends Persona {
    private final String password;

    public Organizzatore() {
        super("cf", "nome", "cognome", "email");
        this.password = "password";
    }
    public Biglietto aggiungiBiglietto(String nome, String cognome, String email, String cf){
        return new Biglietto("id"); //TODO: logica per la creazione di id unico
    }
    public void aperturaVendite() {
    // TODO: logica per l'apertura delle vendite
    }
    public void chiusuraVendite(){

    }


}
