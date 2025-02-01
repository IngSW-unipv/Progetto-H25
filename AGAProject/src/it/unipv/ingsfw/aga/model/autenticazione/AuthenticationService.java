package it.unipv.ingsfw.aga.model.autenticazione;

import it.unipv.ingsfw.aga.model.persone.Organizzatore;
import it.unipv.ingsfw.aga.model.persone.Staffer;

public class AuthenticationService {
    private static Organizzatore currentOrganizzatore;
    private static Staffer currentStaffer;

    public static void loginOrganizzatore(String password) {
        //da implementare
    }

    public static void loginStaffer(String password) {
        //da implementare
    }

    public static void checkOrganizzatorePermission() {
        if(currentOrganizzatore == null) {
            throw new SecurityException("Accesso negato: necessario login organizzatore");
        }
    }

    public static void checkStaffPermission() {
        if(currentStaffer == null) {
            throw new SecurityException("Accesso negato: necessario login staff");
        }
    }
}