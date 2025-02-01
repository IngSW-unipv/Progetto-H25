package it.unipv.ingsfw.aga.model.exceptions;

public class AlreadyUsedException extends Exception {
    // Costruttore con un messaggio di errore
    public AlreadyUsedException(String message) {
        super(message);
    }

    // Costruttore con un messaggio di errore e una causa
    public AlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }
}