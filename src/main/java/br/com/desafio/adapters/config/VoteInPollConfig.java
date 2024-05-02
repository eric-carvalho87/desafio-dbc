package br.com.desafio.adapters.config;

import br.com.desafio.domain.polling.usecase.VoteInPollUseCase;
import br.com.desafio.infrastructure.external.ExternalUserValidationService;
import br.com.desafio.infrastructure.persistence.PollRepositoryImpl;
import br.com.desafio.infrastructure.persistence.VoteInPollrepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoteInPollConfig {
    @Bean
    public VoteInPollUseCase voteInPollUseCase(PollRepositoryImpl pollRepository, ExternalUserValidationService service, VoteInPollrepositoryImpl voteInPollrepository) {
        return new VoteInPollUseCase(pollRepository, service, voteInPollrepository);
    }
}