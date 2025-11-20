package com.empresa.rh.controller.mapper;

import com.empresa.rh.controller.dtos.request.FuncionarioRequest;
import com.empresa.rh.controller.dtos.response.FuncionarioResponse;
import com.empresa.rh.model.Funcionario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    Funcionario toFuncionario(FuncionarioRequest funcionarioRequest);

    @Mapping(target = "departamentoId", expression = "java(funcionario.getDepartamento() != null ? funcionario.getDepartamento().getId() : null)")
    @Mapping(target = "cargoId", expression = "java(funcionario.getCargo() != null ? funcionario.getCargo().getId() : null)")
    @Mapping(target = "chefeId", expression = "java(funcionario.getChefe() != null ? funcionario.getChefe().getId() : null)")
    FuncionarioResponse toFuncionarioResponse(Funcionario funcionario);
}
