package com.br.projeto_integrador.monitoramento.controller.dto;

import org.antlr.v4.runtime.misc.NotNull;

import lombok.Data;

@Data
public class LoginDTO {
    @NotNull
    private String email;
    @NotNull
    private String senha;
}
