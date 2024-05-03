package br.com.desafio.domain.polling.usecase;

import br.com.desafio.domain.polling.model.Poll;
import br.com.desafio.domain.polling.repository.PollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreatePollUseCaseTest {

    private CreatePollUseCase createPollUseCase;
    private PollRepository pollRepository;
    private Poll poll;

    @BeforeEach
    void setUp() {
        pollRepository = mock(PollRepository.class);
        createPollUseCase = new CreatePollUseCase(pollRepository);
        poll = new Poll(UUID.randomUUID(), "Example Poll", 60L);
    }

    @Test
    void execute_ValidTitleAndDefaultTimeSession_ShouldRegisterPoll() {
        poll.setTimeSession(null);

        when(pollRepository.registerPoll(poll)).thenReturn(poll);

        createPollUseCase.execute(poll);

        verify(pollRepository, times(1)).registerPoll(poll);

        assertEquals(poll.getTimeSession(), 60L);
    }

    @Test
    void execute_ValidTitleAndCustomTimeSession_ShouldRegisterPoll() {
        Long timeSession = 1800L;
        poll.setTimeSession(timeSession);

        when(pollRepository.registerPoll(poll)).thenReturn(poll);

        createPollUseCase.execute(poll);

        verify(pollRepository, times(1)).registerPoll(poll);
        assertEquals(poll.getTimeSession(), timeSession);
    }

    @Test
    void execute_NullTitle_ShouldThrowIllegalArgumentException() {
        poll.setTitle(null);
        assertThrows(IllegalArgumentException.class, () -> createPollUseCase.execute(poll));
    }

    @Test
    void execute_EmptyTitle_ShouldThrowIllegalArgumentException() {
        poll.setTitle("");
        assertThrows(IllegalArgumentException.class, () -> createPollUseCase.execute(poll));
    }

    @Test
    void execute_InvalidTimeSession_ShouldThrowIllegalArgumentException() {
        poll.setTimeSession(4000L);
        assertThrows(IllegalArgumentException.class, () -> createPollUseCase.execute(poll));
    }
}
