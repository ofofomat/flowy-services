package br.edu.catolicasc.flowyservices.exception;

public class ResourceNotFoundException extends Error {
  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
  
}
