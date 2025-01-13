package br.com.danielchipolesch.domain.entities.documentStructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_anexo_texto") // Table name must be created
@Data
public class TextAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_anexo_texto")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinColumn(name = "id_documento", nullable = false, updatable = false)
    private Document document;

    @Column(name = "tx_anexo_texto", columnDefinition = "TEXT")
    private String textAttachment;

//    @OneToOne(mappedBy = "documentAttachment")
//    private Document document;

}
