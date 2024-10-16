package br.com.danielchipolesch.domain.entities.documentStructure;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_ato_normativo")
@Data
public class RegulatoryAct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ato_normativo")
    private Long id;

    @Column(name = "nm_ato_normativo")
    private String fileName;

    @Lob
    @Column(name = "bt_conteudo_ato_normativo")
    private byte[] data;
//
//    @OneToOne(mappedBy = "documentAct")
//    private Document document;
}
