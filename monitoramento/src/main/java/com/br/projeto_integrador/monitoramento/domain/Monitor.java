package com.br.projeto_integrador.monitoramento.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monitor_id")
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private Integer avaliacao;

    @ManyToMany
    @JoinTable(
            name = "monitor_disciplina",
            joinColumns = @JoinColumn(name = "monitor_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas;

    @OneToMany(mappedBy = "monitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Monitoria> monitorias;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id", referencedColumnName = "instituicao_id")
    private Instituicao instituicao;
}