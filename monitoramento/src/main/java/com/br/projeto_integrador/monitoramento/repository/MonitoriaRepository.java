package com.br.projeto_integrador.monitoramento.repository;

import com.br.projeto_integrador.monitoramento.domain.Monitoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MonitoriaRepository extends JpaRepository<Monitoria, Long> {
}
