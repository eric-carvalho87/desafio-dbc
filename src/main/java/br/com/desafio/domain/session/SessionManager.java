package br.com.desafio.domain.session;

import java.util.UUID;

public interface SessionManager {
    void startSession(long sessionDurationSeconds, UUID pollId);
    void endSession();
}
