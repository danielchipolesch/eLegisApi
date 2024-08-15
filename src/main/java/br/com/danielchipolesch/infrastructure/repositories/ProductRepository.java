package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
