package br.com.danielchipolesch.domain.exceptions;

public class ResourceCannotBeUpdatedException extends RuntimeException{
    public ResourceCannotBeUpdatedException(String message){
        super(message);
    }
}
