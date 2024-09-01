package com.br.projeto_integrador.monitoramento.controller.dto.alunos;

import lombok.Data;

import java.util.List;

@Data
public class MonitorResponse {

    private String nome;

    private List<DisciplinasResponse> disciplina;

    private Integer avaliacao;
}
