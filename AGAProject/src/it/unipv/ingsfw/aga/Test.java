package it.unipv.ingsfw.aga;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.biglietto.Biglietto;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;
import it.unipv.ingsfw.aga.model.persone.PersonaFactory;

import java.sql.Date;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, MaxExeededException {
        Persona persona1 = PersonaFactory.creaPersona("Organizzatore", "MFFRR0101","Mario", "Rossi","test@gmail.com", "psw123");
        System.out.println(persona1.toString());
        Persona persona2 = PersonaFactory.creaPersona("Staffer", "PPRR1100C","Piero", "Rossini","piero@gmail.com", null);
        System.out.println(persona1.toString());
        Evento evento = persona1.creaEvento("Festa", persona1.getCodiceFiscale(), new Date(2021, 10, 10), "Milano", 100);
        System.out.println(evento.toString());
        Biglietto b1 = evento.aggiungiBiglietto("GIOBIA01","Giovanni", "Bianchi", "gb@gmail.com", evento.getData(), false);
        Biglietto b2 = evento.aggiungiBiglietto("GIUVER01","Giuseppe", "Verdi", "gv@gmail.com", evento.getData(), false);
        Biglietto b3 = evento.aggiungiBiglietto("GIUROS01","Giuseppe", "Rossi", "gv@gmail.com", evento.getData(), true);
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b3.toString());
    }
}
