package com.empresa.rh.controller.swagger;

import com.empresa.rh.controller.dtos.request.FuncionarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface FuncionarioControllerDocs {

    @Operation(
            summary = "Cria um novo funcionário",
            description = "Endpoint para cadastrar um novo funcionário no sistema",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = FuncionarioRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Funcionário",
                                    value = "{ \"nome\": \"João Silva\", \"email\": \"joao@email.com\", \"salario\": 3500.0 }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação nos dados")
            }
    )
    ResponseEntity<Void> criarFuncionario(FuncionarioRequest funcionarioDTO);
}
