package com.appmeteorologicoprodutor;

import com.appmeteorologicoprodutor.Models.Estado;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.appmeteorologicoprodutor.Services.*;
import com.appmeteorologicoprodutor.Models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main extends Application implements CommandLineRunner {

    @Autowired
    private EventoClimaticoService eventoService;

    @Autowired
    private CidadeMonitoradaService cidadeService;

    @Autowired
    private HistoricoNotificacaoService historicoNotificacaoService;

    @Autowired
    private AlertaService alertaService;

    @Autowired
    private RabbitProducer rabbitProducer;

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Exemplo de interface JavaFX (opcional)
        StackPane root = new StackPane();
        root.getChildren().add(new Label("Interface JavaFX Inicializada!"));
        primaryStage.setTitle("Aplicação Meteorológica");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            mostrarMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do teclado

            switch (escolha) {
                case 1:
                    cadastrarEvento();
                    break;
                case 2:
                    listarEventos();
                    break;
                case 3:
                    atualizarEvento();
                    break;
                case 4:
                    deletarEvento();
                    break;
                case 5:
                    cadastrarCidade();
                    break;
                case 6:
                    listarCidades();
                    break;
                case 7:
                    atualizarCidade();
                    break;
                case 8:
                    deletarCidade();
                    break;
                case 9:
                    listarNotificacoes();
                    break;
                case 10:
                    excluirNotificacao();
                    break;
                case 11:
                    criarAlerta();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Cadastrar Evento Climático");
        System.out.println("2. Listar Eventos Climáticos");
        System.out.println("3. Atualizar Evento Climático");
        System.out.println("4. Deletar Evento Climático");
        System.out.println("5. Cadastrar Cidade Monitorada");
        System.out.println("6. Listar Cidades Monitoradas");
        System.out.println("7. Atualizar Cidade Monitorada");
        System.out.println("8. Deletar Cidade Monitorada");
        System.out.println("9. Listar Notificações");
        System.out.println("10. Excluir Notificação");
        System.out.println("11. Criar Alerta");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarEvento() {
        System.out.print("Digite o tipo do evento: ");
        String tipo = scanner.nextLine();
        System.out.print("Digite a descrição do evento: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o dia e a hora do evento (formato: 00/00/0000 00:00): ");
        String dataHoraInput = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        try {
            LocalDateTime dataHora = LocalDateTime.parse(dataHoraInput, formatter);

            EventoClimatico evento = new EventoClimatico(tipo, descricao, dataHora);
            eventoService.salvarEvento(evento);
            System.out.println("Evento cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao converter a data: " + e.getMessage());
        }
    }

    private void listarEventos() {
        List<EventoClimatico> eventos = eventoService.listarTodosEventos();
        System.out.println("\nEventos cadastrados:");
        eventos.forEach(e -> System.out.println(e.getId() + ": " + e.getTipo() + " - " + e.getDescricao()));
    }

    private void atualizarEvento() {
        System.out.print("Digite o ID do evento a ser atualizado: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do teclado

        System.out.print("Digite o novo tipo do evento: ");
        String tipo = scanner.nextLine();
        System.out.print("Digite a nova descrição do evento: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o novo dia e hora do evento (formato: 00/00/0000 00:00): ");
        String dataHoraInput = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraInput, formatter);

        EventoClimatico eventoAtualizado = new EventoClimatico(tipo, descricao, dataHora);
        eventoService.atualizarEvento(id, eventoAtualizado);
        System.out.println("Evento atualizado com sucesso!");
    }

    private void deletarEvento() {
        System.out.print("Digite o ID do evento a ser deletado: ");
        Long id = scanner.nextLong();
        eventoService.deletarEvento(id);
        System.out.println("Evento deletado com sucesso!");
    }

    private void cadastrarCidade() {
        System.out.print("Digite o nome da cidade: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o estado (sigla): ");
        String estadoInput = scanner.nextLine();
        Estado estado = Estado.valueOf(estadoInput.toUpperCase());

        if (!isEstadoValido(estado)) {
            System.out.println("Sigla de estado inválida.");
            return;
        }

        CidadeMonitorada cidade = new CidadeMonitorada(nome, estado);
        cidadeService.salvarCidade(cidade);
        System.out.println("Cidade cadastrada com sucesso!");
    }

    private void listarCidades() {
        List<CidadeMonitorada> cidades = cidadeService.listarTodasCidades();
        System.out.println("\nCidades cadastradas:");
        cidades.forEach(c -> System.out.println(c.getId() + ": " + c.getNome() + " - " + c.getEstado()));
    }

    private void atualizarCidade() {
        System.out.print("Digite o ID da cidade a ser atualizada: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Limpar o buffer do teclado

        System.out.print("Digite o novo nome da cidade: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o novo estado da cidade: ");
        String estadoInput = scanner.nextLine();
        Estado estado = Estado.valueOf(estadoInput.toUpperCase());

        if (!isEstadoValido(estado)) {
            System.out.println("Sigla de estado inválida.");
            return;
        }

        CidadeMonitorada cidadeAtualizada = new CidadeMonitorada(nome, estado);
        cidadeService.atualizarCidade(id, cidadeAtualizada);
        System.out.println("Cidade atualizada com sucesso!");
    }

    private void deletarCidade() {
        System.out.print("Digite o ID da cidade a ser deletada: ");
        Long id = scanner.nextLong();
        cidadeService.deletarCidade(id);
        System.out.println("Cidade deletada com sucesso!");
    }

    private void listarNotificacoes() {
        List<HistoricoNotificacao> notificacoes = historicoNotificacaoService.listarHistoricoNotificacoes();
        System.out.println("\nNotificações cadastradas:");
        notificacoes.forEach(n -> {DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String dataHoraFormatada = n.getDataHoraEnvio().format(formatter);
            System.out.println(n.getId() + ": Evento - " + n.getEvento().getTipo() +
                    " Cidade - " + n.getCidade().getNome() + " " +
                    n.getEvento().getDataHora() +
                    " (Data de envio: " + dataHoraFormatada + ")");
        });
    }

    private void excluirNotificacao() {
        System.out.print("Digite o ID da notificação a ser excluída: ");
        Long id = scanner.nextLong();
        historicoNotificacaoService.deletarNotificacao(id);
        System.out.println("Notificação excluída com sucesso!");
    }

    private void criarAlerta() {
        System.out.print("Digite o ID do evento: ");
        Long eventoId = scanner.nextLong();

        System.out.print("Digite o ID da cidade: ");
        Long cidadeId = scanner.nextLong();

        EventoClimatico evento = eventoService.buscarEventoPorId(eventoId).orElse(null);
        CidadeMonitorada cidade = cidadeService.buscarCidadePorId(cidadeId).orElse(null);

        if (evento != null && cidade != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String horaFormatada = evento.getDataHora().format(formatter);

            HistoricoNotificacao notificacao = historicoNotificacaoService.criarNotificacao(eventoId, cidadeId);
            String horaEnvioFormatada = notificacao.getDataHoraEnvio().format(formatter);

            String mensagem = String.format("[ALERTA] %s %s em %s/%s dia %s (Notificação enviada em %s)",
                    evento.getTipo(),
                    evento.getDescricao(),
                    cidade.getNome(),
                    cidade.getEstado(),
                    horaFormatada,
                    horaEnvioFormatada);

            rabbitProducer.enviarMensagem(mensagem);

            System.out.println("Alerta criado e enviado com sucesso!");
        } else {
            System.out.println("Evento ou cidade não encontrados.");
        }
    }
    public static boolean isEstadoValido(Estado estado) {
        return estado.equals(Estado.AC) || estado.equals(Estado.AL) ||
                estado.equals(Estado.AP) || estado.equals(Estado.AM) ||
                estado.equals(Estado.BA) || estado.equals(Estado.CE) ||
                estado.equals(Estado.DF) || estado.equals(Estado.ES) ||
                estado.equals(Estado.GO) || estado.equals(Estado.MA) ||
                estado.equals(Estado.MT) || estado.equals(Estado.MS) ||
                estado.equals(Estado.MG) || estado.equals(Estado.PA) ||
                estado.equals(Estado.PB) || estado.equals(Estado.PR) ||
                estado.equals(Estado.PE) || estado.equals(Estado.PI) ||
                estado.equals(Estado.RJ) || estado.equals(Estado.RN) ||
                estado.equals(Estado.RS) || estado.equals(Estado.RO) ||
                estado.equals(Estado.RR) || estado.equals(Estado.SC) ||
                estado.equals(Estado.SP) || estado.equals(Estado.SE) ||
                estado.equals(Estado.TO);
    }
}