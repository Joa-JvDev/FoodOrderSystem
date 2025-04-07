package jva.dev.foodordersystem.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank String email,@NotBlank String password) {
}
