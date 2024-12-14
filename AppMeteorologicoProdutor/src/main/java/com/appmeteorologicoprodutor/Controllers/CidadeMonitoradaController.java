package com.appmeteorologicoprodutor.Controllers;

import com.appmeteorologicoprodutor.Models.CidadeMonitorada;
import com.appmeteorologicoprodutor.Services.CidadeMonitoradaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeMonitoradaController {

    @Autowired
    private CidadeMonitoradaService cidadeService;

    @PostMapping
    public CidadeMonitorada criarCidade(@Valid @RequestBody CidadeMonitorada cidade) {
        return cidadeService.salvarCidade(cidade);
    }

    @GetMapping
    public List<CidadeMonitorada> listarTodasCidades() {
        return cidadeService.listarTodasCidades();
    }

    @GetMapping("/{id}")
    public CidadeMonitorada buscarCidadePorId(@PathVariable Long id) {
        return cidadeService.buscarCidadePorId(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
    }

    @PutMapping("/{id}")
    public CidadeMonitorada atualizarCidade(@PathVariable Long id, @RequestBody CidadeMonitorada cidadeAtualizada) {
        return cidadeService.atualizarCidade(id, cidadeAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deletarCidade(@PathVariable Long id) {
        cidadeService.deletarCidade(id);
    }
}