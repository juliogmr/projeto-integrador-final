package com.br.projeto_integrador.monitoramento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.projeto_integrador.monitoramento.domain.Monitor;
@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long>, JpaSpecificationExecutor<Monitor> {

    Optional<Monitor> findByEmailAndSenha(String email, String senha);

    Optional<Monitor> findById(Long id);
    Optional<Monitor> findByNome(String nome);
}
