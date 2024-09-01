package com.br.projeto_integrador.monitoramento.controller.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class LoginDTO {
    @NotNull
    private String email;
    @NotNull
    private String senha;
}
