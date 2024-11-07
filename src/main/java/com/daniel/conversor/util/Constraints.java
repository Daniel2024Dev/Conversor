package com.daniel.conversor.util;
// Importação das classes necessárias para a aplicação

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

// Classe utilitária para lidar com restrições e formatação de campos de texto
public class Constraints {
    // Aplica uma máscara de formatação de número decimal em um campo de texto (TextField)
    public static void maskDouble(TextField textField) {
        // Define uma mensagem de tooltip para o campo de texto
        Tooltip tooltip = new Tooltip("Digite apenas números!");
        textField.setTooltip(tooltip);
        // Adiciona um ouvinte (listener) para detectar mudanças no texto digitado no campo de texto
        textField.textProperty().addListener((_, _, newValue) -> {
            // Remove todos os caracteres que não são números (dígitos) usando uma expressão regular
            String cleanValue = newValue.replaceAll("\\D", "");
            // Limita o número de caracteres a 14
            if (cleanValue.length() > 14) {
                // Mantém apenas os primeiros 14 caracteres
                cleanValue = cleanValue.substring(0, 14);
            }
            // Cria um StringBuilder para construir o valor formatado
            StringBuilder formatted = new StringBuilder(cleanValue);
            // Adiciona uma vírgula após 2 caracteres da direita para a esquerda (como uma casa decimal)
            if (formatted.length() > 2) {
                formatted.insert(formatted.length() - 2, ',');
            }
            // Adiciona o ponto após 5 caracteres da direita para a esquerda (para separar milhares)
            if (formatted.length() > 6) {
                formatted.insert(formatted.length() - 6, '.');
            }
            // Adiciona o ponto após 8 caracteres da direita para a esquerda (para separar milhões)
            if (formatted.length() > 10) {
                formatted.insert(formatted.length() - 10, '.');
            }
            // Adiciona o ponto após 11 caracteres da direita para a esquerda (para separar bilhões)
            if (formatted.length() > 14) {
                formatted.insert(formatted.length() - 14, '.');
            }
            // Atualiza o campo de texto com a string formatada, utilizando a thread da interface gráfica
            Platform.runLater(() -> {
                // Define o texto formatado no campo de texto
                textField.setText(formatted.toString());
                // Move o cursor para o final do texto
                textField.positionCaret(formatted.length());
            });
        });
    }
}
