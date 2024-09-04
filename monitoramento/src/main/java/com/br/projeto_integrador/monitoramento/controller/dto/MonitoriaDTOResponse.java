package com.br.projeto_integrador.monitoramento.controller.dto;

import java.time.LocalDateTime;

import com.br.projeto_integrador.monitoramento.domain.Aluno;
import com.br.projeto_integrador.monitoramento.domain.Disciplina;
import com.br.projeto_integrador.monitoramento.domain.Monitor;
import com.br.projeto_integrador.monitoramento.domain.Monitoria;

import lombok.Data;

@Data
public class MonitoriaDTOResponse {

	private Long id;
    private LocalDateTime data;
    private String local;
    private String status;

    private MonitorDTOResponse monitor;
    private AlunoDTOResponse aluno;
    private DisciplinaDTOResponse disciplina;
    
    MonitoriaDTOResponse(Long id, LocalDateTime data, String local, String status, Monitor monitor, Aluno aluno, Disciplina disciplina) {
    	this.id = id;
    	this.data = data;
    	this.local = local;
    	this.status = status;
    	this.monitor = MonitorDTOResponse.fromMonitor(monitor);
    	this.aluno = AlunoDTOResponse.fromAluno(aluno);
    	this.disciplina = DisciplinaDTOResponse.fromDisciplina(disciplina);
    }
    
    public static MonitoriaDTOResponse fromMonitoria(Monitoria monitoria) {
    	return new MonitoriaDTOResponse(monitoria.getId(), monitoria.getData(), monitoria.getLocal(), monitoria.getStatus(), monitoria.getMonitor(), monitoria.getAluno(), monitoria.getDisciplina());
    }
}