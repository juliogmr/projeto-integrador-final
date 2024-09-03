package com.br.projeto_integrador.monitoramento.controller.dto;

import com.br.projeto_integrador.monitoramento.domain.Instituicao;

import lombok.Data;

@Data
public class InstituicaoDTOResponse {

	private Long id;
    private String nome;
    private String email;
    
    InstituicaoDTOResponse(Long id, String nome, String email) {
    	this.id = id;
    	this.nome = nome;
    	this.email = email;
    }
    
    public static InstituicaoDTOResponse fromInstituicao(Instituicao instituicao) {
    	return new InstituicaoDTOResponse(instituicao.getId(), instituicao.getNome(), instituicao.getEmail());
    }
}