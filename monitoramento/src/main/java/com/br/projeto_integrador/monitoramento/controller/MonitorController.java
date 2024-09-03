package com.br.projeto_integrador.monitoramento.controller;

import com.br.projeto_integrador.monitoramento.controller.dto.*;
import com.br.projeto_integrador.monitoramento.controller.dto.monitores.AlunoMonitoria;
import com.br.projeto_integrador.monitoramento.controller.dto.monitores.DisciplinasAlunos;
import com.br.projeto_integrador.monitoramento.domain.*;
import com.br.projeto_integrador.monitoramento.repository.*;
import com.br.projeto_integrador.monitoramento.util.ResourceNotFoundException;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/monitores")
@RequiredArgsConstructor
public class MonitorController {

    private final MonitorRepository monitorRepository;

    private final DisciplinaRepository disciplinaRepository;

    private final MonitoriaRepository monitoriaRepository;

    private final AlunoRepository alunoRepository;

    @GetMapping
    public List<MonitorDTOResponse> getAllMonitores(@RequestParam(required = false) String nome, @RequestParam(required = false) String disciplina) {
    	Specification<Monitor> spec = (root, query, cb) -> {
    		List<Predicate> predicates = new ArrayList<>();
    		
    		if (nome != null) {
    			predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
    		}
    		if (disciplina != null) {
    			Join<Monitor, Disciplina> disciplinaJoin = root.join("disciplinas");
    			predicates.add(cb.like(cb.lower(disciplinaJoin.get("nome")), "%" + disciplina.toLowerCase() + "%"));
    		}
    		
    		return cb.and(predicates.toArray(new Predicate[0]));
    	};
    	
    	List<Monitor> monitores = monitorRepository.findAll(spec);
        List<MonitorDTOResponse> list = new ArrayList<>();
        for (Monitor monitor : monitores) {
            MonitorDTOResponse monitorDTOResponse = MonitorDTOResponse.fromMonitor(monitor);
            list.add(monitorDTOResponse);
        }
        return list;
    }


    @PostMapping
    public ResponseEntity<Monitor> createMonitor(@RequestBody Monitor monitor) {
        Monitor savedMonitor = monitorRepository.save(monitor);
        return new ResponseEntity<>(savedMonitor, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MonitorDTOResponse> getMonitorById(@PathVariable Long id) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));
        
        MonitorDTOResponse monitorResponse = MonitorDTOResponse.fromMonitor(monitor);
        
        return new ResponseEntity<>(monitorResponse, HttpStatus.OK);
        
        
    }


    @PutMapping("/{id}")
    public ResponseEntity<Monitor> updateMonitor(@PathVariable Long id, @RequestBody Monitor monitorDetails) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));

        monitor.setNome(monitorDetails.getNome());
        monitor.setEmail(monitorDetails.getEmail());
        monitor.setSenha(monitorDetails.getSenha());
        monitor.setDisciplinas(monitorDetails.getDisciplinas());

        Monitor updatedMonitor = monitorRepository.save(monitor);
        return new ResponseEntity<>(updatedMonitor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonitor(@PathVariable Long id) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));
        monitorRepository.delete(monitor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}/disciplinas")
    public ResponseEntity<List<Disciplina>> getDisciplinasByMonitorId(@PathVariable Long id) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));
        List<Disciplina> disciplinas = monitor.getDisciplinas();
        return new ResponseEntity<>(disciplinas, HttpStatus.OK);
    }

    @PostMapping("/{id}/disciplinas/{disciplinaId}")
    public ResponseEntity<Monitor> addDisciplinaToMonitor(@PathVariable Long id, @PathVariable Long disciplinaId) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));

        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + disciplinaId));

        monitor.getDisciplinas().add(disciplina);
        Monitor updatedMonitor = monitorRepository.save(monitor);
        return new ResponseEntity<>(updatedMonitor, HttpStatus.OK);
    }


    @DeleteMapping("/{id}/disciplinas/{disciplinaId}")
    public ResponseEntity<Monitor> removeDisciplinaFromMonitor(@PathVariable Long id, @PathVariable Long disciplinaId) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));

        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrada com o ID: " + disciplinaId));

        if (!monitor.getDisciplinas().contains(disciplina)) {
            throw new ResourceNotFoundException("Disciplina não associada a este monitor.");
        }

        monitor.getDisciplinas().remove(disciplina);
        Monitor updatedMonitor = monitorRepository.save(monitor);
        return new ResponseEntity<>(updatedMonitor, HttpStatus.OK);
    }


    @GetMapping("/{id}/monitorias")
    public ResponseEntity<List<Monitoria>> getMonitoriasByMonitorId(@PathVariable Long id) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));
        return new ResponseEntity<>(monitor.getMonitorias(), HttpStatus.OK);
    }


    @PostMapping("/{id}/monitorias")
    public ResponseEntity<Monitoria> scheduleMonitoria(@PathVariable Long id, @RequestBody Monitoria monitoriaDetails) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));

        monitoriaDetails.setMonitor(monitor);
        Monitoria savedMonitoria = monitoriaRepository.save(monitoriaDetails);
        return new ResponseEntity<>(savedMonitoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/monitorias/{monitoriaId}")
    public ResponseEntity<Monitoria> updateMonitoria(@PathVariable Long id, @PathVariable Long monitoriaId, @RequestBody Monitoria monitoriaDetails) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));

        Monitoria monitoria = monitoriaRepository.findById(monitoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Monitoria não encontrada com o ID: " + monitoriaId));

        if (!monitoria.getMonitor().getId().equals(monitor.getId())) {
            throw new ResourceNotFoundException("Esta monitoria não está associada ao monitor especificado.");
        }

        monitoria.setData(monitoriaDetails.getData());
        monitoria.setLocal(monitoriaDetails.getLocal());
        monitoria.setAluno(monitoriaDetails.getAluno());
        monitoria.setDisciplina(monitoriaDetails.getDisciplina());

        Monitoria updatedMonitoria = monitoriaRepository.save(monitoria);
        return new ResponseEntity<>(updatedMonitoria, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/monitorias/{monitoriaId}")
    public ResponseEntity<Void> deleteMonitoria(@PathVariable Long id, @PathVariable Long monitoriaId) {
        Monitor monitor = monitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Monitor não encontrado com o ID: " + id));

        Monitoria monitoria = monitoriaRepository.findById(monitoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Monitoria não encontrada com o ID: " + monitoriaId));

        if (!monitoria.getMonitor().getId().equals(monitor.getId())) {
            throw new ResourceNotFoundException("Esta monitoria não está associada ao monitor especificado.");
        }

        monitoriaRepository.delete(monitoria);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // nossos endpoits do figma estao abaixo <--------->
    // Método para "logar"
    @PostMapping("/logar")
    public ResponseEntity<MonitorDTOResponse> logar(@RequestBody LoginDTO loginDTO) {
        var monitor = monitorRepository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());
        if(!monitor.isPresent()){
        	throw new ResourceNotFoundException("Usuário ou senha incorretos");
        }
        
        MonitorDTOResponse monitorResponse = MonitorDTOResponse.fromMonitor(monitor.get());

        return new ResponseEntity<>(monitorResponse, HttpStatus.OK);
    }

    //encontrar alunos por nome
    @GetMapping("/monitoriasPorAluno/{nome}")
    private ResponseEntity<List<AlunoMonitoria>> buscarPorAluno(@PathVariable String nome) {
        var alunos = alunoRepository.findByNomeContaining(nome);

        List<AlunoMonitoria> list = new ArrayList<>();

        if (!alunos.isEmpty()) {
            var alunosReais = alunos.get();
            for (Aluno aluno : alunosReais) {
                AlunoMonitoria alunoResponse = new AlunoMonitoria();
                List<DisciplinasAlunos> listaDisciplinaAluno = new ArrayList<>();
                for (Disciplina disciplina1 : aluno.getDisciplinas()) {
                    DisciplinasAlunos disciplinasAlunos = new DisciplinasAlunos();
                    disciplinasAlunos.setNome(disciplina1.getNome());
                    listaDisciplinaAluno.add(disciplinasAlunos);
                }
                alunoResponse.setDisciplinas(listaDisciplinaAluno);
                alunoResponse.setAvaliacao(aluno.getAvaliacao());
                alunoResponse.setNome(aluno.getNome());
                list.add(alunoResponse);

            }
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //encontras alunos por disciplina
    @GetMapping("/monitoriasPorDisciplina/{nome}")
    private ResponseEntity<List<AlunoMonitoria>> buscarPorDisciplina(@PathVariable String nome){
        var disciplina = disciplinaRepository.findByNome(nome);


        List<AlunoMonitoria> lista = new ArrayList<>();
        if(!disciplina.isEmpty()) {
            var disciplinaAchada = disciplina.get();
            for (Aluno aluno : disciplinaAchada.getAlunos()) {
                AlunoMonitoria alunoMonitoria = new AlunoMonitoria();
                List<DisciplinasAlunos> listaDisciplinaAluno = new ArrayList<>();
                for (Disciplina disciplina1 : aluno.getDisciplinas()) {
                    DisciplinasAlunos disciplinasAlunos = new DisciplinasAlunos();
                    disciplinasAlunos.setNome(disciplina1.getNome());
                    listaDisciplinaAluno.add(disciplinasAlunos);
                }
                alunoMonitoria.setDisciplinas(listaDisciplinaAluno);
                alunoMonitoria.setNome(aluno.getNome());
                alunoMonitoria.setAvaliacao(aluno.getAvaliacao());
                lista.add(alunoMonitoria);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // usar esse para o endpoint do Monitor do Minhas Mentorias"
    @GetMapping("/minhasMonitorias/{id}")
    public ResponseEntity<List<MonitoriaResponseFinalMonitor>> buscarMonitoriasPeloMonitor(@PathVariable Long id) {
        Optional<Monitor> monitor = monitorRepository.findById(id);
        List<MonitoriaResponseFinalMonitor> list = new ArrayList<>();
        if(!monitor.isEmpty()) {

            var monitorAchado = monitor.get();

            for (Monitoria monitoria : monitorAchado.getMonitorias()) {
                MonitoriaResponseFinalMonitor monitoriaResponseFinal = new MonitoriaResponseFinalMonitor();
                monitoriaResponseFinal.setAluno(monitoria.getAluno().getNome());
                monitoriaResponseFinal.setData(monitoria.getData());
                monitoriaResponseFinal.setLocal(monitoria.getLocal());
                monitoriaResponseFinal.setDisciplina(monitoria.getDisciplina().getNome());
                list.add(monitoriaResponseFinal);
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}