package com.br.projeto_integrador.monitoramento.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instituicao_id")
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instituicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Monitor> monitores;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instituicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno> alunos;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instituicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Disciplina> disciplinas;
}
