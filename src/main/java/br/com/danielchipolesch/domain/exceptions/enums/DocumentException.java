package br.com.danielchipolesch.domain.exceptions.enums;

import lombok.Getter;

@Getter
public enum DocumentException {

    NOT_FOUND("Documento não encontrado"),
    ALREADY_EXISTS("Documento já existe"),
    CANNOT_BE_UPDATED("Documento não está em MINUTA ou RASCUNHO");

    private final String message;

    DocumentException(String message) {
        this.message = message;
    }
}
