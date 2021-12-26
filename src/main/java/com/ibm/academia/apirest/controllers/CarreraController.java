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

    /**
     * Endpoint para recuperar una lista de todas las carreras registradas en la base de datos
     *
     * @return lista de las carreras registradas
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<CarreraDto>> findAll() {
        List<CarreraDto> carreras = carreraDAO.buscarTodos()
                .stream().map(CarreraMapper::CarreraToCarreraDto).collect(Collectors.toList());
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    /**
     * Endpoint para recuperar una carrera por ID.
     *
     * @param id de la carrera a recuperar.
     * @return ResponseEntity con la carrera encontrada.
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarreraDto> findById(@PathVariable Integer id) {
        Carrera carrera = carreraDAO.buscarPorId(id);
        CarreraDto response = CarreraMapper.CarreraToCarreraDto(carrera);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Endpoint para recuperar una lista de carreras con base en la cantidad de anios
     *
     * @param anios Integer cantidad de anios
     * @return Response Entity con la lista de carreras
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByCantidadAniosAfter")
    public ResponseEntity<List<CarreraDto>> findByCantidadAniosAfter(@RequestParam Integer anios) {
        List<CarreraDto> carreras = carreraDAO
                .findByCantidadAniosAfter(anios)
                .stream().map(CarreraMapper::CarreraToCarreraDto).collect(Collectors.toList());
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna una lista de carreras que su nombre contenga cierta cadena de caracteres
     *
     * @param nombre cadena de caracteres que debe contener el nombre
     * @return ResponseEntity con la lista de Carreras
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByNombreContains")
    public ResponseEntity<List<CarreraDto>> findByNombreContains(@RequestParam String nombre) {
        List<CarreraDto> carreras = carreraDAO
                .findByNombreContains(nombre)
                .stream().map(CarreraMapper::CarreraToCarreraDto).collect(Collectors.toList());
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna una lista de carreras basándonos en el nombre ignorando mayúsculas
     *
     * @param nombre String del nombre de la carrera
     * @return ResponseEntity con la lista de Carreras
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByNombreContainsIgnoreCase")
    public ResponseEntity<List<CarreraDto>> findByNombreContainsIgnoreCase(@RequestParam String nombre) {
        List<CarreraDto> carreras = carreraDAO
                .findByNombreContainsIgnoreCase(nombre)
                .stream().map(CarreraMapper::CarreraToCarreraDto).collect(Collectors.toList());
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    /**
     * Endpoint para recuperar una list de carreras asociadas a un profesor tomando su nombre y apellido
     *
     * @param nombre   String con el nombre del profesor
     * @param apellido String con el apellido del profesor
     * @return ResponseEntity con las carreras encontradas
     */
    @GetMapping("/findByProfesorNombreYApellido")
    public ResponseEntity<List<CarreraDto>> findCarrerasByProfesorNombreYApellido(@RequestParam String nombre,
                                                                                  @RequestParam String apellido) {
        List<CarreraDto> carreras = carreraDAO
                .findByProfesorNombreYApellido(nombre, apellido)
                .stream().map(CarreraMapper::CarreraToCarreraDto).collect(Collectors.toList());
        return new ResponseEntity<>(carreras, HttpStatus.OK);
    }

    /**
     * Endpoint para registrar una carrera
     *
     * @param carrera Objeto con los datos de la carrera a registrar.
     * @return ResponseEntity con la carrera registrada y el status de la solicitud.
     */
    @PostMapping
    public ResponseEntity<CarreraDto> save(@Valid @RequestBody Carrera carrera) {
        Carrera carreraSaved = carreraDAO.guardar(carrera);
        CarreraDto response = CarreraMapper.CarreraToCarreraDto(carreraSaved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Endpoint para editar una carrera
     *
     * @param id      Integer con el id de la carrera a editar
     * @param carrera Objeto con los datos de la carrera a editar.
     * @return ResponseEntity con la carrera editada y el status de la solicitud.
     * @author Manuel Rojas 12-16-2021
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarreraDto> update(@PathVariable Integer id, @RequestBody Carrera carrera) {
        Carrera carreraUpdated = carreraDAO.update(id, carrera);
        CarreraDto response = CarreraMapper.CarreraToCarreraDto(carreraUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * EndPoint para eliminar una carrera.
     *
     * @param id id de la carrera a eliminar
     * @return response entity con el status de la solicitud.
     * @author Manuel Rojas 12-16-2021
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        carreraDAO.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
