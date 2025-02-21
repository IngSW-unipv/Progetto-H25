package it.unipv.ingsfw.aga.exceptions;

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

