package com.daniel.conversor.util;
// Importação das classes necessárias para a aplicação

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

// Classe utilitária para exibir alertas na interface
public class Alerts {
    // Mostrar um alerta personalizado
    public static void showAlert(String title, String header, String content, AlertType type, int time) {
        // Cria uma instância de um alerta do tipo informado (por exemplo, erro, informação, etc.)
        Alert alert = new Alert(type);
        // Define o título do alerta (aparece na parte superior da janela do alerta)
        alert.setTitle(title);
        // Define o cabeçalho do alerta (texto logo abaixo do título)
        alert.setHeaderText(header);
        // Define o conteúdo principal do alerta (texto que aparece dentro da janela do alerta)
        alert.setContentText(content);
        // Exibe o alerta na interface gráfica
        alert.show();
        // Cria uma transição de pausa que espera por um tempo especificado antes de realizar uma ação
        PauseTransition pause = new PauseTransition(Duration.seconds(time));
        // Define a ação a ser executada após o tempo de pausa (neste caso, fechar o alerta)
        pause.setOnFinished(_ -> {
            alert.close(); // Fecha o alerta automaticamente após o tempo definido
        });
        // Inicia a pausa, fazendo com que o tempo de espera seja contado
        pause.play();
    }
}
