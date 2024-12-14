package com.appmeteorologicoprodutor.Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "historico_notificacoes")
public class HistoricoNotificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evento_id", referencedColumnName = "id")
    private EventoClimatico evento;

    @ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    private CidadeMonitorada cidade;

    @Column(name = "data_hora_envio", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime dataHoraEnvio;

    public HistoricoNotificacao() {
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventoClimatico getEvento() {
        return evento;
    }

    public void setEvento(EventoClimatico evento) {
        this.evento = evento;
    }

    public CidadeMonitorada getCidade() {
        return cidade;
    }

    public void setCidade(CidadeMonitorada cidade) {
        this.cidade = cidade;
    }

    public LocalDateTime getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    public void setDataHoraEnvio(LocalDateTime dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }
}