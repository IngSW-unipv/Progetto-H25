package it.unipv.ingsfw.aga.model.biglietto;

import it.unipv.ingsfw.aga.exceptions.MaxExeededException;
import it.unipv.ingsfw.aga.model.evento.Evento;
import it.unipv.ingsfw.aga.model.persone.Persona;

import java.text.ParseException;

public class MainBiglietto {
    public static void main(String[] args) throws MaxExeededException, ParseException {
        Persona persona = new Persona("ALEBUS123", "Ale", "Bus", "alebus@gmail.com");
        Evento evento = new Evento("2023-12-31", "MEDA", 1000);
        Biglietto biglietto = new Biglietto(persona, evento, "Tony", "Effe", "Tonyeffe@gmail.com");

        Stampabile vipBiglietto = new VipDecorator(biglietto);
        Stampabile staffBiglietto = new StaffDecorator(biglietto);

        System.out.println(vipBiglietto.stampaBiglietto());
        System.out.println(staffBiglietto.stampaBiglietto());
    }
}