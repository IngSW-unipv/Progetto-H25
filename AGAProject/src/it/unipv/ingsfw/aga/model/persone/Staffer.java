package it.unipv.ingsfw.aga.model.persone;

public class Staffer extends Persona {
    private final String password;

    public Staffer() {
        super("cf", "nome", "cognome", "email");
        this.password = "password";
    }
}
