package com.br.projeto_integrador.monitoramento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.projeto_integrador.monitoramento.domain.Monitoria;
@Repository
public interface MonitoriaRepository extends JpaRepository<Monitoria, Long> {
    Optional<Monitoria> findById(Long id);
    
    Optional<List<Monitoria>> findAllByAlunoId(Long id);
}
