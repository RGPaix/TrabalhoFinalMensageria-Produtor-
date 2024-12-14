package com.appmeteorologicoprodutor.Services;

import com.appmeteorologicoprodutor.Models.EventoClimatico;
import com.appmeteorologicoprodutor.Repositories.EventoClimaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoClimaticoService {
    @Autowired
    private EventoClimaticoRepository eventoRepo;

    public EventoClimatico salvarEvento(EventoClimatico evento) {
        return eventoRepo.save(evento);
    }

    public List<EventoClimatico> listarTodosEventos() {
        return eventoRepo.findAll();
    }

    public Optional<EventoClimatico> buscarEventoPorId(Long id) {
        return eventoRepo.findById(id);
    }

    public EventoClimatico atualizarEvento(Long id, EventoClimatico eventoAtualizado) {
        return eventoRepo.findById(id).map(evento -> {
            evento.setTipo(eventoAtualizado.getTipo());
            evento.setDescricao(eventoAtualizado.getDescricao());
            evento.setDataHora(eventoAtualizado.getDataHora());
            return eventoRepo.save(evento);
        }).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    public void deletarEvento(Long id) {
        eventoRepo.deleteById(id);
    }
}