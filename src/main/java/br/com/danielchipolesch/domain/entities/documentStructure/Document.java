package br.com.danielchipolesch.domain.entities.documentStructure;

import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Entity
@Table(name = "") //TODO Insert table name
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_assunto_basico")
    private BasicSubject basicSubject;

    @ManyToOne
    @JoinColumn(name = "id_especie_normativa")
    private DocumentationType documentationType;

    @Column(name = "nr_numero_secundario")
    private Long secondaryNumber;

    @Column(name = "nm_titulo_documento")
    private String documentTitle;

    @OneToOne
    @JoinColumn(name = "id_informacao_documento", nullable = false)
    private DocumentInformation documentInformation;

    @OneToOne
    @JoinColumn(name = "id_ato_normativo", nullable = false)
    private DocumentAct documentAct;

    @OneToOne
    @JoinColumn(name = "id_anexo_documento", nullable = false)
    private DocumentAttachment documentAttachment;

    @Column(name = "dt_criacao", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "dt_alteracao")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "nr_versao", nullable = false)
    @Version
    private Integer version;
}
