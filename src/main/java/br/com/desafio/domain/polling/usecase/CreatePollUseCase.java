package br.com.desafio.domain.polling.usecase;

import br.com.desafio.domain.polling.model.Poll;
import br.com.desafio.domain.polling.repository.PollRepository;
import br.com.desafio.domain.session.SessionManager;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreatePollUseCase {
    private final PollRepository pollRepository;
    private final SessionManager sessionManager;

    public CreatePollUseCase(PollRepository pollRepository, SessionManager sessionManager) {
        this.pollRepository = pollRepository;
        this.sessionManager = sessionManager;
    }

    public UUID execute(Poll poll) {

        if (!isValidTitle(poll.getTitle())) {
            throw new IllegalArgumentException("O título da enquete não pode estar vazio.");
        }

        if (poll.getTimeSession() != null && poll.getTimeSession() > 3600L) {
            throw new IllegalArgumentException("Tempo de sessão excede o tempo máximo");
        }

        if (poll.getTimeSession() == null || poll.getTimeSession() < 0) {
            poll.setTimeSession(60L);
        }

        poll.setStartedAt(LocalDateTime.now());

        Poll pollResult = pollRepository.registerPoll(poll);
        sessionManager.startSession(pollResult.getTimeSession());

        return pollResult.getId();
    }

    private boolean isValidTitle(String title) {
        return title != null && !title.isEmpty();
    }
}
