package com.empresa.rh.controller.swagger;

import com.empresa.rh.controller.dtos.request.CargoRequest;
import com.empresa.rh.controller.dtos.response.CargoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface CargoControllerDocs {

    @Operation(
            summary = "Cria um novo cargo",
            description = "Endpoint para cadastrar um novo cargo no sistema",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CargoRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Cargo",
                                    value = "{ \"nome\": \"Desenvolvedor Backend\", \"descricao\": \"Responsável por implementar APIs e lógica de negócio.\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cargo criado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro de validação nos dados")
            }
    )
    ResponseEntity<CargoResponse> criarFuncionario(CargoRequest cargoRequest);

    @Operation(
            summary = "Exclui um cargo",
            description = "Endpoint para exclusão de um cargo por ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cargo excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cargo não encontrado")
            }
    )
    ResponseEntity<Void> excluir(@PathVariable Long id);

    @Operation(
            summary = "Atualiza os dados de um cargo",
            description = "Endpoint para atualizar os dados de um cargo existente",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CargoRequest.class),
                            examples = @ExampleObject(
                                    name = "Exemplo de Atualização de Cargo",
                                    value = "{ \"nome\": \"Engenheiro de Software\", \"descricao\": \"Lidera o desenvolvimento e revisão de código.\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cargo atualizado com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Cargo não encontrado")
            }
    )
    ResponseEntity<CargoResponse> atualizar(Long id, CargoRequest cargoRequest);

    @Operation(
            summary = "Lista cargos com paginação",
            description = "Endpoint para listar cargos de forma paginada e ordenada",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
            }
    )
    ResponseEntity<Page<CargoResponse>> listarCargos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    );
}
