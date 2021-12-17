package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.services.AulaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aulas")
public class AulaController {

    @Autowired
    private AulaDAO aulaDAO;

    /**
     /**
     * EndPoint que retorna una lista de todas las aulas registradas
     *
     * @return response entity con la lista de todos las aulas registradas
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<Aula>> findAll() {
        List<Aula> aulas = aulaDAO.buscarTodos();
        if (aulas.isEmpty()) {
            throw new NotFoundException("No se encontraron alumnos");
        }
        return new ResponseEntity<>(aulas, HttpStatus.OK);
    }

    /**
     /**
     * EndPoint que retorna un aula según el id
     *
     * @param id Número de identificación del aula
     * @return response entity con el aula encontrada
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{id}")
    public ResponseEntity<Aula> findById(@PathVariable Integer id) {
        Aula aula = aulaDAO.buscarPorId(id);
        return new ResponseEntity<>(aula, HttpStatus.OK);
    }
}
