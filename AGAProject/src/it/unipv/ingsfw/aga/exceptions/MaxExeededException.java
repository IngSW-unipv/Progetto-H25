package it.unipv.ingsfw.aga.exceptions;
/**
 * Eccezione lanciata quando il numero massimo di partecipanti è stato superato
 */
public class MaxExeededException extends Exception {
    public MaxExeededException() {
        super("Il numero massimo di partecipanti è stato superato");
    }
    public MaxExeededException(String message) {
        super(message);
    }
}
