package br.com.desafio.domain.polling.repository;

import br.com.desafio.domain.polling.model.Poll;

import java.util.UUID;

public interface PollRepository {
    Poll registerPoll(String title, long timeSession);
    boolean exists(UUID pollId);
    boolean isSessionOpen(UUID pollId);
    boolean isSessionInProgress(UUID pollId);
    void startSession(UUID pollId, long timeSession);
}
