package br.com.danielchipolesch.domain.exceptions;

import lombok.Getter;

@Getter
public enum DocumentationTypeException {

    NOT_FOUND("Espécie normativa não encontrada"),
    ALREADY_EXISTS("Espécie normativa já existe");

    private final String message;

    DocumentationTypeException(String message) {
        this.message = message;
    }
}
