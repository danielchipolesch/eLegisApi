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
@Table(name = "t_assunto_basico")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BasicSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assunto_basico")
    private Long id;

    @Column(name = "cd_assunto_basico", length = 4, nullable = false)
    private String code;

    @Column(name = "nm_classificacao", nullable = false)
    private String name;

    @Column(name = "tx_descricao", columnDefinition = "TEXT", nullable = false)
    private String description;

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
