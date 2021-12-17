package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.dto.CarreraDto;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.mapper.CarreraMapper;
import com.ibm.academia.apirest.services.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/carreras")
public class CarreraController {

    @Autowired
    private CarreraDAO carreraDAO;

    @GetMapping
    public ResponseEntity<List<CarreraDto>> findAll() {
        List<CarreraDto> carreras = carreraDAO.buscarTodos()
                .stream().map(CarreraMapper::CarreraToCarreraDto).collect(Collectors.toList());
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDto> findAll(@PathVariable Integer id) {
        Carrera carrera = carreraDAO.buscarPorId(id);
        CarreraDto response = CarreraMapper.CarreraToCarreraDto(carrera);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CarreraDto>> findCarrerasByProfesorNombreYApellido(@RequestParam String nombre,
                                                                                  @RequestParam String apellido) {
        List<CarreraDto> carreras = ((List<Carrera>) carreraDAO
                .findCarrerasByProfesorNombreYApellido(nombre, apellido))
                .stream().map(CarreraMapper::CarreraToCarreraDto).collect(Collectors.toList());
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarreraDto> save(@Valid @RequestBody Carrera carrera) {
        Carrera carreraSaved = carreraDAO.guardar(carrera);
        CarreraDto response = CarreraMapper.CarreraToCarreraDto(carreraSaved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreraDto> update(@PathVariable Integer id, @RequestBody Carrera carrera) {
        Carrera carreraUpdated = carreraDAO.updateCarreraById(id, carrera);
        CarreraDto response = CarreraMapper.CarreraToCarreraDto(carreraUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        carreraDAO.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
