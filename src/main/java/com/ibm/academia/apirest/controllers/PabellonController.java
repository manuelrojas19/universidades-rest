package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/pabellones")
public class PabellonController {

    @Autowired
    private PabellonDAO pabellonDAO;

    @GetMapping("/findPabellonsByDireccion_Localidad")
    public ResponseEntity<List<Pabellon>> findPabellonsByDireccion_Localidad(String localidad) {
        List<Pabellon> pabellons = (List<Pabellon>) pabellonDAO
                .findPabellonsByDireccion_Localidad(localidad);
        return new ResponseEntity<>(pabellons, HttpStatus.OK);
    }

    @GetMapping("/findPabellonsByNombre")
    public ResponseEntity<List<Pabellon>> findPabellonsByNombre(String nombre) {
        List<Pabellon> pabellons = (List<Pabellon>) pabellonDAO
                .findPabellonsByNombre(nombre);
        return new ResponseEntity<>(pabellons, HttpStatus.OK);
    }
}
