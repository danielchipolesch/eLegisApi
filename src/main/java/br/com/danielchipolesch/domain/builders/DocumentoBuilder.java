package br.com.danielchipolesch.domain.builders;

import br.com.danielchipolesch.domain.entities.estruturaDocumento.Documento;
import br.com.danielchipolesch.domain.entities.estruturaDocumento.DocumentoStatusEnum;

public class DocumentoBuilder {

    private String siglaEspecieNormativa;
    private String nomeAssuntoBasico;
    private Integer numeroSecundario;
    private String tituloDocumento;
    private DocumentoStatusEnum documentoStatusEnum;
//    private TextAttachment textAttachment;


    public DocumentoBuilder especieNormativa(String siglaEspecieNormativa) {
        this.siglaEspecieNormativa = siglaEspecieNormativa;
        return this;
    }

    public DocumentoBuilder assuntoBasico(String nomeAssuntoBasico) {
        this.nomeAssuntoBasico = nomeAssuntoBasico;
        return this;
    }

    public DocumentoBuilder numeroSecundario(Integer numeroSecundario) {
        this.numeroSecundario = numeroSecundario;
        return this;
    }

    public DocumentoBuilder tituloDocumento(String tituloDocumento) {
        this.tituloDocumento = tituloDocumento;
        return this;
    }

    public DocumentoBuilder documentoStatus(DocumentoStatusEnum documentoStatusEnum) {
        this.documentoStatusEnum = documentoStatusEnum;
        return this;
    }

//    public DocumentoBuilder textAttachment(TextAttachment textAttachment) {
//        this.textAttachment = textAttachment;
//        return this;
//    }

    public Documento build() {
        Documento documento = new Documento();
        documento.setEspecieNormativa(this.siglaEspecieNormativa);
        documento.setAssuntoBasico(this.nomeAssuntoBasico);
        documento.setNumeroSecundario(this.numeroSecundario);
        documento.setTituloDocumento(this.tituloDocumento);
        documento.setDocumentoStatus(this.documentoStatusEnum.name());
//        documento.setTextAttachment(this.textAttachment);
        return documento;
    }
}
