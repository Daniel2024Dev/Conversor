package com.daniel.conversor;
// Importação das classes necessárias para a aplicação

import com.daniel.conversor.dao.Dao;
import com.daniel.conversor.util.Alerts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    // Declara uma variável estática do tipo Scene para armazenar a cena principal da aplicação.
    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Cria um novo carregador de FXML, apontando para o arquivo viewMain.fxml localizado no caminho especificado.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/daniel/conversor/view/multi-view.fxml"));
            // Carrega a estrutura da interface a partir do arquivo FXML e armazena o componente raiz em "scrollPane".
            ScrollPane scrollPane = loader.load();
            // Ajusta o ScrollPane para ocupar toda a largura e altura disponíveis na tela
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            // Cria uma nova cena com o componente raiz, que contém todos os elementos definidos no arquivo FXML.
            mainScene = new Scene(scrollPane);
            // Associa a cena criada ao palco principal (janela) da aplicação.
            primaryStage.setScene(mainScene);
            // Define o título da janela principal como "Converter".
            primaryStage.setTitle("Converter");
            // Largura da janela
            primaryStage.setWidth(765);
            // Altura da janela
            primaryStage.setHeight(600);
            // Exibe o palco principal, tornando a janela visível ao usuário.
            primaryStage.show();
        } catch (IOException e) {
            // Mostra a mensagem de erro
            Alerts.showAlert("Erro", "", "Erro ao carregar sistema!", Alert.AlertType.ERROR, 5);
        }
    }

    // Retornar a cena principal da aplicação.
    public static Scene getMainScene() {
        return mainScene;
    }

    // Principal da aplicação
    public static void main(String[] args) {
        // Cria o diretório e as tabelas necessárias no banco de dados ao iniciar a aplicação.
        Dao.createDirectory();
        Dao.createTables();
        // Lança a aplicação JavaFX
        launch();
    }
}
