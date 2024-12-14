package com.appmeteorologicoprodutor.Repositories;

import com.appmeteorologicoprodutor.Models.EventoClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoClimaticoRepository extends JpaRepository<EventoClimatico, Long> {

}
