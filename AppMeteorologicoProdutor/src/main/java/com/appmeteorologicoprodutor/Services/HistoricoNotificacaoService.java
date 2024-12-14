package com.appmeteorologicoprodutor.Services;

import com.appmeteorologicoprodutor.Models.CidadeMonitorada;
import com.appmeteorologicoprodutor.Models.EventoClimatico;
import com.appmeteorologicoprodutor.Models.HistoricoNotificacao;
import com.appmeteorologicoprodutor.Repositories.CidadeMonitoradaRepository;
import com.appmeteorologicoprodutor.Repositories.EventoClimaticoRepository;
import com.appmeteorologicoprodutor.Repositories.HistoricoNotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoNotificacaoService {
    @Autowired
    private HistoricoNotificacaoRepository historicoRepo;

    @Autowired
    private EventoClimaticoRepository eventoRepo;

    @Autowired
    private CidadeMonitoradaRepository cidadeRepo;

    // Listar todas as notificações
    public List<HistoricoNotificacao> listarHistoricoNotificacoes() {
        return historicoRepo.findAll();
    }

    // Excluir notificação por ID
    public void deletarNotificacao(Long id) {
        historicoRepo.deleteById(id);
    }

    // Criar uma notificação de alerta
    public HistoricoNotificacao criarNotificacao(Long eventoId, Long cidadeId) {
        // Buscar o evento e cidade no banco de dados
        EventoClimatico evento = eventoRepo.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        CidadeMonitorada cidade = cidadeRepo.findById(cidadeId)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        // Criar a notificação
        HistoricoNotificacao notificacao = new HistoricoNotificacao();
        notificacao.setEvento(evento);  // Setando o objeto EventoClimatico
        notificacao.setCidade(cidade);  // Setando o objeto CidadeMonitorada
        notificacao.setDataHoraEnvio(LocalDateTime.now());

        return historicoRepo.save(notificacao);
    }
}