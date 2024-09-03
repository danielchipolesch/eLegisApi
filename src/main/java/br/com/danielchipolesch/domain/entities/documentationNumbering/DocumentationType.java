package br.com.danielchipolesch.domain.entities.documentationNumbering;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "t_especie_normativa")
//@DynamicUpdate
public class DocumentationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sg_especie_normativa", nullable = false)
    private String acronym;

    @Column(name = "nm_especie_normativa", nullable = false)
    private String name;

    @Column(name = "tx_descricao", nullable = false)
    private String description;

    @Column(name = "dt_criacao", updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "dt_edicao")
    @UpdateTimestamp
    private Timestamp editedAt;

    @Version
    @Column(name = "nr_versao", nullable = false)
    private Integer version;
}
