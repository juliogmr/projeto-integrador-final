package com.br.projeto_integrador.monitoramento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.projeto_integrador.monitoramento.domain.Mensagem;
@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    Optional<Mensagem> findById(Long id);
    List<Mensagem> findAllByMonitoriaId(Long id);
    
    @Query("SELECT m FROM Mensagem m JOIN m.monitoria mm JOIN mm.aluno mma WHERE mma.id = :id")
    List<Mensagem> findAllByAlunoId(Long id);
    
    @Query("SELECT m FROM Mensagem m JOIN m.monitoria mm JOIN mm.monitor mmm WHERE mmm.id = :id")
    List<Mensagem> findAllByMonitorId(Long id);
}
