package com.br.projeto_integrador.monitoramento.controller.dto.monitores;

import java.util.List;

import lombok.Data;

@Data
public class AlunoMonitoria {

    private String nome;
    private Integer avaliacao;
    private List<DisciplinasAlunos> disciplinas;
}
