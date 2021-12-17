package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class ProfesorController {
    @Autowired
    @Qualifier("profesorDAOImpl")
    private PersonaDAO profesorDao;

    /**
     /**
     * EndPoint que retorna una lista de todos los profesores registrados
     *
     * @return response entity con la lista de todos los profesores registrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> alumnos = profesorDao.buscarTodos();
        if (alumnos.isEmpty()) {
            throw new NotFoundException("No se encontraron profesores");
        }
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    /**
     /**
     * EndPoint que retorna un profesor según el dni
     *
     * @param dni Número de identificación del profesor
     * @return response entity con el profesor encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{dni}")
    public ResponseEntity<Persona> findByDni(@PathVariable String dni) {
        Persona alumno = profesorDao.buscarPorDni(dni)
                .orElseThrow(() -> new NotFoundException("No se encontro al profesor"));
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }
}
