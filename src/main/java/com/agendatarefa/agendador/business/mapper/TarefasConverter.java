package com.agendatarefa.agendador.business.mapper;

import com.agendatarefa.agendador.dto.TarefasDTO;
import com.agendatarefa.agendador.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasDTO paraTarefasDTO(TarefasEntity tarefasEntity);
    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

}
