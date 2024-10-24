package br.com.danielchipolesch.domain.entities.documentStructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_anexo_documento") // Table name must be created
@Data
public class DocumentAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anexo_documento")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "id_documento", nullable = false, updatable = false)
    private Document document;

    @Column(name = "tx_anexo", columnDefinition = "TEXT")
    private String textAttachment;

//    @OneToOne(mappedBy = "documentAttachment")
//    private Document document;

}
