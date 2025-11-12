package com.empresa.rh.controller.swagger;

import com.empresa.rh.controller.dtos.request.DepartamentoRequest;
import com.empresa.rh.controller.dtos.response.DepartamentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Departamento", description = "Operações relacionadas a departamentos")
public interface DepartamentoControllerDocs {

    @Operation(
            summary = "Cria um novo departamento",
            description = "Cadastra um novo departamento no sistema",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Departamento criado com sucesso",
                            content = @Content(schema = @Schema(implementation = DepartamentoResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content)
            }
    )
    ResponseEntity<DepartamentoResponse> criar(
            @RequestBody(
                    description = "Dados do novo departamento (nome e descrição)",
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    implementation = DepartamentoRequest.class,
                                    example = """
                    {
                      "nome": "Recursos Humanos",
                      "descricao": "Responsável pela gestão de pessoas e processos internos"
                    }
                    """
                            )
                    )
            )
            DepartamentoRequest departamentoRequest
    );

    @Operation(
            summary = "Exclui um departamento",
            description = "Remove um departamento existente pelo ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Departamento excluído com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Departamento não encontrado", content = @Content)
            }
    )
    ResponseEntity<Void> excluir(@PathVariable Long id);

    @Operation(
            summary = "Atualiza um departamento",
            description = "Atualiza os dados de um departamento existente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Departamento atualizado com sucesso",
                            content = @Content(schema = @Schema(implementation = DepartamentoResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Departamento não encontrado", content = @Content)
            }
    )
    ResponseEntity<DepartamentoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody(
                    description = "Dados atualizados do departamento (nome e descrição)",
                    required = true,
                    content = @Content(
                            schema = @Schema(
                                    implementation = DepartamentoRequest.class,
                                    example = """
                    {
                      "nome": "Financeiro",
                      "descricao": "Departamento responsável pelo controle financeiro da empresa"
                    }
                    """
                            )
                    )
            )
            DepartamentoRequest departamentoRequest
    );
}
