package it.unipv.ingsfw.aga.model.persone;

public class PersonaFactory {
    public static Persona creaPersona(String tipo, String codiceFiscale, String nome, String cognome, String email, String password) {
        return switch (tipo.toLowerCase()) {
            case "organizzatore" -> new Organizzatore(codiceFiscale, nome, cognome, email, password);
            case "staffer" -> new Staffer(codiceFiscale, nome, cognome, email);
            default -> throw new IllegalArgumentException("Tipo di persona non valido: " + tipo);
        };
    }
}

