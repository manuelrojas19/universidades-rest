package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {

    @Autowired
    @Qualifier("alumnoDao")
    private PersonaDAO alumnoDao;

    /**
     * /**
     * EndPoint que retorna una lista de todos los alumnos registrados
     *
     * @return response entity con la lista de todos los alumnos registrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> alumnos = ((List<Persona>) alumnoDao.buscarTodos());
        if (alumnos.isEmpty()) {
            throw new NotFoundException("No se encontraron alumnos");
        }
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

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
                .orElseThrow(() -> new NotFoundException("No se encontró al alumno"));
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }


    /**
     * EndPoint para registrar un alumno.
     *
     * @param alumno objeto con los datos del alumno a registrar.
     * @return response entity con el alumno registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @PostMapping
    public ResponseEntity<Persona> saveAlumno(@Valid @RequestBody Alumno alumno) {
        Persona response = alumnoDao.guardar(alumno);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * EndPoint para actulizar los datos de un alumno.
     *
     * @param alumno objeto con los datos del alumno a registrar.
     * @param id    Número de identificación del alumno
     * @return response entity con el alumno registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @PutMapping("{/id}")
    public ResponseEntity<Persona> upateAlumno(@PathVariable Integer id, @Valid @RequestBody Alumno alumno) {
        Persona response = ((AlumnoDAO) alumnoDao).actualizarAlumno(id, alumno);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * EndPoint para eliminar un alumno.
     *
     * @param id id del alumno a eliminar
     * @return response entity con el alumno registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @DeleteMapping("{/id}")
    public ResponseEntity<?> deleteAlumno(@PathVariable Integer id) {
        alumnoDao.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
