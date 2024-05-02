package br.com.desafio.adapters.persistence.mapper;

import br.com.desafio.adapters.persistence.entity.PollEntity;
import br.com.desafio.domain.polling.model.Poll;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PollMapper {
    PollMapper INSTANCE = Mappers.getMapper(PollMapper.class);

    PollEntity pollToPollEntity(Poll poll);

    Poll pollEntityToPoll(PollEntity pollEntity);
}
