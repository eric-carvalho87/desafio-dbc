package br.com.desafio.adapters.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApplicationExceptionAdvice {

    @ExceptionHandler({RuntimeException.class, IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<StandardError> handleException(RuntimeException exception, HttpServletRequest request) {

        StandardError standardError = new StandardError(
                request.getMethod(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getRequestURL().toString(),
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(standardError);
    }
}
