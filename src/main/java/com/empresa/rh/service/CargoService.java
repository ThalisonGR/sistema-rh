package com.empresa.rh.service;

import com.empresa.rh.controller.dtos.request.CargoRequest;
import com.empresa.rh.controller.dtos.response.CargoResponse;
import com.empresa.rh.controller.mapper.CargoMapper;
import com.empresa.rh.model.Cargo;
import com.empresa.rh.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;

    @Autowired
    private CargoMapper mapper;

    public CargoResponse criar (CargoRequest cargoRequest) {
        Cargo cargoSaved = mapper.toCargo(cargoRequest);
        return mapper.toCargoResponse(cargoSaved);
    }

    public CargoResponse atualizarCargo (Long id, CargoRequest cargoRequest) {
        Cargo cargo = repository.findById(id).orElseThrow(() -> new RuntimeException("Cargo não existe"));
        cargo.setNome(cargoRequest.nome());
        cargo.setDescricao(cargoRequest.descricao());

        repository.save(cargo);

        return mapper.toCargoResponse(cargo);
    }

    public void excluir(Long id) {
        Cargo cargo = repository.findById(id).orElseThrow(() -> new RuntimeException("Cargo não existe"));
        repository.deleteById(cargo.getId());
    }


}
