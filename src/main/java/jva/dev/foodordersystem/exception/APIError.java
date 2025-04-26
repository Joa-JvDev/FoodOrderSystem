package jva.dev.foodordersystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum APIError {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Los atributos no estan correctamente definidos"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "El mensaje se esta enviando en un formato incorrecto"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "El mensaje no existe");

    private final HttpStatus status;
    private final String description;
}
