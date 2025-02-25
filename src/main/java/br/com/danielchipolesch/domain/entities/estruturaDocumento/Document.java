package br.com.danielchipolesch.domain.entities.estruturaDocumento;

import br.com.danielchipolesch.domain.entities.numeracaoDocumento.AssuntoBasico;
import br.com.danielchipolesch.domain.entities.numeracaoDocumento.EspecieNormativa;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Document extends RepresentationModel<Document> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_especie_normativa", nullable = false)
    private EspecieNormativa especieNormativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_assunto_basico", nullable = false)
    private AssuntoBasico assuntoBasico;

    @Column(name = "nr_numero_secundario", nullable = false)
    private Integer secondaryNumber;

    @Column(name = "nm_titulo_documento", nullable = false)
    private String documentTitle;

    @Column(name = "st_documento", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentoStatusEnum documentoStatusEnum;

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id_ato_normativo")
//    @OneToOne(mappedBy = "document", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private RegulatoryAct regulatoryAct;

//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "id_anexo_documento", nullable = false, updatable = false)
    @OneToOne(mappedBy = "document", fetch = FetchType.LAZY)
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
