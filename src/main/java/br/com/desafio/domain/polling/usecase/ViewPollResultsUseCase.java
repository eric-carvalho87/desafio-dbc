package br.com.desafio.domain.polling.usecase;

import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import br.com.desafio.domain.polling.repository.PollRepository;
import br.com.desafio.domain.polling.repository.VoteRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ViewPollResultsUseCase {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    public ViewPollResultsUseCase(PollRepository pollRepository, VoteRepository voteRepository) {
        this.pollRepository = pollRepository;
        this.voteRepository = voteRepository;
    }

    public Map<VoteOptionEnum, Long> execute(UUID pollId) {
        if (!pollRepository.exists(pollId)) {
            throw new IllegalStateException("A enquete não existe.: " + pollId);
        }

        Map<VoteOptionEnum, Long> results = new HashMap<>();

        Long totalNoVotes = voteRepository.getTotalVotesForOption(pollId, VoteOptionEnum.NAO);
        results.put(VoteOptionEnum.NAO, totalNoVotes);

        Long totalYesVotes = voteRepository.getTotalVotesForOption(pollId, VoteOptionEnum.SIM);
        results.put(VoteOptionEnum.SIM, totalYesVotes);

        return results;
    }
}
