package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
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


    /**
     * EndPoint para recuperar todos los empleados registrados en la bd
     *
     * @return response entity con la lista de todos los empleados registrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> empleados = empleadoDao.buscarTodos();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    /**
     * EndPoint para recuperar a un empleado según su id
     *
     * @param id Integer con el id del empleado a recuperar.
     * @return response entity con el empleado encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable Integer id) {
        Persona empleado = empleadoDao.buscarPorId(id);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna un empleado según el dni
     *
     * @param dni Número de identificación del empleado
     * @return response entity con el empleado encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByDni")
    public ResponseEntity<Persona> findByDni(@RequestParam String dni) {
        Persona empleado = empleadoDao.buscarPorDni(dni);
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }

    @GetMapping("/findByTipoEmpleado")
    public ResponseEntity<List<Persona>> findByTipoEmpleado(@RequestParam TipoEmpleado tipoEmpleado) {
        List<Persona> empleados = ((EmpleadoDAO) empleadoDao).findByTipoEmpleado(tipoEmpleado);
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
