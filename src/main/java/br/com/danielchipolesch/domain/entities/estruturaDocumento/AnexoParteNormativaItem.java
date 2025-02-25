package br.com.danielchipolesch.domain.entities.estruturaDocumento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnexoParteNormativaItem {

    private String id;
    private String type;  // Ex: "CAPÍTULO", "SEÇÃO", "ARTIGO"
    private String title; // Nome do item normativo
    private String content; // Texto do item
    private List<AnexoParteNormativaItem> children; // Lista de elementos filhos
}
