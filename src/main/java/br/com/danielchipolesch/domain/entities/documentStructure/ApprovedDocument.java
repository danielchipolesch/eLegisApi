package br.com.danielchipolesch.domain.entities.documentStructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_documento_aprovado")
@Data
public class ApprovedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento_aprovado")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_documento")
    private Document document;

    //TODO Criar demais atributos
}
