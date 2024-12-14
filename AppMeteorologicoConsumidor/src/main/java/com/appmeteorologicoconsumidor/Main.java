package com.appmeteorologicoconsumidor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration(proxyBeanMethods = false)
public class Main extends Application {

    private static ConfigurableApplicationContext springContext;

    public static void main(String[] args) {
        // Inicializa o Spring Boot
        springContext = SpringApplication.run(Main.class, args);

        // Inicializa o JavaFX
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Carregar a interface JavaFX (FXML)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/appmeteorologicoconsumidor/TelaConsumidor.fxml"));
        loader.setControllerFactory(springContext::getBean);
        Scene scene = new Scene(loader.load());

        // Configuração da janela principal
        stage.setTitle("Consumidor - Alerta Meteorológico");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        springContext.close();
    }

    // O listener do RabbitMQ que escuta mensagens da fila
    @RabbitListener(queues = "alertaQueue")
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);

        // Passar a mensagem para o controlador JavaFX
        Platform.runLater(() -> ConsumerController.addMessageToQueue(mensagem));
    }
}