package br.com.danielchipolesch.domain.entities.estruturaDocumento;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
import java.util.List;

@Data
@Document(collection = "documento")
public class Documento {

    @Id
    private String id;

    @Field("especieNormativa")
    private String especieNormativa;

    @Field("assuntoBasico")
    private String assuntoBasico;

    @Field("numeroSecundario")
    private Integer numeroSecundario;

    @Field("nomeDocumento")
    private String tituloDocumento;

    @Field("documentStatus")
    private String documentoStatus;

    @Field("portaria")
    private Long idPortaria;

    @Field("parteNormativa")
    @DBRef
    private List<AnexoParteNormativaItem> itens;

    @Field("dataCriacao")
    @CreatedDate
    private Timestamp dtCriacao;

    @Field("dataAlteracao")
    @LastModifiedDate
    private Timestamp dtAlteracao;

    @Field("numeroVersao")
    @Version
    private Integer versao;
}
