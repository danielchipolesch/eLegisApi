package br.com.danielchipolesch.domain.builders;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import br.com.danielchipolesch.domain.entities.documentStructure.RegulatoryAct;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentAttachment;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;

public class DocumentBuilder {

    private DocumentationType documentationType;
    private BasicSubject basicSubject;
    private Integer secondaryNumber;
    private String documentTitle;
    private DocumentStatus documentStatus;
    private RegulatoryAct regulatoryAct;
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

    public DocumentBuilder documentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
        return this;
    }

    public DocumentBuilder documentAct(RegulatoryAct regulatoryAct) {
        this.regulatoryAct = regulatoryAct;
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
        document.setDocumentStatus(this.documentStatus);
        document.setRegulatoryAct(this.regulatoryAct);
        document.setDocumentAttachment(this.documentAttachment);
        return document;
    }
}
