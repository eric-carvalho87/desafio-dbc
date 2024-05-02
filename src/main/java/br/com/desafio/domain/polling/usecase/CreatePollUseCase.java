package br.com.desafio.domain.polling.usecase;

import br.com.desafio.domain.polling.model.Poll;
import br.com.desafio.domain.polling.repository.PollRepository;

import java.util.UUID;

public class CreatePollUseCase {
    private final PollRepository pollRepository;

    public CreatePollUseCase(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public UUID execute(String title) {

        if (!isValidTitle(title)) {
            throw new IllegalArgumentException("O título da enquete não pode estar vazio.");
        }

        Poll poll = pollRepository.registerPoll(title, 60L);
        return poll.getId();
    }

    public UUID execute(String title, Long timeSession) {

        if (!isValidTitle(title)) {
            throw new IllegalArgumentException("A duração da sessão da enquete deve ser maior que zero.");
        }

        if (timeSession == null || timeSession <= 0) {
            throw new IllegalArgumentException("A duração da sessão da enquete deve ser maior que zero.");
        }

        Poll poll = pollRepository.registerPoll(title, timeSession);
        return poll.getId();
    }

    private boolean isValidTitle(String title) {
        return title != null && !title.isEmpty();
    }
}
