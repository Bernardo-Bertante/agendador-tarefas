package com.agendatarefa.agendador.business.mapper;

import com.agendatarefa.agendador.dto.TarefasDTO;
import com.agendatarefa.agendador.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefasUpdateConverter {

    void alterarTarefa(TarefasDTO dto, @MappingTarget TarefasEntity entity);
}
