package br.com.danielchipolesch.domain.entities.documentStructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "") //TODO Insert table name
@Data
public class DocumentAct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ato_normativo")
    private Long id;

    @Column(name = "nm_ato_normativo")
    private String name;

    @Column(name = "bt_conteudo_ato_normativo")
    private byte[] content;

    @OneToOne(mappedBy = "documentAct")
    private Document document;

}
