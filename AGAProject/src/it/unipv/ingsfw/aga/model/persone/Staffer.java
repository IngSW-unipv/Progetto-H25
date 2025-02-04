package it.unipv.ingsfw.aga.model.persone;

public class Staffer extends Invitato{
    private String password;

    public Staffer(String nome, String cognome, String email) {
        super(nome, cognome, email);
        this.password = "changeme";
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void checkIngresso(){
        //TODO
    }

    public void checkGuardaroba(){
        //TODO
    }

    public Invitato creaInvitato(String nome, String cognome, String email){
        return new Invitato(nome, cognome, email);
        //TODO limite aggiunte
    }
}
