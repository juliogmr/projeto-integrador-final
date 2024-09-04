package com.br.projeto_integrador.monitoramento.controller.dto.alunos;

import java.util.List;

import lombok.Data;

@Data
public class MonitorResponse {

    private String nome;

    private List<DisciplinasResponse> disciplina;

    private Integer avaliacao;
}
