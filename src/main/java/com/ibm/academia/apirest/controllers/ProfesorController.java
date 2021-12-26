package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProfesorController {

    @Autowired
    @Qualifier("profesorDAOImpl")
    private PersonaDAO profesorDao;

    /**
     * /**
     * EndPoint que retorna una lista de todos los profesores registrados
     *
     * @return response entity con la lista de todos los profesores registrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/profesores")
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> profesores = profesorDao.buscarTodos();
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna un profesor según el id
     *
     * @param id Integer con el id del profesor
     * @return response entity con el profesor encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/profesores/{id}")
    public ResponseEntity<Persona> findByDni(@PathVariable Integer id) {
        Persona alumno = profesorDao.buscarPorId(id);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }


    /**
     * EndPoint que retorna un profesor según el dni
     *
     * @param dni Número de identificación del profesor
     * @return response entity con el profesor encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/profesores/findByDni")
    public ResponseEntity<Persona> findByDni(@RequestParam String dni) {
        Persona alumno = profesorDao.buscarPorDni(dni);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    /**
     * Endpoint que retorna una lista de profesores según la carrera
     *
     * @param carrera carrera de los profesores a buscar.
     * @return lista de los profesores asociados a la carrera.
     */
    @GetMapping("/carreras/{carrera}/profesores")
    public ResponseEntity<List<Persona>> findByCarrera(@PathVariable String carrera) {
        List<Persona> profesores = ((ProfesorDAO) profesorDao).buscarProfesorPorCarrera(carrera);
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    /**
     * EndPoint para registrar un profesor.
     *
     * @param profesor objeto con los datos del profesor a registrar.
     * @return response entity con el profesor registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @PostMapping("/profesores")
    public ResponseEntity<Persona> save(@Valid @RequestBody Profesor profesor) {
        Persona response = profesorDao.guardar(profesor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * EndPoint para editar los datos de un profesor.
     *
     * @param profesor objeto con los datos del profesor a actualizar.
     * @return response entity con el profesor actualizado.
     * @author Manuel Rojas 12-16-2021
     */
    @PutMapping("/profesores/{id}")
    public ResponseEntity<Persona> update(@PathVariable Integer id, @Valid @RequestBody Profesor profesor) {
        Persona response = ((ProfesorDAO) profesorDao).actualizar(id, profesor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * EndPoint para eliminar un profesor.
     *
     * @param id id del profesor a eliminar
     * @return response entity con el profesor registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @DeleteMapping("{/profesores/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        profesorDao.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
