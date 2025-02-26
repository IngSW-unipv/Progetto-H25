package it.unipv.ingsfw.aga.model.persone;

import it.unipv.ingsfw.aga.persistence.PersistenceFacade;

public class MainPersona {
    //Attenzione: modifica il DB!
    public static void main(String[] args) {
        Persona nuovaPersona = PersonaFactory.creaPersona("organizzatore", "FRAPA123", "Francesco", "Dj", "fradj@gmail.com", "psw123");
        PersistenceFacade facade = PersistenceFacade.getInstance();
        // Aggiungi la persona al database
        boolean aggiuntaRiuscita = facade.registazioneOrganizzatore(nuovaPersona.getCodiceFiscale(), nuovaPersona.getNome(), nuovaPersona.getCognome(), nuovaPersona.getEmail(), nuovaPersona.getPassword());
        if (aggiuntaRiuscita) {
            System.out.println("Persona aggiunta con successo!");
        } else {
            System.out.println("Errore nell'aggiunta della persona.");
        }
    }
}