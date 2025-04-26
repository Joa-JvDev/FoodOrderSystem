package jva.dev.foodordersystem.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter @Setter
public class ApplicationExeption extends RuntimeException {

    private HttpStatus status;
    private String description;
    private List<String> reasons;

    public ApplicationExeption(APIError error) {
        this.status = error.getStatus();
        this.description = error.getDescription();
    }

    public ApplicationExeption(HttpStatus status, String description, List<String> reasons) {
        this.status = status;
        this.description = description;
        this.reasons = reasons;
    }
}
