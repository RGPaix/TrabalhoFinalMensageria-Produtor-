package com.appmeteorologicoprodutor.Repositories;

import com.appmeteorologicoprodutor.Models.HistoricoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoNotificacaoRepository extends JpaRepository<HistoricoNotificacao, Long> {
}
