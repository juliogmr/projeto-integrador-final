package com.br.projeto_integrador.monitoramento.controller.dto.alunos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MonitoriaMarcada {

    private LocalDateTime data;
    private String local;
    private String disciplina;
    private String monitor;
}
