package com.daniel.conversor.controller;
// Importação das classes necessárias para a aplicação

import com.daniel.conversor.dao.HistoryDao;
import com.daniel.conversor.model.ConversionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryViewController implements Initializable {
    // Declaração do campo de texto para filtro
    @FXML
    private TextField textFieldFilter;
    // Declaração da ListView que exibirá as conversões
    @FXML
    private ListView<ConversionModel> listView;
    // Lista observável que conterá as conversões
    private ObservableList<ConversionModel> conversions;

    // Configura o filtro, adicionando um listener ao campo de texto
    private void setupFilter() {
        // Adiciona um ouvinte para o campo de texto de filtro, que será acionado sempre que o texto mudar
        textFieldFilter.textProperty().addListener((_, _, newValue) -> {
            // Chama a função de filtragem com o novo texto inserido
            filterList(newValue);
        });
    }

    // Filtra a lista de conversões com base no texto de filtro
    private void filterList(String filter) {
        // Cria uma nova lista para armazenar os itens filtrados
        ObservableList<ConversionModel> filteredList = FXCollections.observableArrayList();
        // Itera sobre todas as conversões
        for (ConversionModel conversion : conversions) {
            // Verifica se a descrição contém o texto do filtro (ignorando maiúsculas/minúsculas)
            if (conversion.getDescription().toLowerCase().contains(filter.toLowerCase()) ||
                    conversion.getTitle().toLowerCase().contains(filter.toLowerCase())) {
                // Adiciona à lista filtrada se o filtro corresponder
                filteredList.add(conversion);
            }
        }
        // Atualiza a ListView com a lista filtrada
        listView.setItems(filteredList);
    }

    // Carrega a ListView com os dados das conversões
    private void loadListView() {
        // Chama o 'read()' da classe HistoryDao para obter as conversões salvas
        conversions = FXCollections.observableArrayList(HistoryDao.read());
        // Define como as células da ListView serão exibidas, incluindo um botão de "Excluir"
        listView.setCellFactory(_ -> new ListCell<>() {
            @Override
            protected void updateItem(ConversionModel conversion, boolean empty) {
                super.updateItem(conversion, empty);
                // Verifica se a célula está vazia ou não
                if (empty || conversion == null) {
                    // Se estiver vazia, não exibe texto nem gráficos
                    setText(null);
                    setGraphic(null);
                } else {
                    // Cria o layout (HBox) para exibir o título e a descrição da conversão
                    HBox hBox = new HBox();
                    // Cria um Text para o título
                    Text titleText = new Text(conversion.getTitle() + "\n");
                    // Adiciona estilo para o título
                    titleText.getStyleClass().add("title-label");
                    // Cria o Text para a descrição
                    Text descriptionText = new Text(conversion.getDescription());
                    // Adiciona estilo para a descrição
                    descriptionText.getStyleClass().add("description-label");
                    // Agrupa título e descrição
                    TextFlow textFlow = new TextFlow(titleText, descriptionText);
                    // Define a largura máxima para o TextFlow
                    textFlow.setPrefWidth(500);
                    // Cria o botão de exclusão
                    Button button = new Button("Excluir");
                    // Define a ação que ocorre quando o botão é clicado
                    button.setOnAction(_ -> {
                        // Remove a conversão do banco de dados
                        HistoryDao.delete(conversion.getId());
                        // Remove a conversão da lista de itens
                        conversions.remove(conversion);
                        // Atualiza a ListView para refletir as mudanças
                        listView.setItems(conversions);
                    });
                    // Configura o alinhamento do HBox para que o conteúdo fique alinhado à esquerda
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    // Adiciona o texto e o botão ao HBox
                    hBox.getChildren().addAll(textFlow, button);
                    // Define o espaçamento entre o texto e o botão
                    hBox.setSpacing(10);
                    // Define o layout personalizado (HBox) para a célula
                    setGraphic(hBox);
                }
            }
        });
        // Define os itens da ListView com a lista de conversões
        listView.setItems(conversions);
    }

    // Inicialização que é chamado quando a view é carregada
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Carrega os itens na ListView
        loadListView();
        // Configura o filtro para a ListView
        setupFilter();
    }
}
