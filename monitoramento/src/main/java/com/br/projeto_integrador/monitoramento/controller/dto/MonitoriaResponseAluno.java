package com.br.projeto_integrador.monitoramento.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitoriaResponseAluno {

    private LocalDateTime data;
    private String local;

    private MonitorResponseAluno monitor;
}
