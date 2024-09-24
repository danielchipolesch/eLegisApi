package br.com.danielchipolesch.application.helpers;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.services.DocumentStatusEnum;

public class DocumentHelper {

    public static void updateStatusDocument(Document document, DocumentStatusEnum documentStatusEnum) {
       document.setDocumentStatusEnum(documentStatusEnum);
    }
}
