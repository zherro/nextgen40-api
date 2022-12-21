package com.bettersoft.nextgen4api.config.exception;

import com.bettersoft.nextgen4api.model.error.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> tratarBadCredentialsException(BadCredentialsException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorDetails error = ErrorDetails.builder()
                .status(status.value())
                .code("AUTH_ERROR")
                .title("Acesso não autorizado")
                .detail("Verifique suas informações de acesso e tente novamente!")
                .build();

        return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleGenericException(BusinessException ex, WebRequest request) {
        var status = Objects.requireNonNull(HttpStatus.resolve(ex.getErrorDetail().getStatus()));
        return this.handleExceptionInternal(ex, ex.getErrorDetail(), new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ex.printStackTrace();

        ErrorDetails error = ErrorDetails.builder()
                .status(status.value())
                .code("U_ERROR")
                .title("Erro inesperado!")
                .detail("Ocorreu um erro interno inesperado.")
                .build();

        return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ex.printStackTrace();

        ErrorDetails error = ErrorDetails.builder()
                .status(status.value())
                .code("ACCESS_DENIED")
                .title("Erro inesperado!")
                .detail("Acesso negado!")
                .build();

        return this.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

            if(body == null) {
                ex.printStackTrace();
                body = ErrorDetails.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .code("U_ERROR")
                        .title(status.getReasonPhrase())
                        // .detail(ex.getMessage())
                        .detail("Ocorreu um erro interno inesperado.")
                        .build();
            }

            return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}