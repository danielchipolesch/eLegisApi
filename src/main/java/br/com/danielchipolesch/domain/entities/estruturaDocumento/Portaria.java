package br.com.danielchipolesch.domain.entities.estruturaDocumento;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "t_ato_normativo")
@Data
public class Portaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ato_normativo")
    private Long id;

    @Column(name = "nm_ato_normativo")
    private String fileName;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
//    @JoinColumn(name = "id_documento")
//    private Document document;

    @Lob
    @Column(name = "bt_conteudo_ato_normativo")
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;
//
//    @OneToOne(mappedBy = "documentAct")
//    private Document document;
}
