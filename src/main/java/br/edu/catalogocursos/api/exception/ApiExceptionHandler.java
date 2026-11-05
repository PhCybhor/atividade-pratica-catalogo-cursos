package br.edu.catalogocursos.api.exception;

import br.edu.catalogocursos.application.RecursoDuplicadoException;
import br.edu.catalogocursos.application.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiError> tratarNaoEncontrado(
            RecursoNaoEncontradoException exception,
            HttpServletRequest request) {
        return resposta(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    @ExceptionHandler(RecursoDuplicadoException.class)
    public ResponseEntity<ApiError> tratarDuplicado(
            RecursoDuplicadoException exception,
            HttpServletRequest request) {
        return resposta(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> tratarValidacao(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        return resposta(HttpStatus.BAD_REQUEST, "Um ou mais campos são inválidos", request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> tratarJsonInvalido(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {
        return resposta(HttpStatus.BAD_REQUEST, "JSON ausente ou inválido", request);
    }

    private ResponseEntity<ApiError> resposta(
            HttpStatus status,
            String message,
            HttpServletRequest request) {
        ApiError erro = new ApiError(status.value(), message, request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }
}
