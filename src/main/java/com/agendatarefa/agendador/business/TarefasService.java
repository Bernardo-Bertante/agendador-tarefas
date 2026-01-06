package com.agendatarefa.agendador.business;

import com.agendatarefa.agendador.business.mapper.TarefasConverter;
import com.agendatarefa.agendador.business.mapper.TarefasUpdateConverter;
import com.agendatarefa.agendador.dto.TarefasDTO;
import com.agendatarefa.agendador.infrastructure.entity.TarefasEntity;
import com.agendatarefa.agendador.infrastructure.enums.StatusNotificacaoEnum;
import com.agendatarefa.agendador.infrastructure.exceptions.ResourceNotFoundException;
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
    private final TarefasUpdateConverter tarefasUpdateConverter;
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
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicio, dataFim, StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("ID " + id + " é inexistente", e.getCause());
        }
    }

    public TarefasDTO alterarStatusTarefa(String id, StatusNotificacaoEnum status) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("ID " + id + " é inexistente"));
            entity.setStatusNotificacaoEnum(status);
            entity.setDataAlteracao(LocalDateTime.now());
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("ID " + id + " é inexistente", e.getCause());
        }
    }

    public TarefasDTO alterarTarefa(String id, TarefasDTO dto) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("ID " + id + " é inexistente"));
            tarefasUpdateConverter.alterarTarefa(dto, entity);
            entity.setDataAlteracao(LocalDateTime.now());
            return tarefasConverter.paraTarefasDTO(tarefasRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("ID " + id + " é inexistente", e.getCause());
        }
    }
}
