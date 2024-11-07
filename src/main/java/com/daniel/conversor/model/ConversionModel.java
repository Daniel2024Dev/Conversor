package com.daniel.conversor.model;

// Define a classe ConversionModel, que representa uma conversão de unidades
public class ConversionModel {
    // Declaração das variáveis de instância
    private int id;
    private final String title;
    private final String description;

    // Construtor que inicializa a conversão com todos os atributos (usado quando a conversão já tem um ID)
    public ConversionModel(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // Construtor que inicializa a conversão sem o ID (usado para novas conversões antes de serem armazenadas no banco)
    public ConversionModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getter para obter o ID da conversão
    public int getId() {
        // Retorna o ID
        return id;
    }

    // Getter para obter o título da conversão
    public String getTitle() {
        // Retorna o título
        return title;
    }

    // Getter para obter a descrição da conversão
    public String getDescription() {
        // Retorna a descrição
        return description;
    }
}
