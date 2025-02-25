package it.unipv.ingsfw.aga.model.evento;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.persone.Organizzatore;

import java.text.ParseException;

// Verifica l'accesso corretto al DB e l'aggiunta del nuovo evento
public class MainEvento {
    //Attenzione: modifica il DB!
    public static void main(String[] args) {
        try {
            // Creazione di un organizzatore
            Organizzatore organizzatore = new Organizzatore("FRAPA123", "Francesco", "Dj", "fradj@gmail.com", "psw123");
            // Creazione dell'evento (Serve per aprire e chiudere le vendite)
            Evento evento = new Evento ("2025-09-12", "Firenze", 50);

            // Creazione di un evento
            String tipoEvento = "TipoEvento";
            String data = "2025-09-12";
            String location = "Firenze";
            int maxPartecipanti = 50;

            // Aggiunta dell'evento al database
            Evento evento2 = organizzatore.creaEvento(tipoEvento, data, location, maxPartecipanti);
            System.out.println("Evento creato e aggiunto al database: " + evento);

            // Apertura delle vendite
            organizzatore.setVenditeAperte(evento, true);
            System.out.println("Vendite aperte per l'evento: " + evento);

            // Chiusura delle vendite
            organizzatore.setVenditeAperte(evento, false);
            System.out.println("Vendite chiuse per l'evento: " + evento);

        } catch (MaxExeededException | ParseException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.err.println("Errore durante la creazione dell'evento: " + e.getMessage());
        }
    }
}
