package br.com.danielchipolesch.domain.exceptions;

import lombok.Getter;

@Getter
public enum DocumentException {

    NOT_FOUND("Documento não encontrado"),
    ALREADY_EXISTS("Documento já existe");

    private final String message;

    DocumentException(String message) {
        this.message = message;
    }
}
