package it.unipv.ingsfw.aga.model.staff;

public class Staffer extends Persona {
    private final String password;

    public Staffer() {
        super("cf", "nome", "cognome", "email");
        this.password = "password";
    }
}
