package br.com.desafio.adapters.persistence.mapper;

import br.com.desafio.adapters.persistence.entity.VoteInPollEntity;
import br.com.desafio.adapters.rest.DTO.VoteInPollDTO;
import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteInPollMapper {
    VoteInPollMapper INSTANCE = Mappers.getMapper(VoteInPollMapper.class);

    @Named("mapToInt")
    default VoteOptionEnum mapToInt(int value) {
        return VoteOptionEnum.fromValue(value);
    }

    @Mappings({
        @Mapping(source = "pollId", target = "pollId"),
        @Mapping(source = "cpfNumber", target = "cpfNumber"),
        @Mapping(source = "votingOption", target = "voteOptionEnum", qualifiedByName = "mapToInt")
    })
    VoteInPollEntity pollToPollEntity(VoteInPollDTO voteInPollDTO);
}
