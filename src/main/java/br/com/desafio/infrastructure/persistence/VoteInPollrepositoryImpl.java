package br.com.desafio.infrastructure.persistence;

import br.com.desafio.infrastructure.repository.entity.VoteInPollEntity;
import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import br.com.desafio.domain.polling.repository.VoteRepository;
import br.com.desafio.infrastructure.repository.VoteInPollJpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VoteInPollrepositoryImpl implements VoteRepository {

    private final VoteInPollJpaRepository voteInPollJpaRepository;

    public VoteInPollrepositoryImpl(VoteInPollJpaRepository voteInPollJpaRepository) {
        this.voteInPollJpaRepository = voteInPollJpaRepository;
    }

    @Override
    public void registerVote(UUID pollId, String cpfNumber, int votingOption) {
        VoteInPollEntity entity = new VoteInPollEntity(pollId, cpfNumber, votingOption);
        voteInPollJpaRepository.save(entity);
    }

    @Override
    public boolean hasVoted(UUID pollId, String cpfNumber) {
        return voteInPollJpaRepository.hasVoted(pollId, cpfNumber);
    }

    @Override
    public Long getTotalVotesForOption(UUID pollId, VoteOptionEnum voteOption) {
        return voteInPollJpaRepository.getTotalVotesForOption(pollId, voteOption);
    }
}
