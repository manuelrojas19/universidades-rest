package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Persona;
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
     * EndPoint que retorna un profesor según el dni
     *
     * @param dni Número de identificación del profesor
     * @return response entity con el profesor encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{dni}")
    public ResponseEntity<Persona> findByDni(@PathVariable String dni) {
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
     * EndPoint para registrar un alumno.
     *
     * @param profesor objeto con los datos del alumno a registrar.
     * @return response entity con el alumno registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @PostMapping("/profesores")
    public ResponseEntity<Persona> saveProfesor(@Valid @RequestBody Persona profesor) {
        Persona response = profesorDao.guardar(profesor);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    /**
     * EndPoint para eliminar un alumno.
     *
     * @param id id del alumno a eliminar
     * @return response entity con el alumno registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @DeleteMapping("{/profesores/{id}")
    public ResponseEntity<?> deleteProfesor(@PathVariable Integer id) {
        profesorDao.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
