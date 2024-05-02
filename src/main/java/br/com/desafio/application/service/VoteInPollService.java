package br.com.desafio.application.service;

import br.com.desafio.adapters.rest.DTO.VoteInPollDTO;
import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import br.com.desafio.domain.polling.usecase.VoteInPollUseCase;
import org.springframework.stereotype.Service;

@Service
public class VoteInPollService {
    private final VoteInPollUseCase voteInPollUseCase;

    public VoteInPollService(VoteInPollUseCase voteInPollUseCase) {
        this.voteInPollUseCase = voteInPollUseCase;
    }

    public void vote(VoteInPollDTO voteInPollDTO) {
        voteInPollUseCase.execute(voteInPollDTO.getPollId(), voteInPollDTO.getCpfNumber(), VoteOptionEnum.fromValue(voteInPollDTO.getVotingOption()));
    }
}
