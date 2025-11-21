package com.empresa.rh.controller;

import com.empresa.rh.controller.dtos.request.DepartamentoRequest;
import com.empresa.rh.controller.dtos.response.DepartamentoResponse;
import com.empresa.rh.controller.swagger.DepartamentoControllerDocs;
import com.empresa.rh.service.DepartamentoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rh/departamento")
public class DepartamentoController implements DepartamentoControllerDocs {

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    private DepartamentoService departamentoService;


    @PostMapping
    public ResponseEntity<DepartamentoResponse> criar(@RequestBody DepartamentoRequest departamentoRequest) {

        DepartamentoResponse departamentoResponse = departamentoService.criar(departamentoRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(departamentoResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(departamentoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        departamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoResponse> atualizar(@PathVariable Long id, @RequestBody DepartamentoRequest departamentoRequest) {
        DepartamentoResponse departamentoResponse = departamentoService.atualizarFuncionario(id, departamentoRequest);
        return ResponseEntity.ok().body(departamentoResponse);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<DepartamentoResponse>> listarDepartamentos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) String nome) {
        org.springframework.data.domain.Page<DepartamentoResponse> departamentos = departamentoService.listarDepartamentos(page, size, sortBy, nome);
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<DepartamentoResponse>> listarTodosDepartamentos() {
        java.util.List<DepartamentoResponse> departamentos = departamentoService.listarTodosDepartamentos();
        return ResponseEntity.ok(departamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoResponse> buscarPorId(@PathVariable Long id) {
        DepartamentoResponse departamentoResponse = departamentoService.buscarDepartamnetoId(id);
        return ResponseEntity.ok(departamentoResponse);
    }

}

