package jva.dev.foodordersystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRegisterRequest(@NotBlank String name, @NotBlank @Email( message = "Use un formato valido") String email,
                                  @NotBlank(message = "Campo requerido") String password,
                                  @NotBlank(message = "Ingrese un numero de contacto") String phone,
                                  String address) {
}
