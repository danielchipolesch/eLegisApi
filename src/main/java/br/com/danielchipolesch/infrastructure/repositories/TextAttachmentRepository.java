package br.com.danielchipolesch.infrastructure.repositories;

import br.com.danielchipolesch.domain.entities.documentStructure.TextAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextAttachmentRepository extends JpaRepository<TextAttachment, Long> {
}
