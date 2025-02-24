package br.com.danielchipolesch.domain.builders;

import br.com.danielchipolesch.domain.entities.documentStructure.Doc;
import br.com.danielchipolesch.domain.entities.documentStructure.DocumentStatus;
import br.com.danielchipolesch.domain.entities.documentStructure.TextAttachment;

public class DocBuilder {

    private String documentationTypeAcronym;
    private String basicSubjectName;
    private Integer secondaryNumber;
    private String documentTitle;
    private DocumentStatus documentStatus;
    private TextAttachment textAttachment;


    public DocBuilder documentationType(String documentationType) {
        this.documentationTypeAcronym = documentationType;
        return this;
    }

    public DocBuilder basicSubject(String basicSubject) {
        this.basicSubjectName = basicSubject;
        return this;
    }

    public DocBuilder secondaryNumber(Integer secondaryNumber) {
        this.secondaryNumber = secondaryNumber;
        return this;
    }

    public DocBuilder documentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
        return this;
    }

    public DocBuilder documentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
        return this;
    }

    public DocBuilder textAttachment(TextAttachment textAttachment) {
        this.textAttachment = textAttachment;
        return this;
    }

    public Doc build() {
        Doc doc = new Doc();
        doc.setDocumentationType(this.documentationTypeAcronym);
        doc.setBasicSubject(this.basicSubjectName);
        doc.setSecondaryNumber(this.secondaryNumber);
        doc.setDocumentTitle(this.documentTitle);
        doc.setDocumentStatus(this.documentStatus.name());
        doc.setTextAttachment(this.textAttachment);
        return doc;
    }
}
