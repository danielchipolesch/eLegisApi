package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.documentStructure.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
