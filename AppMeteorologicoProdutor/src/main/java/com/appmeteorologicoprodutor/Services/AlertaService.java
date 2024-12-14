package com.appmeteorologicoprodutor.Services;
import com.appmeteorologicoprodutor.RabbitProducer;
import com.appmeteorologicoprodutor.Models.HistoricoNotificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

    @Autowired
    private HistoricoNotificacaoService historicoNotificacaoService;

    @Autowired
    private RabbitProducer rabbitProducer;

    // Criar notificação e enviar alerta
    public void gerarAlerta(Long eventoId, Long cidadeId) {
        // 1. Criar a notificação no banco de dados
        HistoricoNotificacao notificacao = historicoNotificacaoService.criarNotificacao(eventoId, cidadeId);

        // 2. Enviar a mensagem via RabbitMQ
        String mensagem = "Alerta: Evento ID " + eventoId + " para a cidade ID " + cidadeId;
        rabbitProducer.enviarMensagem(mensagem);
    }
}