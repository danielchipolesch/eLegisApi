package br.com.danielchipolesch.domain.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

}
