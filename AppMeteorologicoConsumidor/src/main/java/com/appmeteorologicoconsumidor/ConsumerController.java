package com.appmeteorologicoconsumidor;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Controller
public class ConsumerController {

    @FXML
    private TextArea alertTextArea;

    // Fila para armazenar mensagens recebidas
    private static final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    //@FXML
    //public void initialize() {
    //    alertTextArea.setEditable(false);
    //}

    public ConsumerController() {
        // Thread para processar mensagens da fila
        Thread messageProcessor = new Thread(() -> {
            while (true) {
                try {
                    // Pega a próxima mensagem da fila
                    String message = messageQueue.take();

                    // Atualiza a interface no thread da UI
                    Platform.runLater(() -> {
                        if (message != null && !message.isEmpty()) {
                            alertTextArea.appendText(message + "\n");
                        }
                    });
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        messageProcessor.setDaemon(true);
        messageProcessor.start();
    }
    // Adiciona mensagem à fila
    public static void addMessageToQueue(String message) {
        if (message != null && !message.isEmpty()) {
            messageQueue.offer(message); // Adiciona mensagem à fila
        } else {
            System.out.println("Mensagem inválida não foi adicionada à fila.");
        }
    }
    // Método para tratar o fechamento da janela
    @FXML
    public void handleClose() {
        Stage stage = (Stage) alertTextArea.getScene().getWindow();
        stage.close();
    }
}