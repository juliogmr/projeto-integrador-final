package com.br.projeto_integrador.monitoramento.controller.dto;

import com.br.projeto_integrador.monitoramento.domain.Disciplina;

import lombok.Data;

@Data
public class DisciplinaDTOResponse {
	private Long id;
    private String nome;
    
    DisciplinaDTOResponse(Long id, String nome) {
    	this.id = id;
    	this.nome = nome;
    }
    
    public static DisciplinaDTOResponse fromDisciplina(Disciplina disciplina) {
    	return new DisciplinaDTOResponse(disciplina.getId(), disciplina.getNome());
    }
}
