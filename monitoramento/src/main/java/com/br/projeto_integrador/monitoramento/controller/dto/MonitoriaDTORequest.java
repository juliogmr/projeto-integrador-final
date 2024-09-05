package com.br.projeto_integrador.monitoramento.controller.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MonitoriaDTORequest {

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data;
    private String local;
    private String status;
    
    private Long monitorId;
    private Long alunoId;
    private Long disciplinaId;
}