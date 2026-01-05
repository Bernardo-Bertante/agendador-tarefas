package com.agendatarefa.agendador.infrastructure.repository;

import com.agendatarefa.agendador.infrastructure.entity.TarefasEntity;
import com.agendatarefa.agendador.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetweenAndStatusNotificacaoEnum(LocalDateTime dataInicio, LocalDateTime dataFim, StatusNotificacaoEnum status);

    List<TarefasEntity> findByEmailUsuario(String email);

}
