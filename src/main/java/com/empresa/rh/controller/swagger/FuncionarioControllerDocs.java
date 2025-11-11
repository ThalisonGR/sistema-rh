package com.empresa.rh.controller.swagger;

import com.empresa.rh.controller.dtos.request.FuncionarioRequest;
import com.empresa.rh.controller.dtos.response.FuncionarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

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
    ResponseEntity<FuncionarioResponse> criarFuncionario(FuncionarioRequest funcionarioRequest);

    @Operation(
            summary = "Excluí um funcionário",
            description = "Endpoint para exclusão de funcionário por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionário excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
            }
    )
    ResponseEntity<Void> excluir(@PathVariable Long id);

    @Operation(
            summary = "Atualiza os dados de um funcionário",
            description = "Endpoint para atualizar os dados de um funcionário existente",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = FuncionarioRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Atualização",
                                    value = "{ \"nome\": \"João Souza\", \"email\": \"joaosouza@email.com\", \"salario\": 4200.0 }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado")
            }
    )
    ResponseEntity<FuncionarioResponse> atualizar(Long id, FuncionarioRequest funcionarioRequest);
}
