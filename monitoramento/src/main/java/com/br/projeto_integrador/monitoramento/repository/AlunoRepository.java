package com.br.projeto_integrador.monitoramento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.br.projeto_integrador.monitoramento.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno> {

    Optional<Aluno> findByEmailAndSenha(String email, String senha);

    Optional<Aluno> findById(Long id);

    Optional<List<Aluno>> findByNomeContaining(String nome);
}

