package br.com.desafio.adapters.config;

import br.com.desafio.domain.polling.repository.PollRepository;
import br.com.desafio.domain.polling.repository.VoteRepository;
import br.com.desafio.domain.polling.usecase.CreatePollUseCase;
import br.com.desafio.domain.polling.usecase.StartPollUseCase;
import br.com.desafio.domain.polling.usecase.ViewPollResultsUseCase;
import br.com.desafio.infrastructure.persistence.PollRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PollConfig {
    @Bean
    public CreatePollUseCase createPollUseCase(PollRepositoryImpl pollRepository) {
        return new CreatePollUseCase(pollRepository);
    }

    @Bean
    public StartPollUseCase startPollUseCase(PollRepositoryImpl pollRepository) {
        return new StartPollUseCase(pollRepository);
    }

    @Bean
    public ViewPollResultsUseCase viewPollResultsUseCase(PollRepository pollRepository, VoteRepository voteRepository) {
        return new ViewPollResultsUseCase(pollRepository, voteRepository);
    }
}