package br.com.desafio.domain.polling.usecase;

import br.com.desafio.domain.polling.model.Poll;
import br.com.desafio.domain.polling.repository.PollRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreatePollUseCaseTest {

    private CreatePollUseCase createPollUseCase;
    private PollRepository pollRepository;

    @BeforeEach
    void setUp() {
        pollRepository = mock(PollRepository.class);
        createPollUseCase = new CreatePollUseCase(pollRepository);
    }

    @Test
    void execute_ValidTitleAndDefaultTimeSession_ShouldRegisterPoll() {
        String title = "Example Poll";
        UUID pollId = UUID.randomUUID();

        when(pollRepository.registerPoll(title, 60L)).thenReturn(new Poll(pollId, title, 60L));

        UUID createdPollId = createPollUseCase.execute(title);

        verify(pollRepository, times(1)).registerPoll(title, 60L);

        assertEquals(pollId, createdPollId);
    }

    @Test
    void execute_ValidTitleAndCustomTimeSession_ShouldRegisterPoll() {
        String title = "Example Poll";
        Long timeSession = 120L;
        UUID pollId = UUID.randomUUID();

        when(pollRepository.registerPoll(title, timeSession)).thenReturn(new Poll(pollId, title, timeSession));

        UUID createdPollId = createPollUseCase.execute(title, timeSession);

        verify(pollRepository, times(1)).registerPoll(title, timeSession);
        assertEquals(pollId, createdPollId);
    }

    @Test
    void execute_NullTitle_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> createPollUseCase.execute(null));
    }

    @Test
    void execute_EmptyTitle_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> createPollUseCase.execute(""));
    }

    @Test
    void execute_InvalidTimeSession_ShouldThrowIllegalArgumentException() {
        String title = "Example Poll";
        Long timeSession = 0L;

        assertThrows(IllegalArgumentException.class, () -> createPollUseCase.execute(title, timeSession));
    }
}
