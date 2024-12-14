package com.appmeteorologicoprodutor.Services;

import com.appmeteorologicoprodutor.Models.CidadeMonitorada;
import com.appmeteorologicoprodutor.Repositories.CidadeMonitoradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeMonitoradaService {
    @Autowired
    private CidadeMonitoradaRepository cidadeRepo;

    public CidadeMonitorada salvarCidade(CidadeMonitorada cidade) {
        return cidadeRepo.save(cidade);
    }

    public List<CidadeMonitorada> listarTodasCidades() {
        return cidadeRepo.findAll();
    }

    public Optional<CidadeMonitorada> buscarCidadePorId(Long id) {
        return cidadeRepo.findById(id);
    }

    public CidadeMonitorada atualizarCidade(Long id, CidadeMonitorada cidadeAtualizada) {
        return cidadeRepo.findById(id).map(cidade -> {
            cidade.setNome(cidadeAtualizada.getNome());
            cidade.setEstado(cidadeAtualizada.getEstado());
            return cidadeRepo.save(cidade);
        }).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
    }

    public void deletarCidade(Long id) {
        cidadeRepo.deleteById(id);
    }
}