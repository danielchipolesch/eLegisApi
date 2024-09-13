package br.com.danielchipolesch.domain.entities.documentStructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "") // Table name must be created
@Data
public class DocumentAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anexo_documento")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String textDocument;

    @OneToOne(mappedBy = "documentAttachment")
    private Document document;
}
