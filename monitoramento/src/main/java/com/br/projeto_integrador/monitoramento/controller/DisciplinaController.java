package com.br.projeto_integrador.monitoramento.controller;

import com.br.projeto_integrador.monitoramento.domain.Aluno;
import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import com.br.projeto_integrador.monitoramento.domain.Monitor;
import com.br.projeto_integrador.monitoramento.repository.AlunoRepository;
import com.br.projeto_integrador.monitoramento.repository.DisciplinaRepository;
import com.br.projeto_integrador.monitoramento.repository.MonitorRepository;
import com.br.projeto_integrador.monitoramento.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaRepository disciplinaRepository;

    private final AlunoRepository alunoRepository;

    private final MonitorRepository monitorRepository;

    // Método para listar todas as disciplinas
    @GetMapping
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    // Método para criar uma nova disciplina
    @PostMapping
    public ResponseEntity<Disciplina> createDisciplina(@RequestBody Disciplina disciplina) {
        Disciplina savedDisciplina = disciplinaRepository.save(disciplina);
        return new ResponseEntity<>(savedDisciplina, HttpStatus.CREATED);
    }

    // Método para buscar uma disciplina pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));
        return new ResponseEntity<>(disciplina, HttpStatus.OK);
    }

    // Método para atualizar uma disciplina existente
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplinaDetails) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));

        disciplina.setNome(disciplinaDetails.getNome());
        // Atualize outros campos necessários

        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return new ResponseEntity<>(updatedDisciplina, HttpStatus.OK);
    }

    // Método para deletar uma disciplina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));
        disciplinaRepository.delete(disciplina);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para listar todos os monitores associados a uma disciplina
    @GetMapping("/{id}/monitores")
    public ResponseEntity<List<Monitor>> getMonitoresByDisciplinaId(@PathVariable Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));
        return new ResponseEntity<>(disciplina.getMonitores(), HttpStatus.OK);
    }

    // Método para listar todos os alunos associados a uma disciplina
    @GetMapping("/{id}/alunos")
    public ResponseEntity<List<Aluno>> getAlunosByDisciplinaId(@PathVariable Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));
        return new ResponseEntity<>(disciplina.getAlunos(), HttpStatus.OK);
    }

    // Método para adicionar um monitor a uma disciplina
    @PostMapping("/{id}/monitores/{monitorId}")
    public ResponseEntity<Disciplina> addMonitorToDisciplina(@PathVariable Long id, @PathVariable Long monitorId) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));

        Monitor monitor = monitorRepository.findById(monitorId)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + monitorId));

        disciplina.getMonitores().add(monitor);
        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return new ResponseEntity<>(updatedDisciplina, HttpStatus.OK);
    }

    // Método para remover um monitor de uma disciplina
    @DeleteMapping("/{id}/monitores/{monitorId}")
    public ResponseEntity<Disciplina> removeMonitorFromDisciplina(@PathVariable Long id, @PathVariable Long monitorId) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));

        Monitor monitor = monitorRepository.findById(monitorId)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + monitorId));

        disciplina.getMonitores().remove(monitor);
        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return new ResponseEntity<>(updatedDisciplina, HttpStatus.OK);
    }

    // Método para adicionar um aluno a uma disciplina
    @PostMapping("/{id}/alunos/{alunoId}")
    public ResponseEntity<Disciplina> addAlunoToDisciplina(@PathVariable Long id, @PathVariable Long alunoId) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + alunoId));

        disciplina.getAlunos().add(aluno);
        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return new ResponseEntity<>(updatedDisciplina, HttpStatus.OK);
    }

    // Método para remover um aluno de uma disciplina
    @DeleteMapping("/{id}/alunos/{alunoId}")
    public ResponseEntity<Disciplina> removeAlunoFromDisciplina(@PathVariable Long id, @PathVariable Long alunoId) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + id));

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + alunoId));

        disciplina.getAlunos().remove(aluno);
        Disciplina updatedDisciplina = disciplinaRepository.save(disciplina);
        return new ResponseEntity<>(updatedDisciplina, HttpStatus.OK);
    }
}
