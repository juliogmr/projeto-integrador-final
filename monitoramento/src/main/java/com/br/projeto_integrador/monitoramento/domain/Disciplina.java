package com.br.projeto_integrador.monitoramento.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disciplina_id")
    private Long id;

    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "disciplinas")
    private List<Monitor> monitores;

    @JsonIgnore
    @ManyToMany(mappedBy = "disciplinas")
    private List<Aluno> alunos;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id", referencedColumnName = "instituicao_id")
    private Instituicao instituicao;
}
