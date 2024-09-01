package com.br.projeto_integrador.monitoramento.repository;

import com.br.projeto_integrador.monitoramento.domain.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {

    Optional<Monitor> findByEmailAndSenha(String email, String senha);

    Optional<Monitor> findById(Long id);
    Optional<Monitor> findByNome(String nome);
}
