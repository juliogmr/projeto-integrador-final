package com.br.projeto_integrador.monitoramento.controller.dto.monitores;

import lombok.Data;

import java.util.List;

@Data
public class AlunoMonitoria {

    private String nome;
    private Integer avaliacao;
    private List<DisciplinasAlunos> disciplinas;
}
