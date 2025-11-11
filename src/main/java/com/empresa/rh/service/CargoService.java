package com.empresa.rh.service;

import com.empresa.rh.controller.dtos.request.CargoRequest;
import com.empresa.rh.controller.dtos.response.CargoResponse;
import com.empresa.rh.controller.mapper.CargoMapper;
import com.empresa.rh.model.Cargo;
import com.empresa.rh.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;

    @Autowired
    private CargoMapper mapper;

    public CargoResponse criar (CargoRequest cargoRequest) {
        Cargo cargoSaved = mapper.toCargo(cargoRequest);
        repository.save(cargoSaved);
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

    public Page<CargoResponse> listarCargos(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Cargo> cargos = repository.findAll(pageable);
        return cargos.map(mapper::toCargoResponse);
    }


}
