package it.unipv.ingsfw.aga.model.persone;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.exceptions.PermissionDeniedException;
import it.unipv.ingsfw.aga.model.banco.QrCode;
import it.unipv.ingsfw.aga.model.banco.Type;
import it.unipv.ingsfw.aga.model.evento.Evento;

import java.util.Date;

public class Persona {
    private String nome;
    private String cognome;
    private String email;

    public Persona(String nome, String cognome, String email) {
        this.nome=  nome;
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
    public Evento creaEvento(String tipoEvento, Date data, String location, int maxPartecipanti) throws MaxExeededException {
        throw new PermissionDeniedException();
    }
    public void checkIngresso(Type type, QrCode qrCode){
        throw new PermissionDeniedException();
    }
    public void checkGuardaroba(Type type, QrCode qrCode){
        throw new PermissionDeniedException();
    }
    @Override
    public String toString() {
        return "[Persona]\n" +
                "Tipo: Nessuno\n" +
                "Nome" + nome + cognome + "\n" +
                "Email: " + email + "\n";
    }
}