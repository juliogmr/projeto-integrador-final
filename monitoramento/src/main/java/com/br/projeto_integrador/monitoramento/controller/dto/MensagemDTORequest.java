package com.br.projeto_integrador.monitoramento.controller.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MensagemDTORequest {

    private LocalDateTime data;
    private String tipo;
 
    private Long monitoriaId;
    
    private Boolean respondida;
}