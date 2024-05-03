package br.com.desafio.adapters.mapper;

import br.com.desafio.infrastructure.repository.entity.PollEntity;
import br.com.desafio.domain.polling.model.Poll;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PollMapper {
    PollMapper INSTANCE = Mappers.getMapper(PollMapper.class);

    PollEntity pollToPollEntity(Poll poll);

    Poll pollEntityToPoll(PollEntity pollEntity);

    List<Poll> listPollEntityToListPoll(List<PollEntity> listPollEntity);
}
