package com.empresa.rh.controller.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioRequest(@NotBlank String nome, @NotBlank String email, @NotBlank Double salario) {
}
