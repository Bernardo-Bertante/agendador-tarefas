package com.agendatarefa.agendador.infrastructure.repository;

import com.agendatarefa.agendador.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    List<TarefasEntity> findByEmailUsuario(String email);

}
