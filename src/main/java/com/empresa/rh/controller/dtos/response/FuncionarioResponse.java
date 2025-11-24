package com.empresa.rh.controller.dtos.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FuncionarioResponse(
        Long id,
        String nome,
        String email,
        LocalDate dataContratacao,
        Double salario,
        String departamento,   // nome do departamento
        String cargo,          // nome do cargo
        String chefe           // nome do chefe
) {}
