package jva.dev.foodordersystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank @Email(message = "Use un formato valido") String email,
                               @NotBlank(message = "Campo obligatorio") String password) {
}
