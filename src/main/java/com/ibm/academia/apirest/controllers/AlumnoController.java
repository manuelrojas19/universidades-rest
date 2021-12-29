package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {

    @Autowired
    @Qualifier("alumnoDao")
    private PersonaDAO alumnoDao;

    /**
     * EndPoint que retorna una lista de todos los alumnos registrados
     *
     * @return response entity con la lista de todos los alumnos registrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> alumnos = alumnoDao.buscarTodos();
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna un alumno según su id
     *
     * @param id id del alumno
     * @return response entity con el alumno encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable Integer id) {
        Persona alumno = alumnoDao.buscarPorId(id);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna un alumno según su dni
     *
     * @param dni Número de identificación del alumno
     * @return response entity con el alumno encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByDni")
    public ResponseEntity<Persona> findByDni(@RequestParam String dni) {
        Persona alumno = alumnoDao.buscarPorDni(dni);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    /**
     * Endpoint que retorna una lista de alumnos asociados a una carrera según su nombre
     *
     * @param nombreCarrera String con el nombre de la carrera
     * @return Lista de carreras
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByNombreCarrera")
    public ResponseEntity<List<Persona>> findByNombreCarrera(@RequestParam String nombreCarrera) {
        log.info("Nombre Carrera --> {}", nombreCarrera);
        List<Persona> alumnos = ((AlumnoDAO) alumnoDao).buscarPorNombreCarrera(nombreCarrera);
        return new ResponseEntity<>(alumnos, HttpStatus.OK);
    }

    /**
     * EndPoint para registrar un alumno.
     *
     * @param alumno    objeto con los datos del alumno a registrar.
     * @param idCarrera request param con la carrera a la que se asociara el alumno, si esta no se encuentra en la
     *                  petición el alumno será registrado sin asociarse a una carrera.
     * @return response entity con los datos del alumno registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @PostMapping
    public ResponseEntity<Persona> save(@Valid @RequestBody Alumno alumno,
                                        @RequestParam(required = false) Integer idCarrera) {
        log.info("Received data --> {}, carrera --> {}", alumno, idCarrera);
        Persona personaSaved;
        if (Objects.nonNull(idCarrera))
            personaSaved = ((AlumnoDAO) alumnoDao).guardar(idCarrera, alumno);
        else
            personaSaved = alumnoDao.guardar(alumno);
        return new ResponseEntity<>(personaSaved, HttpStatus.CREATED);
    }

    /**
     * EndPoint para actualizar los datos de un alumno.
     *
     * @param alumno objeto con los datos del alumno a actualizar.
     * @param id     Número de identificación del alumno
     * @return response entity con el alumno actualizado.
     * @author Manuel Rojas 12-16-2021
     */
    @PutMapping("/{id}")
    public ResponseEntity<Persona> update(@PathVariable Integer id, @Valid @RequestBody Alumno alumno) {
        log.info("Received data --> {}", alumno);
        Persona response = ((AlumnoDAO) alumnoDao).actualizar(id, alumno);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * EndPoint para eliminar un alumno.
     *
     * @param id id del alumno a eliminar
     * @return response entity con el status del alumno
     * @author Manuel Rojas 12-16-2021
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        alumnoDao.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
