package it.unipv.ingsfw.aga.model.staff;

public class Persona {
    private String nome, cognome, email, cf;

    public Persona(String nome, String cognome, String email, String cf) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.cf = cf;
    }
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }
    public String getCf() {
        return cf;
    }
}
