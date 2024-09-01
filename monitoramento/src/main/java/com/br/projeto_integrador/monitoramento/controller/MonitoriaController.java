package com.br.projeto_integrador.monitoramento.controller;

import com.br.projeto_integrador.monitoramento.domain.Monitoria;
import com.br.projeto_integrador.monitoramento.repository.MonitoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monitorias")
@RequiredArgsConstructor
public class MonitoriaController {

    private final MonitoriaRepository monitoriaRepository;

    @GetMapping
    public List<Monitoria> getAllMonitorias() {
        return monitoriaRepository.findAll();
    }

    @PostMapping
    public Monitoria createMonitoria(@RequestBody Monitoria monitoria) {
        return monitoriaRepository.save(monitoria);
    }

    // Outros métodos conforme necessário
}
