package it.unipv.ingsfw.aga.model.persone;

public class PersonaFactory {
    public static Persona creaPersona(String tipo, String nome, String cognome, String email) {
        switch (tipo.toLowerCase()) {
            case "organizzatore":
                return new Organizzatore(nome, cognome, email);
            case "staffer":
                return new Staffer(nome, cognome, email);
            default:
                throw new IllegalArgumentException("Tipo di persona non valido: " + tipo);
        }
    }
}

