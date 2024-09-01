package com.br.projeto_integrador.monitoramento.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private Integer avaliacao;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id", referencedColumnName = "instituicao_id")
    private Instituicao instituicao;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Monitoria> monitorias;
}