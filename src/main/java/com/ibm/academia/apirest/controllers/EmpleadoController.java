package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {
    @Autowired
    @Qualifier("empleadoDao")
    private PersonaDAO empleadoDao;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> personas = empleadoDao.buscarTodos();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna un empleado según el dni
     *
     * @param dni Número de identificación del empleado
     * @return response entity con el empleado encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{dni}")
    public ResponseEntity<Persona> findByDni(@PathVariable String dni) {
        Persona alumno = empleadoDao.buscarPorDni(dni);
        return new ResponseEntity<>(alumno, HttpStatus.OK);
    }

    @GetMapping("/findByTipoEmpleado")
    public ResponseEntity<List<Persona>> findByTipoEmpleado(@RequestParam TipoEmpleado tipoEmpleado) {
        List<Persona> empleados = (List<Persona>) ((EmpleadoDAO) empleadoDao)
                .findEmpleadoByTipoEmpleado(tipoEmpleado);
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    /**
     * EndPoint para registrar un empleado.
     *
     * @param alumno objeto con los datos del empleado a registrar.
     * @return response entity con el empleado registrado.
     * @author Manuel Rojas 12-16-2021
     */
    @PostMapping
    public ResponseEntity<Persona> saveEmpleado(@Valid @RequestBody Alumno alumno) {
        Persona response = empleadoDao.guardar(alumno);
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
//    @PutMapping("{/id}")
//    public ResponseEntity<Persona> upateAlumno(@PathVariable Integer id, @Valid @RequestBody Alumno alumno) {
//        Persona response = ((AlumnoDAO) empleadoDao).actualizarAlumno(id, alumno);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    /**
     * EndPoint para eliminar un alumno.
     *
     * @param id id del empleado a eliminar
     * @return response entity con el status de la solicitud
     * @author Manuel Rojas 12-16-2021
     */
    @DeleteMapping("{/id}")
    public ResponseEntity<?> deleteEmpleado(@PathVariable Integer id) {
        empleadoDao.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
