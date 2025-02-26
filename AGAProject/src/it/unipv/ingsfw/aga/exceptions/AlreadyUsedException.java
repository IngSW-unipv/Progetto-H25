package it.unipv.ingsfw.aga.exceptions;
/**
 * Eccezione lanciata quando si tenta di creare un evento in una data già occupata.
 */
public class AlreadyUsedException extends Exception {

    public AlreadyUsedException(String message) {
        super(message);
    }

    public AlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }
}