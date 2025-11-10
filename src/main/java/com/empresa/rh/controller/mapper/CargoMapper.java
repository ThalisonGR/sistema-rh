package com.empresa.rh.controller.mapper;

import com.empresa.rh.controller.dtos.request.CargoRequest;
import com.empresa.rh.controller.dtos.response.CargoResponse;
import com.empresa.rh.model.Cargo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargoMapper {

    Cargo toCargo(CargoRequest cargoRequest);

    CargoResponse toCargoResponse(Cargo cargo);

}
