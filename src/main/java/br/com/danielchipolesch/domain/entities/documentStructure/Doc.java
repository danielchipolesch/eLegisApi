package br.com.danielchipolesch.domain.entities.documentStructure;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;

@Data
@Document(collection = "document")
public class Doc {

    @Id
    private String id;

    @Field("tipoDocumento")
    private String documentationType;

    @Field("assuntoBasico")
    private String basicSubject;

    @Field("numeroSecundario")
    private Integer secondaryNumber;

    @Field("nomeDocumento")
    private String documentTitle;

    @Field("documentStatus")
    private String documentStatus;

    @Field("portaria")
    private Long regulatoryActId;

    @Field("anexoTextual")
    @DBRef
    private TextAttachment textAttachment;

    @Field("dataCriacao")
    @CreatedDate
    private Timestamp createdAt;

    @Field("dataAlteracao")
    @LastModifiedDate
    private Timestamp updatedAt;

    @Field("numeroVersao")
    @Version
    private Integer version;
}
