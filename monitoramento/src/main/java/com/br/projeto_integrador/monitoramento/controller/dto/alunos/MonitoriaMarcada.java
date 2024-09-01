package com.br.projeto_integrador.monitoramento.controller.dto.alunos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MonitoriaMarcada {

    private LocalDateTime data;
    private String local;
    private String disciplina;
    private String monitor;
}
