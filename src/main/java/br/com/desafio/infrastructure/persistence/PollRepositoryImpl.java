package br.com.desafio.infrastructure.persistence;

import br.com.desafio.adapters.persistence.entity.PollEntity;
import br.com.desafio.adapters.persistence.mapper.PollMapper;
import br.com.desafio.domain.polling.model.Poll;
import br.com.desafio.domain.polling.repository.PollRepository;
import br.com.desafio.infrastructure.repository.PollJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class PollRepositoryImpl implements PollRepository {

    private final PollJpaRepository pollJpaRepository;

    public PollRepositoryImpl(PollJpaRepository pollJpaRepository) {
        this.pollJpaRepository = pollJpaRepository;
    }

    @Override
    public Poll registerPoll(String title, long timeSession) {
        PollEntity pollEntity = new PollEntity(null, title, timeSession, null);
        return PollMapper.INSTANCE.pollEntityToPoll(pollJpaRepository.save(pollEntity));
    }

    @Override
    public boolean exists(UUID pollId) {
        return pollJpaRepository.existsById(pollId);
    }

    @Override
    public boolean isSessionOpen(UUID pollId) {
        Optional<PollEntity> pollEntity = pollJpaRepository.findById(pollId);

        if (pollEntity.isEmpty()) {
            throw new IllegalArgumentException("Enquete não encontrada com o ID fornecido: " + pollId);
        }

        return pollEntity.get().getStartedAt() != null;
    }

    @Override
    public boolean isSessionInProgress(UUID pollId) {
        Optional<PollEntity> pollEntity = pollJpaRepository.findById(pollId);

        if (pollEntity.isEmpty()) {
            throw new IllegalArgumentException("Enquete não encontrada com o ID fornecido: " + pollId);
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime endTime = pollEntity.get().getStartedAt().plusSeconds(pollEntity.get().getTimeSession());
        return now.isBefore(endTime);
    }

    @Override
    public void startSession(UUID pollId, long timeSession) {
        Optional<PollEntity> optionalUser = pollJpaRepository.findById(pollId);
        if (optionalUser.isPresent()) {
            PollEntity pollEntity = optionalUser.get();
            pollEntity.setTimeSession(timeSession);
            pollEntity.setStartedAt(LocalDateTime.now());
            pollJpaRepository.save(pollEntity);
        } else {
            throw new EntityNotFoundException("Poll not found with ID: " + pollId);
        }
    }
}
