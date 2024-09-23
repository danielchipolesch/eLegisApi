package br.com.danielchipolesch.domain.entities.documentationNumbering;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "t_especie_normativa")
//@DynamicUpdate
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DocumentationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especie_normativa")
    private Long id;

    @Column(name = "sg_especie_normativa", nullable = false)
    private String acronym;

    @Column(name = "nm_especie_normativa", nullable = false)
    private String name;

    @Column(name = "tx_descricao", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "dt_criacao", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "dt_alteracao")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Version
    @Column(name = "nr_versao", nullable = false)
    private Integer version;
}
