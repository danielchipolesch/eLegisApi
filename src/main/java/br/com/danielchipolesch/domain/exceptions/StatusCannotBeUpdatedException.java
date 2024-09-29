package br.com.danielchipolesch.domain.exceptions;

public class StatusCannotBeUpdatedException extends RuntimeException{
    public StatusCannotBeUpdatedException(String message) {
        super(message);
    }
}
