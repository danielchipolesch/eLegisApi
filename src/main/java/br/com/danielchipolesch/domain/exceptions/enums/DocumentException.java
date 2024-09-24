package br.com.danielchipolesch.domain.exceptions.enums;

import br.com.danielchipolesch.domain.services.DocumentStatusEnum;
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

    public static String cannotUpdateForStatus(DocumentStatusEnum status) {
        return switch (status) {
            case APROVADO -> "Documentos aprovados não podem ser alterados.";
            case PUBLICADO -> "Documentos publicados não podem ser alterados.";
            case ARQUIVADO -> "Documentos arquivados não podem ser alterados.";
            case CANCELADO -> "Documentos cancelados não podem ser alterados.";
            case REVOGADO -> "Documentos revogados não podem ser alterados";
            default -> "Estado inválido para atualização.";
        };
    }
}
