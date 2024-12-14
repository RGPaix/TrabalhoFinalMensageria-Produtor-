package com.appmeteorologicoprodutor.Controllers;

import com.appmeteorologicoprodutor.Models.EventoClimatico;
import com.appmeteorologicoprodutor.Services.EventoClimaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoClimaticoController {

    @Autowired
    private EventoClimaticoService eventoService;

    @PostMapping
    public EventoClimatico criarEvento(@RequestBody EventoClimatico evento) {
        return eventoService.salvarEvento(evento);
    }

    @GetMapping
    public List<EventoClimatico> listarTodosEventos() {
        return eventoService.listarTodosEventos();
    }

    @GetMapping("/{id}")
    public EventoClimatico buscarEventoPorId(@PathVariable Long id) {
        return eventoService.buscarEventoPorId(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }

    @PutMapping("/{id}")
    public EventoClimatico atualizarEvento(@PathVariable Long id, @RequestBody EventoClimatico eventoAtualizado) {
        return eventoService.atualizarEvento(id, eventoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarEvento(@PathVariable Long id) {
        eventoService.deletarEvento(id);
    }
}
