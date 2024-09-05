package com.br.projeto_integrador.monitoramento.controller.dto;

import java.util.List;

import com.br.projeto_integrador.monitoramento.domain.Aluno;
import com.br.projeto_integrador.monitoramento.domain.Disciplina;

import lombok.Data;

@Data
public class AlunoDTOResponse {

	private Long id;
    private String nome;
    private String email;
    private List<Disciplina> disciplinas;
    
    AlunoDTOResponse(Long id, String nome, String email, List<Disciplina> disciplinas) {
    	this.id = id;
    	this.nome = nome;
    	this.email = email;
    	this.disciplinas = disciplinas;
    }
    
    public static AlunoDTOResponse fromAluno(Aluno aluno) {
    	return new AlunoDTOResponse(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getDisciplinas());
    }
}