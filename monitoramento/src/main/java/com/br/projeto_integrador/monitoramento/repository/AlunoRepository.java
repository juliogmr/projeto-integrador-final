package com.br.projeto_integrador.monitoramento.repository;

import com.br.projeto_integrador.monitoramento.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByEmailAndSenha(String email, String senha);

    Optional<Aluno> findById(Long id);

    Optional<List<Aluno>> findByNomeContaining(String nome);
}

