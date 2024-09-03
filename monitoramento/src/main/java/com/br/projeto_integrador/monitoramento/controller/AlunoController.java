package com.br.projeto_integrador.monitoramento.controller;

import com.br.projeto_integrador.monitoramento.controller.dto.*;
import com.br.projeto_integrador.monitoramento.controller.dto.alunos.DisciplinasResponse;
import com.br.projeto_integrador.monitoramento.controller.dto.alunos.MonitorResponse;
import com.br.projeto_integrador.monitoramento.controller.dto.alunos.MonitoriaMarcada;
import com.br.projeto_integrador.monitoramento.domain.Aluno;
import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import com.br.projeto_integrador.monitoramento.domain.Monitor;
import com.br.projeto_integrador.monitoramento.domain.Monitoria;
import com.br.projeto_integrador.monitoramento.repository.AlunoRepository;
import com.br.projeto_integrador.monitoramento.repository.DisciplinaRepository;
import com.br.projeto_integrador.monitoramento.repository.MonitorRepository;
import com.br.projeto_integrador.monitoramento.repository.MonitoriaRepository;
import com.br.projeto_integrador.monitoramento.util.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoRepository alunoRepository;

    private final MonitoriaRepository monitoriaRepository;

    private final MonitorRepository monitorRepository;

    private final DisciplinaRepository disciplinaRepository;


    // Método para listar todos os alunos
    @GetMapping
    public List<AlunoDTOResponse> getAllAlunos(@RequestParam(required = false) String nome, @RequestParam(required = false) String disciplina) {
    	Specification<Aluno> spec = (root, query, cb) -> {
    		List<Predicate> predicates = new ArrayList<>();
    		
    		if (nome != null) {
    			predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
    		}
    		if (disciplina != null) {
    			Join<Aluno, Disciplina> disciplinaJoin = root.join("disciplinas");
    			predicates.add(cb.like(cb.lower(disciplinaJoin.get("nome")), "%" + disciplina.toLowerCase() + "%"));
    		}
    		
    		return cb.and(predicates.toArray(new Predicate[0]));
    	};
    	
    	List<Aluno> alunos = alunoRepository.findAll(spec);
        List<AlunoDTOResponse> list = new ArrayList<>();
        for (Aluno aluno: alunos) {
            AlunoDTOResponse alunoDTOResponse = getAlunoDTOResponse(aluno);
            list.add(alunoDTOResponse);
        }
        return list;
    }

    // Método para criar um novo aluno
    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
        Aluno savedAluno = alunoRepository.save(aluno);
        return new ResponseEntity<>(savedAluno, HttpStatus.CREATED);
    }

    // Método para buscar um aluno pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTOResponse> getAlunoById(@PathVariable Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + id));

        AlunoDTOResponse alunoDTOResponse = getAlunoDTOResponse(aluno);

        return new ResponseEntity<>(alunoDTOResponse, HttpStatus.OK);
    }

    // Método para atualizar as informações de um aluno
    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable Long id, @RequestBody Aluno alunoDetails) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + id));

        aluno.setNome(alunoDetails.getNome());
        aluno.setEmail(alunoDetails.getEmail());
        aluno.setSenha(alunoDetails.getSenha());
        aluno.setDisciplinas(alunoDetails.getDisciplinas());

        Aluno updatedAluno = alunoRepository.save(aluno);
        return new ResponseEntity<>(updatedAluno, HttpStatus.OK);
    }

    // Método para deletar um aluno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + id));
        alunoRepository.delete(aluno);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método para listar todas as disciplinas de um aluno
    @GetMapping("/{id}/disciplinas")
    public ResponseEntity<List<DisciplinaDTOResponseAluno>> getDisciplinasByAlunoId(@PathVariable Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + id));

        var alunoDTO = getAlunoDTOResponse(aluno);

        return new ResponseEntity<>(alunoDTO.getDisciplinas(), HttpStatus.OK);
    }

    // Método para listar todas as monitorias de um aluno
    @GetMapping("/{id}/monitorias")
    public ResponseEntity<List<MonitoriaResponseAluno>> getMonitoriasByAlunoId(@PathVariable Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + id));

        var alunoDTO = getAlunoDTOResponse(aluno);

        return new ResponseEntity<>(alunoDTO.getMentoria(), HttpStatus.OK);
    }

    // Método para agendar uma nova monitoria para um aluno
    @PostMapping("/{id}/monitorias")
    public ResponseEntity<Monitoria> agendarMonitoria(@PathVariable Long id, @RequestBody Monitoria monitoriaDetails) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + id));
        monitoriaDetails.setAluno(aluno);
        Monitoria savedMonitoria = monitoriaRepository.save(monitoriaDetails);
        return new ResponseEntity<>(savedMonitoria, HttpStatus.CREATED);
    }

    // metodos estaticos

    private static AlunoDTOResponse getAlunoDTOResponse(Aluno aluno) {
        AlunoDTOResponse alunoDTOResponse = new AlunoDTOResponse();
        alunoDTOResponse.setId(aluno.getId());
        alunoDTOResponse.setNome(aluno.getNome());
        alunoDTOResponse.setEmail(aluno.getEmail());
        List<DisciplinaDTOResponseAluno> listaDisciplinas = new ArrayList<>();
        for (Disciplina disciplina: aluno.getDisciplinas()
        ) {
            DisciplinaDTOResponseAluno disciplinaDTOResponseAluno = new DisciplinaDTOResponseAluno();
            disciplinaDTOResponseAluno.setNome(disciplina.getNome());
            listaDisciplinas.add(disciplinaDTOResponseAluno);
        }
        alunoDTOResponse.setDisciplinas(listaDisciplinas);
        alunoDTOResponse.setEmail(alunoDTOResponse.getEmail());
        List<MonitoriaResponseAluno> listaMonitoria = new ArrayList<>();
        for(Monitoria monitoria : aluno.getMonitorias()){
            MonitoriaResponseAluno monitoriaResponseAluno = new MonitoriaResponseAluno();
            monitoriaResponseAluno.setData(monitoria.getData());
            monitoriaResponseAluno.setLocal(monitoria.getLocal());
            monitoriaResponseAluno.setMonitor(new MonitorResponseAluno());
            monitoriaResponseAluno.getMonitor().setNome(monitoria.getMonitor().getNome());
            listaMonitoria.add(monitoriaResponseAluno);
        }
        alunoDTOResponse.setMentoria(listaMonitoria);
        alunoDTOResponse.setDisciplinas(listaDisciplinas);
        return alunoDTOResponse;
    }

    //endpoints uteis OS QUE VAMOS USAR ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // procurar monitores pelo nome
    @GetMapping("/buscarMonitor/{monitor}")
    private ResponseEntity<MonitorResponse> buscarPorMonitor(@PathVariable String monitor){
        Optional<Monitor> monitorEntity = monitorRepository.findByNome(monitor);
        var monitorReal = monitorEntity.get();
        MonitorResponse monitorResponse = new MonitorResponse();
        monitorResponse.setNome(monitorReal.getNome());
        monitorResponse.setAvaliacao(monitorReal.getAvaliacao());
        List<DisciplinasResponse> listaDisciplinasResponses = new ArrayList<>();
        for(Disciplina disciplina1 : monitorReal.getDisciplinas()){
            DisciplinasResponse disciplinasResponse = new DisciplinasResponse();
            disciplinasResponse.setNome(disciplina1.getNome());
            listaDisciplinasResponses.add(disciplinasResponse);
        }
        monitorResponse.setDisciplina(listaDisciplinasResponses);

        return new ResponseEntity<>(monitorResponse, HttpStatus.OK);
    }

    // procurar monitores pela disciplina
    @GetMapping("/buscarMonitores/{disciplina}")
    private ResponseEntity<List<MonitorResponse>> buscarPorDisciplina(@PathVariable String disciplina){
        Optional<Disciplina> disciplinaBusca = disciplinaRepository.findByNome(disciplina);
        List<MonitorResponse> lista = new ArrayList<>();
        if(!disciplinaBusca.isEmpty()) {
            var disciplinaReal = disciplinaBusca.get();

            for (Monitor monitor : disciplinaReal.getMonitores()) {
                MonitorResponse monitorResponse = new MonitorResponse();
                monitorResponse.setNome(monitor.getNome());
                monitorResponse.setAvaliacao(monitor.getAvaliacao());
                List<DisciplinasResponse> listaDisciplinasResponses = new ArrayList<>();
                for (Disciplina disciplina1 : monitor.getDisciplinas()) {
                    DisciplinasResponse disciplinasResponse = new DisciplinasResponse();
                    disciplinasResponse.setNome(disciplina1.getNome());
                    listaDisciplinasResponses.add(disciplinasResponse);
                }
                monitorResponse.setDisciplina(listaDisciplinasResponses);
                lista.add(monitorResponse);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Monitorias agendadas (lista)
    @GetMapping("/minhasMonitorias/{id}")
    private ResponseEntity<List<MonitoriaMarcada>> buscarMonitorias(@PathVariable Long id){
        Optional<Aluno> aluno = alunoRepository.findById(id);
        List<MonitoriaMarcada> lista = new ArrayList<>();
        if(!aluno.isEmpty()) {
            var alunoAchado = aluno.get();


            for (Monitoria monitoria : alunoAchado.getMonitorias()) {
                MonitoriaMarcada monitoriaMarcada = new MonitoriaMarcada();
                monitoriaMarcada.setData(monitoria.getData());
                monitoriaMarcada.setLocal(monitoria.getLocal());
                monitoriaMarcada.setDisciplina(monitoria.getDisciplina().getNome());
                monitoriaMarcada.setMonitor(monitoria.getMonitor().getNome());
                lista.add(monitoriaMarcada);
            }

            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Método para "logar"
    @PostMapping("/logar")
    public ResponseEntity<AlunoDTOResponse> logar(@RequestBody LoginDTO loginDTO) {
        var aluno = alunoRepository.findByEmailAndSenha(loginDTO.getEmail(), loginDTO.getSenha());
        if(!aluno.isPresent()){
        	throw new ResourceNotFoundException("Usuário ou senha incorretos");
        }

        AlunoDTOResponse alunoResponse = getAlunoDTOResponse(aluno.get());
        
        return new ResponseEntity<>(alunoResponse, HttpStatus.OK);
    }
}