package com.appmeteorologicoprodutor.Repositories;

import com.appmeteorologicoprodutor.Models.CidadeMonitorada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeMonitoradaRepository extends JpaRepository<CidadeMonitorada, Long> {
}
