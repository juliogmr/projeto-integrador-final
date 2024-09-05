package com.br.projeto_integrador.monitoramento.controller.dto;

import java.util.List;

import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import com.br.projeto_integrador.monitoramento.domain.Monitor;

import lombok.Data;

@Data
public class MonitorDTOResponse {

	private Long id;
    private String nome;
    private String email;
    private Integer avaliacao;
    private List<Disciplina> disciplinas;

    MonitorDTOResponse(Long id, String nome, String email, Integer avaliacao, List<Disciplina> disciplinas) {
    	this.id = id;
    	this.nome = nome;
    	this.email = email;
    	this.avaliacao = avaliacao;
    	this.disciplinas = disciplinas;
    }
    
    public static MonitorDTOResponse fromMonitor(Monitor monitor) {
    	return new MonitorDTOResponse(monitor.getId(), monitor.getNome(), monitor.getEmail(), monitor.getAvaliacao(), monitor.getDisciplinas());
    }
}
