package it.unipv.ingsfw.aga.exceptions;

/**
 * Eccezione lanciata quando un utente non dispone delle autorizzazioni necessarie per eseguire un'operazione.
 */
public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException() {
        super("Non disponi dell'autorizzazione necessaria per eseguire questa operazione");
    }

    public PermissionDeniedException(String message) {
        super(message);
    }

    public PermissionDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}

