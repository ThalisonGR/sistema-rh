package com.empresa.rh.service;

import com.empresa.rh.controller.dtos.request.FuncionarioRequest;
import com.empresa.rh.controller.dtos.response.FuncionarioResponse;
import com.empresa.rh.controller.mapper.FuncionarioMapper;
import com.empresa.rh.model.Cargo;
import com.empresa.rh.model.Departamento;
import com.empresa.rh.model.Funcionario;
import com.empresa.rh.repository.CargoRepository;
import com.empresa.rh.repository.DepartamentoRepository;
import com.empresa.rh.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    private  FuncionarioRepository repository;

    @Autowired
    private  FuncionarioMapper mapper;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public FuncionarioResponse criar(FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = mapper.toFuncionario(funcionarioRequest);
        
        // Associar departamento se fornecido
        if (funcionarioRequest.departamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(funcionarioRequest.departamentoId())
                .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Departamento", funcionarioRequest.departamentoId()));
            funcionario.setDepartamento(departamento);
        }
        
        // Associar cargo se fornecido
        if (funcionarioRequest.cargoId() != null) {
            Cargo cargo = cargoRepository.findById(funcionarioRequest.cargoId())
                .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Cargo", funcionarioRequest.cargoId()));
            funcionario.setCargo(cargo);
        }
        
        // Associar chefe se fornecido
        if (funcionarioRequest.chefeId() != null) {
            Funcionario chefe = repository.findById(funcionarioRequest.chefeId())
                .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Chefe", funcionarioRequest.chefeId()));
            funcionario.setChefe(chefe);
        }
        
        funcionario = repository.save(funcionario);
        return mapper.toFuncionarioResponse(funcionario);
    }

    public FuncionarioResponse buscarFuncionarioPorId(Long id) {
        Funcionario funcionario = repository.findById(id)
            .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Funcionário", id));
        return mapper.toFuncionarioResponse(funcionario);
    }

    public void excluir(Long id) {
        verificaSeExiste(id);
        repository.deleteById(id);
    }

    private void verificaSeExiste(Long id) {
        repository.findById(id)
            .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Funcionário", id));
    }

    public FuncionarioResponse atualizarFuncionario(Long id, FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = repository.findById(id)
            .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Funcionário", id));
        funcionario.setNome(funcionarioRequest.nome());
        funcionario.setEmail(funcionarioRequest.email());
        funcionario.setSalario(funcionarioRequest.salario());

        // Atualizar departamento
        if (funcionarioRequest.departamentoId() != null) {
            Departamento departamento = departamentoRepository.findById(funcionarioRequest.departamentoId())
                .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Departamento", funcionarioRequest.departamentoId()));
            funcionario.setDepartamento(departamento);
        } else {
            funcionario.setDepartamento(null);
        }

        // Atualizar cargo
        if (funcionarioRequest.cargoId() != null) {
            Cargo cargo = cargoRepository.findById(funcionarioRequest.cargoId())
                .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Cargo", funcionarioRequest.cargoId()));
            funcionario.setCargo(cargo);
        } else {
            funcionario.setCargo(null);
        }

        // Atualizar chefe
        if (funcionarioRequest.chefeId() != null) {
            Funcionario chefe = repository.findById(funcionarioRequest.chefeId())
                .orElseThrow(() -> new com.empresa.rh.exception.ResourceNotFoundException("Chefe", funcionarioRequest.chefeId()));
            funcionario.setChefe(chefe);
        } else {
            funcionario.setChefe(null);
        }

        repository.save(funcionario);
        return mapper.toFuncionarioResponse(funcionario);
    }

    public Page<FuncionarioResponse> listarFuncionarios(int page, int size, String sortBy, String nome) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Funcionario> funcionarios;
        
        if (nome != null && !nome.trim().isEmpty()) {
            funcionarios = repository.findByNomeContainingIgnoreCase(nome, pageable);
        } else {
            funcionarios = repository.findAll(pageable);
        }
        
        return funcionarios.map(mapper::toFuncionarioResponse);
    }

}
