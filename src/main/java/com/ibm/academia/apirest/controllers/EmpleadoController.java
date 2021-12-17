package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.services.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {
    @Autowired
    @Qualifier("empleadoDao")
    private PersonaDAO empleadoDao;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        List<Persona> personas = (List<Persona>) empleadoDao.buscarTodos();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

}
