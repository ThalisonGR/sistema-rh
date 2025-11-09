package com.empresa.rh.controller;

import com.empresa.rh.controller.dtos.request.FuncionarioRequest;
import com.empresa.rh.controller.dtos.response.FuncionarioResponse;
import com.empresa.rh.controller.mapper.FuncionarioMapper;
import com.empresa.rh.model.Funcionario;
import com.empresa.rh.service.FuncionarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService, FuncionarioMapper funcionarioMapper) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> criarFuncionario(@RequestBody FuncionarioRequest funcionarioRequest) {

        FuncionarioResponse funcionarioSaved = funcionarioService.criar(funcionarioRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionarioSaved.id())
                .toUri();

        return ResponseEntity.created(location).body(funcionarioSaved);
    }



}
