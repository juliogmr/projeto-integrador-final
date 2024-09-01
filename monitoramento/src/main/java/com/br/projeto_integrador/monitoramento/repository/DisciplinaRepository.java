package com.br.projeto_integrador.monitoramento.repository;


import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Optional<Disciplina> findByNome(String nome);
}
