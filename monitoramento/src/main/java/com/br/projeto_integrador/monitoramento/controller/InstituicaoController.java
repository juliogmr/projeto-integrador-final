package com.br.projeto_integrador.monitoramento.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.projeto_integrador.monitoramento.controller.dto.InstituicaoDTOResponse;
import com.br.projeto_integrador.monitoramento.controller.dto.LoginDTO;
import com.br.projeto_integrador.monitoramento.repository.InstituicaoRepository;
import com.br.projeto_integrador.monitoramento.util.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/instituicoes")
@RequiredArgsConstructor
public class InstituicaoController {

    private final InstituicaoRepository instituicaoRepository;

    // Método para "logar"
    @PostMapping("/logar")
    public ResponseEntity<InstituicaoDTOResponse> logar(@RequestBody LoginDTO loginDTO) {
        var instituicao = instituicaoRepository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());
        if(!instituicao.isPresent()){
        	throw new ResourceNotFoundException("Usuário ou senha incorretos");
        }
        
        InstituicaoDTOResponse instituicaoResponse = InstituicaoDTOResponse.fromInstituicao(instituicao.get());

        return new ResponseEntity<>(instituicaoResponse, HttpStatus.OK);
    }
}