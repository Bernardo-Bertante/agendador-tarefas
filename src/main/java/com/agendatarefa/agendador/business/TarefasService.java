package com.agendatarefa.agendador.business;

import com.agendatarefa.agendador.business.mapper.TarefasConverter;
import com.agendatarefa.agendador.dto.TarefasDTO;
import com.agendatarefa.agendador.infrastructure.entity.TarefasEntity;
import com.agendatarefa.agendador.infrastructure.enums.StatusNotificacaoEnum;
import com.agendatarefa.agendador.infrastructure.repository.TarefasRepository;
import com.agendatarefa.agendador.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO criarTarefa(TarefasDTO dto, String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefasEntity(dto);

        return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));

    }

    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicio, dataFim));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }
}
