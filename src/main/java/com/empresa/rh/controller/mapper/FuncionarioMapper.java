package com.empresa.rh.controller.mapper;

import com.empresa.rh.controller.dtos.request.FuncionarioRequest;
import com.empresa.rh.controller.dtos.response.FuncionarioResponse;
import com.empresa.rh.model.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    Funcionario toFuncionario(FuncionarioRequest funcionarioRequest);

    @Mapping(source = "departamento.nome", target = "departamento")
    @Mapping(source = "cargo.nome", target = "cargo")
    @Mapping(source = "chefe.nome", target = "chefe")
    FuncionarioResponse toFuncionarioResponse(Funcionario funcionario);
}
