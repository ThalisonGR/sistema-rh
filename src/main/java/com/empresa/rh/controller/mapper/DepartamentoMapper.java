package com.empresa.rh.controller.mapper;

import com.empresa.rh.controller.dtos.request.DepartamentoRequest;
import com.empresa.rh.controller.dtos.response.DepartamentoResponse;
import com.empresa.rh.model.Departamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    Departamento toDepartamento(DepartamentoRequest departamentoRequest);

    DepartamentoResponse toDepartamentoResponse(Departamento departamento);
}
