package com.daniel.conversor.controller;
// Importação das classes necessárias para a aplicação

import com.daniel.conversor.MainApplication;
import com.daniel.conversor.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MultiViewController implements Initializable {
    // Declaração do VBox que servirá como contêiner para as views carregadas
    @FXML
    private VBox vBoxContainer;

    // Função para carregar a view inicial
    private void container() {
        try {
            // Carrega o arquivo FXML da view inicial
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/daniel/conversor/view/home-view.fxml"));
            // Carrega o conteúdo da view em um VBox
            VBox subView = loader.load();
            // Adiciona a subview ao contêiner principal (vBoxContainer)
            vBoxContainer.getChildren().add(subView); // Adiciona a subview ao contêiner
        } catch (IOException e) {
            Alerts.showAlert("Erro", "", "Erro ao carregar sistema!", Alert.AlertType.ERROR, 5);
        }
    }

    // Ação para carregar a view Home
    @FXML
    public void onMenuItemHomeViewAction() {
        loadView("/com/daniel/conversor/view/home-view.fxml");
    }

    // Ação para carregar a view History
    @FXML
    public void onMenuItemHistoryViewAction() {
        loadView("/com/daniel/conversor/view/history-view.fxml");

    }

    // Função chamada ao inicializar a classe
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Carrega a view inicial
        container();
    }

    // Função para carregar uma nova view dentro da VBox principal
    private synchronized void loadView(String absoluteName) {
        try {
            // Cria um carregador de recursos FXML com o caminho absoluto fornecido.
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            // Carrega o conteúdo do FXML em um VBox
            VBox newVBox = loader.load();
            // Obtém a cena principal da aplicação.
            Scene mainScene = MainApplication.getMainScene();
            // Acessa a VBox principal da cena, que é o conteúdo do ScrollPane.
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
            // Obtém o primeiro elemento da VBox principal, que é o menu principal da aplicação.
            Node mainMenu = mainVBox.getChildren().getFirst();
            // Limpa todos os elementos da VBox principal.
            mainVBox.getChildren().clear();
            // Adiciona novamente o menu principal à VBox.
            mainVBox.getChildren().add(mainMenu);
            // Adiciona todos os filhos da nova VBox (carregada do FXML) à VBox principal.
            mainVBox.getChildren().addAll(newVBox.getChildren());
        } catch (IOException e) {
            // Mostra a mensagem de erro
            Alerts.showAlert("Erro", "", "Erro ao carregar sistema!", Alert.AlertType.ERROR, 5);
        }
    }
}