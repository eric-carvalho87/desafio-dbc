package br.com.desafio.domain.polling.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Poll {
    private UUID id;
    private String title;
    private Long timeSession;
    private LocalDateTime startedAt;

    public Poll(UUID id, String title, Long timeSession) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("O título da enquete não pode estar vazio");
        }

        this.id = id;
        this.title = title;
        this.timeSession = (timeSession != null && timeSession > 0) ? timeSession : 60L;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTimeSession() {
        return timeSession;
    }

    public void setTimeSession(Long timeSession) {
        this.timeSession = timeSession;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public boolean isSessionOpen() {
        LocalDateTime endTime = startedAt.plusSeconds(timeSession);
        return LocalDateTime.now().isBefore(endTime);
    }

    @Override
    public String toString() {
        return "Poll{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", timeSession=" + timeSession +
                ", startedAt=" + startedAt +
                '}';
    }
}
