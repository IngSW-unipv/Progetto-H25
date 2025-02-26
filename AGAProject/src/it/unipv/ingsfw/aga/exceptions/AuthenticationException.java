package it.unipv.ingsfw.aga.exceptions;
/**
 * Eccezione lanciata in caso di errore di autenticazione.
 */
public class AuthenticationException extends RuntimeException {

  public AuthenticationException() {
    super("Errore di autenticazione!");
  }

  public AuthenticationException(String message) {
    super(message);
  }

  public AuthenticationException(String message, Throwable cause) {
    super(message, cause);
  }
}

