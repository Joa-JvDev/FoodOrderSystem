package jva.dev.foodordersystem.exception;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class ErrorDTO {
    private String description;
    private List<String> reasons = new ArrayList<>();
}
