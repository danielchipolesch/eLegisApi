package br.com.danielchipolesch.domain.exceptions;

public class ResourceAlreadyExistsException extends  RuntimeException{
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
