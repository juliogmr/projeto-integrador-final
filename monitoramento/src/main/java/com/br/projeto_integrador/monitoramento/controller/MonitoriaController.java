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

import com.br.projeto_integrador.monitoramento.controller.dto.MensagemDTOResponse;
import com.br.projeto_integrador.monitoramento.controller.dto.MonitoriaDTORequest;
import com.br.projeto_integrador.monitoramento.controller.dto.MonitoriaDTOResponse;
import com.br.projeto_integrador.monitoramento.domain.Aluno;
import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import com.br.projeto_integrador.monitoramento.domain.Mensagem;
import com.br.projeto_integrador.monitoramento.domain.Monitor;
import com.br.projeto_integrador.monitoramento.domain.Monitoria;
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
@RequestMapping("/monitorias")
@RequiredArgsConstructor
public class MonitoriaController {

    private final AlunoRepository alunoRepository;

    private final MonitoriaRepository monitoriaRepository;

    private final MonitorRepository monitorRepository;

    private final DisciplinaRepository disciplinaRepository;
    
    private final MensagemRepository mensagemRepository;

    @GetMapping
    public ResponseEntity<List<MonitoriaDTOResponse>> getAllMonitorias() {
        var monitorias = monitoriaRepository.findAll();
        
        List<MonitoriaDTOResponse> monitoriasDTOList = monitorias.stream()
        		.map((monitoria) -> MonitoriaDTOResponse.fromMonitoria(monitoria))
        		.collect(Collectors.toList());
        
        return new ResponseEntity<>(monitoriasDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MonitoriaDTOResponse> createMonitoria(@RequestBody MonitoriaDTORequest monitoriaRequest) {
    	Long monitorId = monitoriaRequest.getMonitorId();
    	if (monitorId == null) {
    		throw new ResourceNotFoundException("monitorId é obrigatório");
    	}
    	Monitor monitor = monitorRepository.findById(monitorId)
    			.orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + monitorId));
    	
    	Long alunoId = monitoriaRequest.getAlunoId();
    	if (alunoId == null) {
    		throw new ResourceNotFoundException("alunoId é obrigatório");
    	}
    	Aluno aluno = alunoRepository.findById(alunoId)
    			.orElseThrow((() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + alunoId)));
    	
    	Long disciplinaId = monitoriaRequest.getDisciplinaId();
    	if (disciplinaId == null) {
    		throw new ResourceNotFoundException("disciplinaId é obrigatório");
    	}
    	Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
    			.orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + disciplinaId));
    	
    	Monitoria novaMonitoria = new Monitoria();
    	novaMonitoria.setData(monitoriaRequest.getData());
    	novaMonitoria.setLocal(monitoriaRequest.getLocal());
    	novaMonitoria.setStatus("pendente");
    	novaMonitoria.setMonitor(monitor);
    	novaMonitoria.setAluno(aluno);
    	novaMonitoria.setDisciplina(disciplina);
    	
        Monitoria updatedMonitoria = monitoriaRepository.save(novaMonitoria);
        
        // Enviar mensagem de solicitacao
        Mensagem mensagemSolicitacao = new Mensagem();
        mensagemSolicitacao.setData(LocalDateTime.now());
        mensagemSolicitacao.setTipo("Solicitação de Monitoria");
        mensagemSolicitacao.setMonitoria(updatedMonitoria);
        mensagemSolicitacao.setRespondida(false);

        mensagemRepository.save(mensagemSolicitacao);
        
        return new ResponseEntity<>(MonitoriaDTOResponse.fromMonitoria(updatedMonitoria), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<MonitoriaDTOResponse> updateMonitoria(@PathVariable Long id, @RequestBody MonitoriaDTORequest monitoriaRequest) {
        Monitoria monitoria = monitoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitoria não encontrada com o ID: " + id));

        Long monitorId = monitoriaRequest.getMonitorId();
        Monitor monitor = null;
        if(monitorId != null) {
	    	monitor = monitorRepository.findById(monitorId)
	    			.orElse(null);
        }
    	
    	Long alunoId = monitoriaRequest.getAlunoId();
    	Aluno aluno = null;
    	if(alunoId != null) {
	    	aluno = alunoRepository.findById(alunoId)
	    			.orElse(null);
    	}
    	
    	Long disciplinaId = monitoriaRequest.getDisciplinaId();
    	Disciplina disciplina = null;
    	if (disciplinaId != null) {
	    	disciplina = disciplinaRepository.findById(disciplinaId)
	    			.orElse(null);
    	}
    	
    	if(monitoriaRequest.getData() != null) {
    		monitoria.setData(monitoriaRequest.getData());
    	}
    	
    	if(monitoriaRequest.getLocal() != null) {
    		monitoria.setLocal(monitoriaRequest.getLocal());
    	}
    	
    	if(monitoriaRequest.getStatus() != null) {
    		monitoria.setStatus(monitoriaRequest.getStatus());
    	}
    	
    	if(monitor != null) {
    		monitoria.setMonitor(monitor);
    	}
    	
    	if(aluno != null) {
    		monitoria.setAluno(aluno);
    	}
    	
    	if(disciplina != null) {
    		monitoria.setDisciplina(disciplina);
    	}
        
        Monitoria updatedMonitoria = monitoriaRepository.save(monitoria);
        return new ResponseEntity<>(MonitoriaDTOResponse.fromMonitoria(updatedMonitoria), HttpStatus.OK);
    }
    
    @PutMapping("/{id}/aceitar")
    public ResponseEntity<MonitoriaDTOResponse> aceitarMonitoria(@PathVariable Long id) {
        Monitoria monitoria = monitoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitoria não encontrada com o ID: " + id));


    	monitoria.setStatus("confirmada");
    	Monitoria updatedMonitoria = monitoriaRepository.save(monitoria);
    	
    	// Atualizar mensagem
    	List<Mensagem> mensagens = mensagemRepository.findAllByMonitoriaId(id).stream()
    			.filter(msg -> msg.getTipo().equals("Solicitação de Monitoria"))
    			.collect(Collectors.toList());
    	
    	if(mensagens.size() == 0) {
    		throw new ResourceNotFoundException("Mensagem de solicitação de monitoria não encontrada");
    	}
    	
    	Mensagem mensagem = mensagens.get(0);
    	
    	mensagem.setRespondida(true);
    	
    	mensagemRepository.save(mensagem);
    	
    	// Enviar resposta
    	Mensagem resposta = new Mensagem();
    	resposta.setData(LocalDateTime.now());
    	resposta.setTipo("Monitoria Aceita");
    	resposta.setMonitoria(updatedMonitoria);
    	resposta.setRespondida(false);
    	
    	mensagemRepository.save(resposta);
        
        
        return new ResponseEntity<>(MonitoriaDTOResponse.fromMonitoria(updatedMonitoria), HttpStatus.OK);
    }

    @PutMapping("/{id}/recusar")
    public ResponseEntity<MonitoriaDTOResponse> recusarMonitoria(@PathVariable Long id) {
        Monitoria monitoria = monitoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitoria não encontrada com o ID: " + id));


    	monitoria.setStatus("cancelada");
    	Monitoria updatedMonitoria = monitoriaRepository.save(monitoria);
    	
    	// Atualizar mensagem
    	List<Mensagem> mensagens = mensagemRepository.findAllByMonitoriaId(id).stream()
    			.filter(msg -> msg.getTipo().equals("Solicitação de Monitoria"))
    			.collect(Collectors.toList());
    	
    	if(mensagens.size() == 0) {
    		throw new ResourceNotFoundException("Mensagem de solicitação de monitoria não encontrada");
    	}
    	
    	Mensagem mensagem = mensagens.get(0);
    	
    	mensagem.setRespondida(true);
        
    	mensagemRepository.save(mensagem);
    	
    	// Enviar resposta
    	Mensagem resposta = new Mensagem();
    	resposta.setData(LocalDateTime.now());
    	resposta.setTipo("Monitoria Recusada");
    	resposta.setMonitoria(updatedMonitoria);
    	resposta.setRespondida(false);
    	
    	mensagemRepository.save(resposta);
        
        return new ResponseEntity<>(MonitoriaDTOResponse.fromMonitoria(updatedMonitoria), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonitoria(@PathVariable Long id) {
        Monitoria monitoria = monitoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitoria não encontrada com o ID: " + id));
        
        List<Mensagem> mensagens = mensagemRepository.findAllByMonitoriaId(id);
        
        for(Mensagem mensagem : mensagens) {
        	mensagemRepository.delete(mensagem);
        }
        
        monitoriaRepository.delete(monitoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
}
