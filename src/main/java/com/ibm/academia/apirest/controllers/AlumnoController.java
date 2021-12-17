package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alumnos")
@Validated
public class AlumnoController {
    @Autowired
    @Qualifier("alumnoDao")
    private PersonaDAO alumnoDao;

    /**
     /**
     * EndPoint que retorna una lista de todos los alumnos registrados
     *
     * @return response entity con la lista de todos los alumnos registrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> alumnos = alumnoDao.buscarTodos();
        if (alumnos.isEmpty()) {
            throw new NotFoundException("No se encontraron alumnos");
        }
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    /**
     /**
     * EndPoint que retorna un alumno según el dni
     *
     * @param dni Número de identificación del alumno
     * @return response entity con el alumno encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{dni}")
    public ResponseEntity<Persona> findByDni(@PathVariable String dni) {
        Persona alumno = alumnoDao.buscarPorDni(dni)
                .orElseThrow(() -> new NotFoundException("No se encontro al alumno"));
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }


}
