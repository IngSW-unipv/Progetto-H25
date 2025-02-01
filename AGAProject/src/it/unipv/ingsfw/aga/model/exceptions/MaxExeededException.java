package it.unipv.ingsfw.aga.model.exceptions;

public class MaxExeededException extends Exception {
    public MaxExeededException(String message) {
        super(message);
    }
    public MaxExeededException(String message, Throwable cause) {
        super(message, cause);
    }
}
