package it.unipv.ingsfw.aga.model.persone;

public class Persona {
    private String nome;
    private String cognome;
    private String email;


    public Persona(String nome, String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Nome: " + nome + " Cognome: " + cognome + " Email: " + email;
    }
}
