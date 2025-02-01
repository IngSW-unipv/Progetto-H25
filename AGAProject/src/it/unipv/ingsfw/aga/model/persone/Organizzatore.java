package it.unipv.ingsfw.aga.model.persone;

import it.unipv.ingsfw.aga.model.autenticazione.AuthenticationService;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;

import java.time.LocalDate;

public class Organizzatore extends Persona {
    private String password;

    public Organizzatore() {
        super("cf", "nome", "cognome", "email");
        this.password = "password";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(String password) {
        if (this.password.equals(password)) {
            System.out.println("Login effettuato");
        } else {
            System.out.println("Password errata");
        }
    }
    public void creaEvento(String nome, LocalDate data, String location) {
        AuthenticationService.checkOrganizzatorePermission();
        // logica creazione evento
    }

    public void creaBiglietto(){}

    public void cancellaBiglietto(Biglietto biglietto) {}

    public void aperturaVendita() {}

    public void chiusuraVendita() {}

    public void verificaPagamento(){}

    public void verificaAccessoEvento(){}

    public void verificaAccessoGuardaroba(){}

    public void verificaBiglietto(){}



}
