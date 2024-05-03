package br.com.desafio.application.service;

import br.com.desafio.adapters.persistence.mapper.PollMapper;
import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import br.com.desafio.domain.polling.model.Poll;
import br.com.desafio.domain.polling.usecase.CreatePollUseCase;
import br.com.desafio.domain.polling.usecase.StartPollUseCase;
import br.com.desafio.domain.polling.usecase.ViewPollResultsUseCase;
import br.com.desafio.infrastructure.repository.PollJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PollService {

    private final CreatePollUseCase createPollUseCase;
    private final StartPollUseCase startPollUseCase;
    private final ViewPollResultsUseCase viewPollResultsUseCase;
    private final PollJpaRepository pollJpaRepository;

    public PollService(CreatePollUseCase createPollUseCase, StartPollUseCase startPollUseCase, ViewPollResultsUseCase viewPollResultsUseCase, PollJpaRepository pollJpaRepository) {
        this.createPollUseCase = createPollUseCase;
        this.startPollUseCase = startPollUseCase;
        this.viewPollResultsUseCase = viewPollResultsUseCase;
        this.pollJpaRepository = pollJpaRepository;
    }

    public UUID createPoll(Poll pollRequest) {
        return createPollUseCase.execute(pollRequest);
    }

    public void startPoll(UUID pollId, long timeSession) {
        startPollUseCase.execute(pollId, timeSession);
    }

    public Map<VoteOptionEnum, Long> viewResult(UUID pollId) {
        return viewPollResultsUseCase.execute(pollId);
    }

    public List<Poll> listPolls() {
        return PollMapper.INSTANCE.listPollEntityToListPoll(pollJpaRepository.findAll());
    }
}
