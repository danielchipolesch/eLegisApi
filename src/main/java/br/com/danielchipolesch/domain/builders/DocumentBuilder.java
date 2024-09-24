package br.com.danielchipolesch.domain.builders;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAct;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAttachment;
import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import br.com.danielchipolesch.domain.services.DocumentStatusEnum;

public class DocumentBuilder {

    private DocumentationType documentationType;
    private BasicSubject basicSubject;
    private Integer secondaryNumber;
    private String documentTitle;
    private DocumentStatusEnum documentStatusEnum;
    private DocumentAct documentAct;
    private DocumentAttachment documentAttachment;


    public DocumentBuilder documentationType(DocumentationType documentationType) {
        this.documentationType = documentationType;
        return this;
    }

    public DocumentBuilder basicSubject(BasicSubject basicSubject) {
        this.basicSubject = basicSubject;
        return this;
    }

    public DocumentBuilder secondaryNumber(Integer secondaryNumber) {
        this.secondaryNumber = secondaryNumber;
        return this;
    }

    public DocumentBuilder documentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
        return this;
    }

    public DocumentBuilder documentStatusEnum(DocumentStatusEnum documentStatusEnum) {
        this.documentStatusEnum = documentStatusEnum;
        return this;
    }

    public DocumentBuilder documentAct(DocumentAct documentAct) {
        this.documentAct = documentAct;
        return this;
    }

    public DocumentBuilder documentAttachment(DocumentAttachment documentAttachment) {
        this.documentAttachment = documentAttachment;
        return this;
    }

    public Document build() {
        Document document = new Document();
        document.setDocumentationType(this.documentationType);
        document.setBasicSubject(this.basicSubject);
        document.setSecondaryNumber(this.secondaryNumber);
        document.setDocumentTitle(this.documentTitle);
        document.setDocumentStatusEnum(this.documentStatusEnum);
        document.setDocumentAct(this.documentAct);
        document.setDocumentAttachment(this.documentAttachment);
        return document;
    }
}
