package br.com.desafio.adapters.session;

import br.com.desafio.domain.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class SessionManagerImpl implements SessionManager {
    private static final Logger log = LoggerFactory.getLogger(SessionManagerImpl.class);
    private ScheduledExecutorService scheduler;
    private UUID pollID;

    public SessionManagerImpl() {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void startSession(long sessionDurationSeconds, UUID pollID) {
        LocalDateTime sessionEndTime = LocalDateTime.now().plusSeconds(sessionDurationSeconds);
        this.pollID = pollID;
        log.info("Sessão aberta, pollId: {}. Tempo de término: {}", pollID, sessionEndTime);

        scheduler.schedule(this::endSession, sessionDurationSeconds, TimeUnit.SECONDS);
    }

    public void endSession() {
        log.info("Sessão encerrada. PollId: {}", pollID);
        // Notificar o serviço
    }
}
