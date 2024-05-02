package br.com.desafio.infrastructure.external;

import br.com.desafio.domain.user.validation.UserValidationService;
import br.com.desafio.infrastructure.exception.ExternalServiceException;
import br.com.desafio.infrastructure.external.DTO.ResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserValidationService implements UserValidationService {

    private final RestTemplate restTemplate;
    private final static String ABLE_TO_VOTE = "ABLE_TO_VOTE";
    private final static String ENDPOINT_USER = "https://user-info.herokuapp.com/users/";

    public ExternalUserValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isValidUser(String cpfNumber) {
        try {
            ResponseDto response = restTemplate.getForObject(ENDPOINT_USER, ResponseDto.class, cpfNumber);
            return response != null && ABLE_TO_VOTE.equals(response.getStatus());
        } catch (HttpClientErrorException.NotFound ex) {
            return false;
        } catch (Exception ex) {
            throw new ExternalServiceException("Erro ao validar usuário na API externa", ex);
        }
    }
}
