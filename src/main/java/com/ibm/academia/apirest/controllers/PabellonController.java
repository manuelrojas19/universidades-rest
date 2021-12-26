package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/pabellones")
public class PabellonController {
    @Autowired
    private PabellonDAO pabellonDAO;

    @GetMapping
    public ResponseEntity<List<Pabellon>> findAll() {
        List<Pabellon> pabellones = pabellonDAO.buscarTodos();
        return new ResponseEntity<>(pabellones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pabellon> findById(@PathVariable Integer id) {
        Pabellon pabellon = pabellonDAO.buscarPorId(id);
        return new ResponseEntity<>(pabellon, HttpStatus.OK);
    }

    @GetMapping("/findByDireccion_Localidad")
    public ResponseEntity<List<Pabellon>> findByDireccion_Localidad(@RequestParam String localidad) {
        List<Pabellon> pabellones = pabellonDAO
                .findByDireccion_Localidad(localidad);
        return new ResponseEntity<>(pabellones, HttpStatus.OK);
    }

    @GetMapping("/findByNombre")
    public ResponseEntity<List<Pabellon>> findByNombre(@RequestParam String nombre) {
        List<Pabellon> pabellones = pabellonDAO
                .findByNombre(nombre);
        return new ResponseEntity<>(pabellones, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pabellon> save(@Valid @RequestBody Pabellon pabellon) {
        Pabellon pabellonSaved = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<>(pabellonSaved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        pabellonDAO.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
