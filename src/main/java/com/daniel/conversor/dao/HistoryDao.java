package com.daniel.conversor.dao;
// Importação das classes necessárias para trabalhar com SQL e manipulação de dados

import com.daniel.conversor.model.ConversionModel;
import com.daniel.conversor.util.Alerts;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao extends Dao {
    // Inseri uma nova conversão no banco de dados
    public static void create(ConversionModel conversionModel) {
        // SQL para inserir um novo registro na tabela History
        String sql = "INSERT INTO History (Title, Description) VALUES(?, ?)";
        // Preparar o comando SQL e executar a inserção
        try (PreparedStatement pstmt = connect().prepareStatement(sql)) {
            // Define o título da conversão
            pstmt.setString(1, conversionModel.getTitle());
            // Define a descrição da conversão
            pstmt.setString(2, conversionModel.getDescription());
            // Executa a atualização no banco
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Mostra a mensagem de erro
            Alerts.showAlert("Erro", "", "Falha no banco de dados!", Alert.AlertType.ERROR, 5);
        }
    }

    // Ler todas as conversões do banco de dados
    public static List<ConversionModel> read() {
        // Cria uma lista para armazenar as conversões
        List<ConversionModel> conversion = new ArrayList<>();
        // SQL para selecionar todas as conversões da tabela History
        String sql = "SELECT * FROM History";
        // Cria um Statement para executar a consulta no banco de dados
        try (Statement stmt = connect().createStatement();
             // Executa a consulta e obtém o ResultSet
             ResultSet rs = stmt.executeQuery(sql)) {
            // Itera sobre o resultado da consulta
            while (rs.next()) {
                // Cria um objeto ConversionModel com os dados do banco
                ConversionModel conversionModel = new ConversionModel(
                        rs.getInt("Id"),
                        rs.getString("Title"),
                        rs.getString("Description")
                );
                // Adiciona a conversão à lista
                conversion.add(conversionModel);
            }

        } catch (SQLException e) {
            // Mostra a mensagem de erro
            Alerts.showAlert("Erro", "", "Falha no banco de dados!", Alert.AlertType.ERROR, 5);
        }
        // Retorna a lista de conversões
        return conversion;
    }

    // Exclui uma conversão do banco de dados, dada a sua ID
    public static void delete(int id) {
        // SQL para excluir a conversão com o ID especificado
        String sql = "DELETE FROM History WHERE Id = ?";
        // Prepara e executar o comando de exclusão
        try (PreparedStatement pstmt = connect().prepareStatement(sql)) {
            // Define a ID da conversão a ser excluída
            pstmt.setInt(1, id);
            // Executa a exclusão no banco
            pstmt.executeUpdate();
        } catch (SQLException e) {
            // Mostra a mensagem de erro
            Alerts.showAlert("Erro", "", "Falha no banco de dados!", Alert.AlertType.ERROR, 5);
        }
    }
}
