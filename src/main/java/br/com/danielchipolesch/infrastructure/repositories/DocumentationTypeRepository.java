package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.documentationNumbering.DocumentationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentationTypeRepository extends JpaRepository<DocumentationType, Long> {
    boolean existsByAcronym(String documentationTypeEnumAcronym);
}
