package br.com.desafio.domain.polling.usecase;

import br.com.desafio.domain.polling.repository.PollRepository;

import java.util.UUID;

public class StartPollUseCase {
    private final PollRepository pollRepository;

    public StartPollUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public void execute(UUID pollId, long timeSession) {
        pollRepository.startSession(pollId, timeSession);
    }
}
