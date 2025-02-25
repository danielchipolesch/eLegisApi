package br.com.danielchipolesch.domain.builders;

import br.com.danielchipolesch.domain.entities.estruturaDocumento.Document;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.DocumentoStatusEnum;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.TextAttachment;
import br.com.danielchipolesch.domain.entities.numeracaoDocumento.AssuntoBasico;
import br.com.danielchipolesch.domain.entities.numeracaoDocumento.EspecieNormativa;

public class DocumentBuilder {

    private EspecieNormativa especieNormativa;
    private AssuntoBasico assuntoBasico;
    private Integer secondaryNumber;
    private String documentTitle;
    private DocumentoStatusEnum documentoStatusEnum;
    private TextAttachment textAttachment;


    public DocumentBuilder documentationType(EspecieNormativa especieNormativa) {
        this.especieNormativa = especieNormativa;
        return this;
    }

    public DocumentBuilder basicSubject(AssuntoBasico assuntoBasico) {
        this.assuntoBasico = assuntoBasico;
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

    public DocumentBuilder documentStatus(DocumentoStatusEnum documentoStatusEnum) {
        this.documentoStatusEnum = documentoStatusEnum;
        return this;
    }

    public DocumentBuilder textAttachment(TextAttachment textAttachment) {
        this.textAttachment = textAttachment;
        return this;
    }

    public Document build() {
        Document document = new Document();
        document.setEspecieNormativa(this.especieNormativa);
        document.setAssuntoBasico(this.assuntoBasico);
        document.setSecondaryNumber(this.secondaryNumber);
        document.setDocumentTitle(this.documentTitle);
        document.setDocumentoStatusEnum(this.documentoStatusEnum);
        document.setTextAttachment(this.textAttachment);
        return document;
    }
}
