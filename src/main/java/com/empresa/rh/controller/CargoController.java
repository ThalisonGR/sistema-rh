package com.empresa.rh.controller;

import com.empresa.rh.controller.dtos.request.CargoRequest;
import com.empresa.rh.controller.dtos.response.CargoResponse;
import com.empresa.rh.controller.swagger.CargoControllerDocs;
import com.empresa.rh.service.CargoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/rh/cargo")
public class CargoController implements CargoControllerDocs {

    private CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @PostMapping
    public ResponseEntity<CargoResponse> criarFuncionario(@RequestBody CargoRequest cargoRequest) {

        CargoResponse cargoResponse = cargoService.criar(cargoRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cargoResponse.id())
                .toUri();

        return ResponseEntity.created(location).body(cargoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cargoService.excluir(id);
        return  ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CargoResponse> atualizar(@PathVariable Long id, @RequestBody CargoRequest cargoRequest) {
        CargoResponse cargoResponse = cargoService.atualizarCargo(id, cargoRequest);
        return ResponseEntity.ok().body(cargoResponse);
    }

    @GetMapping("/listarCargos")
    public ResponseEntity<Page<CargoResponse>> listarCargos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Page<CargoResponse> cargoResponsePage = cargoService.listarCargos(page, size, sortBy);
        return ResponseEntity.ok(cargoResponsePage);
    }



}
