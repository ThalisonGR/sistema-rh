package com.empresa.rh.controller;

import com.empresa.rh.controller.dtos.request.FuncionarioRequest;
import com.empresa.rh.controller.dtos.response.FuncionarioResponse;
import com.empresa.rh.controller.mapper.FuncionarioMapper;
import com.empresa.rh.controller.swagger.FuncionarioControllerDocs;
import com.empresa.rh.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rh/funcionario")
public class FuncionarioController implements FuncionarioControllerDocs {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService, FuncionarioMapper funcionarioMapper) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> criarFuncionario(@RequestBody @Valid FuncionarioRequest funcionarioRequest) {

        FuncionarioResponse funcionarioSaved = funcionarioService.criar(funcionarioRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionarioSaved.id())
                .toUri();

        return ResponseEntity.created(location).body(funcionarioSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> atualizar(@PathVariable Long id, @RequestBody  FuncionarioRequest funcionarioRequest) {
        FuncionarioResponse funcionarioSaved = funcionarioService.atualizarFuncionario(id, funcionarioRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionarioSaved.id())
                .toUri();

         return ResponseEntity.ok().body(funcionarioSaved);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<FuncionarioResponse>> listarFuncionarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String nome) {
            Page<FuncionarioResponse> funcionarios = funcionarioService.listarFuncionarios(page, size, sortBy, nome);
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> buscarPorId(@PathVariable Long id) {
        FuncionarioResponse funcionarioResponse = funcionarioService.buscarFuncionarioPorId(id);
        return ResponseEntity.ok(funcionarioResponse);
    }

}
