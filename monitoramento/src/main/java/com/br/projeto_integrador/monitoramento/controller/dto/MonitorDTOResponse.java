package com.br.projeto_integrador.monitoramento.controller.dto;

import lombok.Data;

@Data
public class MonitorDTOResponse {

    private MonitoriaResponseMonitor monitorias;

    private AlunoResponseMonitor aluno;

    private DisciplinaDTOResponseAluno disciplina;
}
