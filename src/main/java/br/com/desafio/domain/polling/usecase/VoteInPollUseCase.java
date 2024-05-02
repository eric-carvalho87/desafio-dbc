package br.com.desafio.domain.polling.usecase;

import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import br.com.desafio.domain.polling.repository.PollRepository;
import br.com.desafio.domain.polling.repository.VoteRepository;
import br.com.desafio.domain.user.validation.UserValidationService;

import java.util.UUID;

public class VoteInPollUseCase {
    private final PollRepository pollRepository;
    private final UserValidationService userValidationService;
    private final VoteRepository voteRepository;

    public VoteInPollUseCase(PollRepository pollRepository, UserValidationService userValidationService, VoteRepository voteRepository) {
        this.pollRepository = pollRepository;
        this.userValidationService = userValidationService;
        this.voteRepository = voteRepository;
    }

    public void execute(UUID pollId, String cpfNumber, VoteOptionEnum votingOption) {

        if (!pollRepository.exists(pollId)) {
            throw new IllegalStateException("A enquete não existe.");
        }

        if (!userValidationService.isValidUser(cpfNumber)) {
            throw new IllegalStateException("Usuário inválido.");
        }

        if (!pollRepository.isSessionOpen(pollId)) {
            throw new IllegalStateException("A sessão não foi iniciada.");
        }

        if (!pollRepository.isSessionInProgress(pollId)) {
            throw new IllegalStateException("A sessão de votação está encerrada.");
        }

        if (voteRepository.hasVoted(pollId, cpfNumber)) {
            throw new IllegalStateException("O usuário já votou nesta enquete.");
        }

        voteRepository.registerVote(pollId, cpfNumber, votingOption.getValue());
    }
}
