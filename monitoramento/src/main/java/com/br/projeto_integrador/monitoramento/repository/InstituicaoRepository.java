package com.br.projeto_integrador.monitoramento.repository;

import com.br.projeto_integrador.monitoramento.domain.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

    Optional<Instituicao> findByEmailAndSenha(String email, String senha);
}
