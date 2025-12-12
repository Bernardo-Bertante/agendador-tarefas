package com.agendatarefa.agendador.controller;

import com.agendatarefa.agendador.business.TarefasService;
import com.agendatarefa.agendador.dto.TarefasDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
