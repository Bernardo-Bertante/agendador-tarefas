package com.agendatarefa.agendador.controller;

import com.agendatarefa.agendador.business.TarefasService;
import com.agendatarefa.agendador.dto.TarefasDTO;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tarefas")
@AllArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> criarTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.criarTarefa(tarefasDTO, token));
    }

    //???????????
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {

        return ResponseEntity.ok(tarefasService.buscarTarefasPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }
}
