package com.empresa.rh.service;

import com.empresa.rh.controller.dtos.request.DepartamentoRequest;
import com.empresa.rh.controller.dtos.response.DepartamentoResponse;
import com.empresa.rh.controller.mapper.DepartamentoMapper;
import com.empresa.rh.model.Departamento;
import com.empresa.rh.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoService {

    private DepartamentoRepository repository;

    private DepartamentoMapper mapper;

    public DepartamentoService(DepartamentoRepository repository, DepartamentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DepartamentoResponse criar(DepartamentoRequest departamentoRequest) {
        Departamento departamento = repository.save(mapper.toDepartamento(departamentoRequest));
        return mapper.toDepartamentoResponse(departamento);
    }

    public DepartamentoResponse buscarDepartamnetoId(Long id) {
        Departamento departamento =  repository.findById(id).orElseThrow(() -> new RuntimeException("Nao existe o id"));
        return mapper.toDepartamentoResponse(departamento);
    }

    public void excluir(Long id) {
        verificaSeExiste(id);
        repository.deleteById(id);
    }

    private void verificaSeExiste(Long id) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Nao existe o id"));
    }

    public DepartamentoResponse atualizarFuncionario(Long id, DepartamentoRequest departamentoRequest) {
        Departamento departamento = repository.findById(id).orElseThrow(() -> new RuntimeException("Nao existe o id"));
        departamento.setNome(departamentoRequest.nome());
        departamento.setDescricao(departamentoRequest.descricao());
        repository.save(departamento);
        return mapper.toDepartamentoResponse(departamento);
    }

}
