package com.br.projeto_integrador.monitoramento.controller.dto;

import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import com.br.projeto_integrador.monitoramento.domain.Instituicao;
import com.br.projeto_integrador.monitoramento.domain.Monitoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class AlunoDTORequest {

    private String nome;
    private String email;
    private String senha;
}