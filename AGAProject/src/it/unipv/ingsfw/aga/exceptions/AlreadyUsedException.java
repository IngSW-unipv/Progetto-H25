package it.unipv.ingsfw.aga.exceptions;

public class AlreadyUsedException extends Exception {

    public AlreadyUsedException(String message) {
        super(message);
    }

    public AlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }
}