package it.unipv.ingsfw.aga.exceptions;

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

