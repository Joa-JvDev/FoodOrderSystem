package jva.dev.foodordersystem.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExeptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationExeption.class)
    public ResponseEntity<ErrorDTO> handleApplicationExeption(ApplicationExeption e, WebRequest request) {
        return ResponseEntity.status(e.getStatus())
                .body(new ErrorDTO(e.getDescription(), e.getReasons()));
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                               HttpStatusCode status, WebRequest request) {
        List<String> reasons = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            reasons.add(String.format("%s: %s", error.getField(), error.getDefaultMessage()));
        }

        return ResponseEntity.status(APIError.VALIDATION_ERROR.getStatus())
                .body(new ErrorDTO(APIError.VALIDATION_ERROR.getDescription(), reasons));
    }

}
