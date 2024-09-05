package com.br.projeto_integrador.monitoramento.controller.dto;

import java.time.LocalDateTime;

import com.br.projeto_integrador.monitoramento.domain.Mensagem;
import com.br.projeto_integrador.monitoramento.domain.Monitoria;

import lombok.Data;

@Data
public class MensagemDTOResponse {

	private Long id;
    private LocalDateTime data;
    private String tipo;

    private MonitoriaDTOResponse monitoria;
    
    private Boolean respondida;
    
    MensagemDTOResponse(Long id, LocalDateTime data, String tipo, Monitoria monitoria, Boolean respondida) {
    	this.id = id;
    	this.data = data;
    	this.tipo = tipo;
    	this.monitoria = MonitoriaDTOResponse.fromMonitoria(monitoria);
    	this.respondida = respondida;
    }
    
    public static MensagemDTOResponse fromMensagem(Mensagem mensagem) {
    	return new MensagemDTOResponse(mensagem.getId(), mensagem.getData(), mensagem.getTipo(), mensagem.getMonitoria(), mensagem.getRespondida());
    }
}