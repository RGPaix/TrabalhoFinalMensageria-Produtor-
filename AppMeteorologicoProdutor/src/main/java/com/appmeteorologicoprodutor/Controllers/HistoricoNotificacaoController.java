package com.appmeteorologicoprodutor.Controllers;

import com.appmeteorologicoprodutor.Models.HistoricoNotificacao;
import com.appmeteorologicoprodutor.Services.HistoricoNotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoNotificacaoController {

    @Autowired
    private HistoricoNotificacaoService historicoService;

    @GetMapping
    public List<HistoricoNotificacao> listarHistoricoNotificacoes() {
        return historicoService.listarHistoricoNotificacoes();
    }

    @DeleteMapping("/{id}")
    public void deletarNotificacao(@PathVariable Long id) {
        historicoService.deletarNotificacao(id);
    }
}