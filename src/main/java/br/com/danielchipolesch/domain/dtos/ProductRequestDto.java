package br.com.danielchipolesch.domain.dtos;

public record ProductRequestDto(String name,
                                Double price,
                                Integer quantity,
                                Long supplierId) {
}
