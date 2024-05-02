package br.com.desafio.domain.polling.repository;

import br.com.desafio.domain.polling.enums.VoteOptionEnum;

import java.util.UUID;

public interface VoteRepository {
    void registerVote(UUID pollId, String cpfNumber, int votingOption);
    boolean hasVoted(UUID pollId,String cpfNumber);
    Long getTotalVotesForOption(UUID pollId, VoteOptionEnum voteOption);
}
