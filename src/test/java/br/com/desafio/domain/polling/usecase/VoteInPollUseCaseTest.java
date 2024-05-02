package br.com.desafio.domain.polling.usecase;

import br.com.desafio.domain.polling.enums.VoteOptionEnum;
import br.com.desafio.domain.polling.repository.PollRepository;
import br.com.desafio.domain.polling.repository.VoteRepository;
import br.com.desafio.domain.user.validation.UserValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VoteInPollUseCaseTest {

    private VoteInPollUseCase voteInPollUseCase;
    private PollRepository pollRepository;
    private UserValidationService userValidationService;
    private VoteRepository voteRepository;

    private static final UUID pollId = UUID.randomUUID();
    private static final String userId = "35108385056";
    private static final VoteOptionEnum option = VoteOptionEnum.SIM;

    @BeforeEach
    void setUp() {
        pollRepository = mock(PollRepository.class);
        userValidationService = mock(UserValidationService.class);
        voteRepository = mock(VoteRepository.class);
        voteInPollUseCase = new VoteInPollUseCase(pollRepository, userValidationService, voteRepository);
    }

    @Test
    void voteInPoll_InvalidPollId_ShouldThrowException() {

        when(userValidationService.isValidUser(userId)).thenReturn(true);
        when(pollRepository.exists(pollId)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> voteInPollUseCase.execute(pollId, userId, option));
    }

    @Test
    void voteInPoll_InvalidUser_ShouldThrowException() {

        when(userValidationService.isValidUser(userId)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> voteInPollUseCase.execute(pollId, userId, option));
    }

    @Test
    void voteInPoll_SessionClosed_ShouldThrowException() {

        when(userValidationService.isValidUser(userId)).thenReturn(true);
        when(voteRepository.hasVoted(pollId, userId)).thenReturn(false);
        when(pollRepository.exists(pollId)).thenReturn(true);
        when(pollRepository.isSessionOpen(pollId)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> voteInPollUseCase.execute(pollId, userId, option));
    }

    @Test
    void voteInPoll_UserAlreadyVoted_ShouldThrowException() {

        when(userValidationService.isValidUser(userId)).thenReturn(true);
        when(voteRepository.hasVoted(pollId, userId)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> voteInPollUseCase.execute(pollId, userId, option));
    }

    @Test
    void voteInPoll_ValidUserAndPollAndSessionOpenAndUserHasNotVoted_ShouldRegisterVote() {

        when(userValidationService.isValidUser(userId)).thenReturn(true);
        when(voteRepository.hasVoted(pollId, userId)).thenReturn(false);
        when(pollRepository.exists(pollId)).thenReturn(true);
        when(pollRepository.isSessionOpen(pollId)).thenReturn(true);
        when(pollRepository.isSessionInProgress(pollId)).thenReturn(true);

        voteInPollUseCase.execute(pollId, userId, option);

        verify(voteRepository, times(1)).registerVote(pollId, userId, option.getValue());
    }
}