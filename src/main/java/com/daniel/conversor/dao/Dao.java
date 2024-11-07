package com.daniel.conversor.dao;
// Importação das classes necessárias para trabalhar com arquivos, conexões SQL e exceções

import com.daniel.conversor.util.Alerts;
import javafx.scene.control.Alert;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
    // Define o caminho do diretório onde o banco de dados será armazenado
    private static final String directoryPath = "/home/daniel/IdeaProjects/Conversor/db";

    // Criar o diretório onde o banco de dados será armazenado, caso não exista
    public static void createDirectory() {
        // Cria uma instância de File representando o diretório no caminho especificado
        File directory = new File(directoryPath);
        // Verifica se o diretório já existe
        if (!directory.exists()) {
            // Se o diretório não existir, tenta criá-lo
            if (directory.mkdirs()) {
                System.out.println("Diretório criado com sucesso!");
            } else {
                // Mostra a mensagem de erro
                Alerts.showAlert("Erro", "", "Falha ao criar o diretório!", Alert.AlertType.ERROR, 5);
            }
        }
    }

    // URL de conexão ao banco de dados SQLite, concatenando o caminho do diretório com o nome do arquivo do banco
    private static final String URL = "jdbc:sqlite:" + directoryPath + "/conversor.db";

    // Criar uma conexão com o banco de dados SQLite
    public static Connection connect() {
        Connection conn = null;
        try {
            // Tenta conectar ao banco de dados usando o URL configurado
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            // Mostra a mensagem de erro
            Alerts.showAlert("Erro", "", "Erro ao conectar ao banco de dados!", Alert.AlertType.ERROR, 5);
        }
        // Retorna a conexão (pode ser null em caso de erro)
        return conn;
    }

    // Criar as tabelas no banco de dados
    public static void createTables() {
        // Garanti que o diretório de banco de dados existe
        createDirectory();
        // SQL para criar a tabela History, caso não exista
        String historyTableSQL = "CREATE TABLE IF NOT EXISTS History ("
                + "Id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Title TEXT NOT NULL,"
                + "Description TEXT NOT NULL"
                + ");";
        // SQL para criar a tabela ConversionTable, caso não exista
        String conversionTableSQL = "CREATE TABLE IF NOT EXISTS ConversionTable ("
                + "Id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Title TEXT NOT NULL,"
                + "Description TEXT NOT NULL"
                + ");";
        // Tenta conectar ao banco de dados e criar as tabelas
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            // Executa os comandos SQL para criar as tabelas
            stmt.execute(historyTableSQL);
            stmt.execute(conversionTableSQL);
            System.out.println("Tabelas criadas com sucesso!");
        } catch (SQLException e) {
            // Mostra a mensagem de erro
            Alerts.showAlert("Erro", "", "Erro ao criar tabelas do banco de dados!", Alert.AlertType.ERROR, 5);
        }
    }
}
