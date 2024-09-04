package br.com.danielchipolesch.infrastructure.enums;

import jakarta.persistence.Entity;

public enum DocumentHeaderEnum {

    MD("MINISTÉRIO DA DEFESA"),
    COMAER("COMANDO DA AERONÁUTICA");

    private final String description;

    DocumentHeaderEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
