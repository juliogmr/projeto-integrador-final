package com.br.projeto_integrador.monitoramento.controller.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MonitoriaResponseAluno {

    private LocalDateTime data;
    private String local;

    private MonitorResponseAluno monitor;
}
