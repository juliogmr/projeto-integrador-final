package com.br.projeto_integrador.monitoramento.controller;


import com.br.projeto_integrador.monitoramento.controller.dto.LoginDTO;
import com.br.projeto_integrador.monitoramento.domain.Instituicao;
import com.br.projeto_integrador.monitoramento.repository.InstituicaoRepository;
import com.br.projeto_integrador.monitoramento.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

    private final InstituicaoRepository instituicaoRepository;

    // Método para "logar"
    @GetMapping("/logar")
    public ResponseEntity<Long> logar(@RequestBody LoginDTO loginDTO) {
        var instituicao = instituicaoRepository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());
        if(instituicao == null){
            throw new ResourceNotFoundException("Usuário não cadastrado no cinema");
        } else {
            return new ResponseEntity<>(instituicao.get().getId(), HttpStatus.OK);
        }
    }
}
