package com.br.projeto_integrador.monitoramento.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.projeto_integrador.monitoramento.controller.dto.MensagemDTORequest;
import com.br.projeto_integrador.monitoramento.controller.dto.MensagemDTOResponse;
import com.br.projeto_integrador.monitoramento.controller.dto.MonitoriaDTORequest;
import com.br.projeto_integrador.monitoramento.controller.dto.MonitoriaDTOResponse;
import com.br.projeto_integrador.monitoramento.domain.Aluno;
import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import com.br.projeto_integrador.monitoramento.domain.Monitor;
import com.br.projeto_integrador.monitoramento.domain.Monitoria;
import com.br.projeto_integrador.monitoramento.domain.Mensagem;
import com.br.projeto_integrador.monitoramento.repository.AlunoRepository;
import com.br.projeto_integrador.monitoramento.repository.DisciplinaRepository;
import com.br.projeto_integrador.monitoramento.repository.MensagemRepository;
import com.br.projeto_integrador.monitoramento.repository.MonitorRepository;
import com.br.projeto_integrador.monitoramento.repository.MonitoriaRepository;
import com.br.projeto_integrador.monitoramento.util.ResourceNotFoundException;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/mensagens")
@RequiredArgsConstructor
public class MensagemController {

    private final AlunoRepository alunoRepository;

    private final MonitoriaRepository monitoriaRepository;

    private final MonitorRepository monitorRepository;

    private final DisciplinaRepository disciplinaRepository;
    
    private final MensagemRepository mensagemRepository;

    @GetMapping
    public ResponseEntity<List<MensagemDTOResponse>> getAllMensagens() {
        var mensagens = mensagemRepository.findAll();
        
        List<MensagemDTOResponse> mensagensDTOList = mensagens.stream()
        		.map((mensagem) -> MensagemDTOResponse.fromMensagem(mensagem))
        		.collect(Collectors.toList());
        
        return new ResponseEntity<>(mensagensDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MensagemDTOResponse> createMensagem(@RequestBody MensagemDTORequest mensagemRequest) {
    	Long monitoriaId = mensagemRequest.getMonitoriaId();
    	if (monitoriaId == null) {
    		throw new ResourceNotFoundException("monitoriaId é obrigatório");
    	}
    	Monitoria monitoria = monitoriaRepository.findById(monitoriaId)
    			.orElseThrow(() -> new ResourceNotFoundException("Monitoria não encontrada com o ID: " + monitoriaId));
    	
    	Mensagem novaMensagem = new Mensagem();
    	novaMensagem.setData(mensagemRequest.getData());
    	novaMensagem.setTipo(mensagemRequest.getTipo());
    	novaMensagem.setMonitoria(monitoria);
    	
    	novaMensagem.setRespondida(false);
    	if(mensagemRequest.getRespondida() != null) {
    		novaMensagem.setRespondida(mensagemRequest.getRespondida());
    	}
    	
        Mensagem updatedMensagem = mensagemRepository.save(novaMensagem);
        
        return new ResponseEntity<>(MensagemDTOResponse.fromMensagem(updatedMensagem), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTOResponse> updateMensagem(@PathVariable Long id, @RequestBody MensagemDTORequest mensagemRequest) {
        Mensagem mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensagem não encontrada com o ID: " + id));
    	
    	Long monitoriaId = mensagemRequest.getMonitoriaId();
    	Monitoria monitoria = null;
    	if (monitoriaId != null) {
	    	monitoria = monitoriaRepository.findById(monitoriaId)
	    			.orElse(null);
    	}
    	
    	if(mensagemRequest.getData() != null) {
    		mensagem.setData(mensagemRequest.getData());
    	}
    	
    	if(mensagemRequest.getTipo() != null) {
    		mensagem.setTipo(mensagemRequest.getTipo());
    	}
    	
    	if(monitoria != null) {
    		mensagem.setMonitoria(monitoria);
    	}
    	
    	if(mensagemRequest.getRespondida() != null) {
    		mensagem.setRespondida(mensagemRequest.getRespondida());
    	}
        
        Mensagem updatedMensagem = mensagemRepository.save(mensagem);
        return new ResponseEntity<>(MensagemDTOResponse.fromMensagem(updatedMensagem), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensagem(@PathVariable Long id) {
        Mensagem mensagem = mensagemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mensagem não encontrada com o ID: " + id));
        
        mensagemRepository.delete(mensagem);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
