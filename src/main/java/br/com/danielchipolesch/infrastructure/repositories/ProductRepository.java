package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
