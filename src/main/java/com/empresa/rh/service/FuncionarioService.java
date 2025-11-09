package com.empresa.rh.service;

import com.empresa.rh.controller.dtos.request.FuncionarioRequest;
import com.empresa.rh.controller.dtos.response.FuncionarioResponse;
import com.empresa.rh.controller.mapper.FuncionarioMapper;
import com.empresa.rh.model.Funcionario;
import com.empresa.rh.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private  FuncionarioRepository repository;

    @Autowired
    private  FuncionarioMapper mapper;

    public FuncionarioResponse criar(FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = repository.save(mapper.toFuncionario(funcionarioRequest));
        return mapper.toFuncionarioResponse(funcionario);
    }

    public FuncionarioResponse buscarFuncionarioPorId(Long id) {
        Funcionario funcionario =  repository.findById(id).orElseThrow(() -> new RuntimeException("Nao existe o id"));
        return mapper.toFuncionarioResponse(funcionario);
    }

    public void excluir(Long id) {
        verificaSeExiste(id);
        repository.deleteById(id);
    }

    private void verificaSeExiste(Long id) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Nao existe o id"));
    }

    public FuncionarioResponse atualizarFuncionario(Long id, FuncionarioRequest funcionarioRequest) {
        Funcionario funcionario = repository.findById(id).orElseThrow(() -> new RuntimeException("Nao existe o id"));
        funcionario.setNome(funcionarioRequest.nome());
        funcionario.setEmail(funcionarioRequest.email());
        funcionario.setEmail(funcionarioRequest.email());

        return mapper.toFuncionarioResponse(funcionario);
    }

    public List<Funcionario> listarComFiltros(Long departamentoId, Long cargoId, Long chefeId) {
        return repository.findByFiltros(departamentoId, cargoId, chefeId);
    }

}
