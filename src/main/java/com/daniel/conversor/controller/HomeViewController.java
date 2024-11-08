package com.daniel.conversor.controller;
// Importação das classes necessárias para a aplicação

import com.daniel.conversor.dao.HistoryDao;
import com.daniel.conversor.model.ConversionModel;
import com.daniel.conversor.util.Alerts;
import com.daniel.conversor.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeViewController implements Initializable {
    // Declaração dos campos de entrada (TextField) para título e valor
    @FXML
    private TextField textFieldTitle, textFieldValue;
    // Declaração dos CheckBoxes para as opções de conversão
    @FXML
    private CheckBox checkBoxNm_Kgf, checkBoxKgf_Nm;
    // Declaração do VBox que conterá os cards gerados pelas conversões
    @FXML
    private VBox vBoxCard;
    // Lista de conversões, usada para armazenar as conversões realizadas
    ArrayList<ConversionModel> conversion = new ArrayList<>();

    // Função que é chamada quando o botão de conversão é pressionado
    @FXML
    private void onConvertAction() {
        // Recupera os valores dos campos de título e valor, e formata o valor
        String title = textFieldTitle.getText(), formatted = textFieldValue.getText().replace(".", "").replace(",", ".");
        // Limpa a lista de conversões antes de adicionar novas
        conversion.clear();
        // Verifica se o título e o valor estão preenchidos
        if (!title.isEmpty() && !formatted.isEmpty()) {
            // Remove qualquer estilo de erro nos campos
            textFieldTitle.setStyle(null);
            textFieldValue.setStyle(null);
            // Verifica se nenhuma das opções de conversão foi selecionada
            if (!checkBoxNm_Kgf.isSelected() && !checkBoxKgf_Nm.isSelected()) {
                Alerts.showAlert("Erro", "", "Selecione pelo menos uma opção de conversão!", AlertType.ERROR, 5);
            }
            // Verifica se a conversão de Newton-metro para quilograma-força foi selecionada
            if (checkBoxNm_Kgf.isSelected()) {
                // Converte o valor para double e realiza a conversão
                double newtonMetros = Double.parseDouble(formatted);
                double kgf = newtonMetros / 9.80665;
                // Formata o valor da conversão para duas casas decimais
                String kgfFormatado = String.format("%.2f", kgf);
                // Adiciona a conversão à lista de conversões
                conversion.add(new ConversionModel(title, newtonMetros + " newton-metros é equivalente a " + kgfFormatado + " quilogramas-força."));
            }
            // Verifica se a conversão de quilograma-força para Newton-metro foi selecionada
            if (checkBoxKgf_Nm.isSelected()) {
                // Converte o valor para double e realiza a conversão
                double quilogramasForca = Double.parseDouble(formatted);
                double nm = quilogramasForca * 9.80665;
                // Formata o valor da conversão
                String nmFormatado = String.format("%.2f", nm);
                // Adiciona a conversão à lista de conversões
                conversion.add(new ConversionModel(title, quilogramasForca + " quilogramas-força é equivalente a " + nmFormatado + " newton-metros."));
            }
        } else {
            // Se algum campo obrigatório estiver vazio, aplica borda vermelha e exibe alerta
            if (title.isEmpty()) {
                textFieldTitle.setStyle("-fx-border-color: red;");
            }
            if (formatted.isEmpty()) {
                textFieldValue.setStyle("-fx-border-color: red;");
            }
            // Mostra a mensagem de erro
            Alerts.showAlert("Erro", "", "Campo obrigatório não preenchido!", AlertType.ERROR, 5);
        }
        // Chama a função para criar os cards com as conversões realizadas
        createCards();
    }

    // Função que cria os cards para exibir as conversões
    private void createCards() {
        // Limpa os campos de texto
        textFieldTitle.clear();
        textFieldValue.clear();
        // Desmarca os checkbox
        checkBoxNm_Kgf.setSelected(false);
        checkBoxKgf_Nm.setSelected(false);
        // Define o espaçamento entre os cards
        vBoxCard.setSpacing(10); // Define o espaçamento entre os cards
        // Itera sobre cada conversão realizada
        for (ConversionModel conversionModel : conversion) {
            // Cria um novo card (VBox)
            VBox card = new VBox();
            // Adiciona uma classe de estilo customizado
            card.getStyleClass().add("custom-card");
            // Cria os rótulos para exibir o título do card
            Label titleLabel = new Label(conversionModel.getTitle());
            // Adiciona estilo para o título
            titleLabel.getStyleClass().add("title-label");
            // Cria os rótulos para exibir a descrição do card
            Label descriptionLabel = new Label(conversionModel.getDescription());
            // Adiciona estilo para a descrição
            descriptionLabel.getStyleClass().add("description-label");
            // Adiciona os rótulos ao card
            card.getChildren().addAll(titleLabel, descriptionLabel);
            // Adiciona o card ao VBox que contém os cards
            vBoxCard.getChildren().add(card);
            // Salva a conversão no banco de dados (HistoryDao)
            HistoryDao.create(conversionModel);
        }
    }

    // Função chamada quando a tela é inicializada
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Aplica uma máscara para garantir que o valor seja um número decimal
        Constraints.maskDouble(textFieldValue);
    }
}
