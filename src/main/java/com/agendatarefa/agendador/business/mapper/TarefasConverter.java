package com.agendatarefa.agendador.business.mapper;

import com.agendatarefa.agendador.dto.TarefasDTO;
import com.agendatarefa.agendador.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    //????????????
    TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity);
    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> tarefasDTOS);
    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntities);

}
