package jva.dev.foodordersystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum APIError {

    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "Los atributos no estan correctamente definidos"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST, "El formato es invalido"),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "La orden no existe"),
    CART_IS_EMPTY(HttpStatus.BAD_REQUEST, "No tienes productos en tu carrito"),;

    private final HttpStatus status;
    private final String description;
}
