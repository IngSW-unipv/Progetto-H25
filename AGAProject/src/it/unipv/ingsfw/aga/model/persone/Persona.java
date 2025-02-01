package it.unipv.ingsfw.aga.model.persone;

public class Persona {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String email;

    public Persona(String codiceFiscale, String nome, String cognome, String email) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String toString() {
        return "Nome: " + nome + " Cognome: " + cognome + " Email: " + email + " CF: " + codiceFiscale;
    }
}
