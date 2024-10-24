package br.com.danielchipolesch.domain.handlers.exceptions.enums;

import lombok.Getter;

@Getter
public enum RegulatoryActException {

    NOT_FOUND("Ato regulatório não encontrado"),
    ALREADY_EXISTS("Ato regulatório já existe");

    private final String message;

    RegulatoryActException(String message){
        this.message=message;
    }
}
