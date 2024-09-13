package br.com.danielchipolesch.domain.entities.documentationNumbering;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "t_assunto_basico")
public class BasicSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assunto_basico")
    private Long id;

    @Column(name = "nr_assunto_basico", length = 4, nullable = false)
    private String basicNumber;

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
