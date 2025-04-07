package jva.dev.foodordersystem.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(@NotBlank String name,@NotBlank String email,
                                  @NotBlank String password, String phone, String address) {
}
