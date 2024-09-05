package com.br.projeto_integrador.monitoramento.controller.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MonitoriaResponseFinalMonitor {

    private LocalDateTime data;
    private String local;

    private String aluno;

    private String disciplina;
}
