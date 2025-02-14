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
        Persona persona = PersonaFactory.creaPersona("Organizzatore", "Mario", "Rossi","test@gmail.com");
        System.out.println(persona.toString());
        Evento evento = persona.creaEvento("Concerto", new Date(2021, 10, 10), "Milano", 100);
        System.out.println(evento.toString());
        Biglietto b1 = evento.aggiungiBiglietto("Giovanni", "Bianchi", "gb@gmail,com", evento.getIdEvento(), false);
        Biglietto b2 = evento.aggiungiBiglietto("Giuseppe", "Verdi", "gv@gmail,com", evento.getIdEvento(), false);
        Biglietto b3 = evento.aggiungiBiglietto("Giuseppe", "Rossi", "gv@gmail,com", evento.getIdEvento(), true);
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b3.toString());
    }
}
