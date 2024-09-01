package com.br.projeto_integrador.monitoramento.controller.dto;

import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import lombok.Data;

import java.util.List;

@Data
public class AlunoDTOResponse {

    private String nome;
    private String email;
    private List<DisciplinaDTOResponseAluno> disciplinas;
    private List<MonitoriaResponseAluno> mentoria;
}