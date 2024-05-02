package br.com.desafio.application.service;

import br.com.desafio.adapters.rest.DTO.PollResultDTO;
import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import br.com.desafio.domain.polling.model.Poll;
import br.com.desafio.domain.polling.usecase.CreatePollUseCase;
import br.com.desafio.domain.polling.usecase.StartPollUseCase;
import br.com.desafio.domain.polling.usecase.ViewPollResultsUseCase;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class PollService {

    private final CreatePollUseCase createPollUseCase;
    private final StartPollUseCase startPollUseCase;
    private final ViewPollResultsUseCase viewPollResultsUseCase;

    public PollService(CreatePollUseCase createPollUseCase, StartPollUseCase startPollUseCase, ViewPollResultsUseCase viewPollResultsUseCase) {
        this.createPollUseCase = createPollUseCase;
        this.startPollUseCase = startPollUseCase;
        this.viewPollResultsUseCase = viewPollResultsUseCase;
    }

    public UUID createPoll(Poll pollRequest) {
        return createPollUseCase.execute(pollRequest.getTitle(), pollRequest.getTimeSession());
    }

    public void startPoll(UUID pollId, long timeSession) {
        startPollUseCase.execute(pollId, timeSession);
    }

    public Map<VoteOptionEnum, Long> viewResult(UUID pollId) {
        return viewPollResultsUseCase.execute(pollId);
    }
}
