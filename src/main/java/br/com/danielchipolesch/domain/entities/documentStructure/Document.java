package br.com.danielchipolesch.domain.entities.documentStructure;

import br.com.danielchipolesch.domain.entities.documentationNumbering.BasicSubject;
import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Timestamp;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_documento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Document extends RepresentationModel<Document> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_especie_normativa", nullable = false)
    private DocumentationType documentationType;

    @ManyToOne
    @JoinColumn(name = "id_assunto_basico", nullable = false)
    private BasicSubject basicSubject;

    @Column(name = "nr_numero_secundario", nullable = false)
    private Integer secondaryNumber;

    @Column(name = "nm_titulo_documento", nullable = false)
    private String documentTitle;

    @Column(name = "st_documento", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentStatus documentStatus;

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id_ato_normativo")
    @OneToOne(mappedBy = "document")
    private RegulatoryAct regulatoryAct;

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id_anexo_documento", nullable = false, updatable = false)
    @OneToOne(mappedBy = "document")
    private TextAttachment textAttachment;

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
