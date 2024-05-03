package br.com.desafio.infrastructure.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "Poll")
public class PollEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid")
    private UUID id;
    private String title;
    private Long timeSession;
    private LocalDateTime startedAt;

    public PollEntity() {
    }

    public PollEntity(UUID id, String title, Long timeSession, LocalDateTime startedAt) {
        this.id = id;
        this.title = title;
        this.timeSession = timeSession;
        this.startedAt = startedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public Long getTimeSession() {
        return timeSession;
    }

    public void setTimeSession(Long timeSession) {
        this.timeSession = timeSession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PollEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", timeSession=" + timeSession +
                ", startedAt=" + startedAt +
                '}';
    }
}
