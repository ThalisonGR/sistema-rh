package com.empresa.rh.service;

import com.empresa.rh.controller.dtos.request.DepartamentoRequest;
import com.empresa.rh.controller.dtos.response.DepartamentoResponse;
import com.empresa.rh.controller.mapper.DepartamentoMapper;
import com.empresa.rh.model.Departamento;
import com.empresa.rh.repository.DepartamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Departamento departamento = repository.findById(id)
            .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Departamento", id));
        return mapper.toDepartamentoResponse(departamento);
    }

    public void excluir(Long id) {
        verificaSeExiste(id);
        repository.deleteById(id);
    }

    private void verificaSeExiste(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Departamento", id));
    }

    public DepartamentoResponse atualizarFuncionario(Long id, DepartamentoRequest departamentoRequest) {
        Departamento departamento = repository.findById(id)
            .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Departamento", id));
        departamento.setNome(departamentoRequest.nome());
        departamento.setDescricao(departamentoRequest.descricao());
        repository.save(departamento);
        return mapper.toDepartamentoResponse(departamento);
    }

    public Page<DepartamentoResponse> listarDepartamentos(int page, int size, String sortBy, String nome) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Departamento> departamentos;
        
        if (nome != null && !nome.trim().isEmpty()) {
            departamentos = repository.findByNomeContainingIgnoreCase(nome, pageable);
        } else {
            departamentos = repository.findAll(pageable);
        }
        
        return departamentos.map(mapper::toDepartamentoResponse);
    }

    public java.util.List<DepartamentoResponse> listarTodosDepartamentos() {
        return repository.findAll().stream()
            .map(mapper::toDepartamentoResponse)
            .collect(java.util.stream.Collectors.toList());
    }

}
