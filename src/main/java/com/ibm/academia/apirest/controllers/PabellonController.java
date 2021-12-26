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

    /**
     * EndPoint que retorna una lista de todos los pabellones registrados
     *
     * @return response entity con la lista de todos los pabellones registrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<Pabellon>> findAll() {
        List<Pabellon> pabellones = pabellonDAO.buscarTodos();
        return new ResponseEntity<>(pabellones, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna un pabellón según su id
     *
     * @param id id del pabellón
     * @return response entity con el pabellón encontrado
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pabellon> findById(@PathVariable Integer id) {
        Pabellon pabellon = pabellonDAO.buscarPorId(id);
        return new ResponseEntity<>(pabellon, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna una lista de todos los pabellones registrados según su localidad
     *
     * @param localidad String con la localidad asociada a los pabellones
     * @return response entity con la lista los pabellones encontrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByDireccion_Localidad")
    public ResponseEntity<List<Pabellon>> findByDireccion_Localidad(@RequestParam String localidad) {
        List<Pabellon> pabellones = pabellonDAO
                .findByDireccion_Localidad(localidad);
        return new ResponseEntity<>(pabellones, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna una lista de todos los pabellones registrados según su nombre
     *
     * @param nombre String con el nombre del pabellón.
     * @return response entity con la lista los pabellones encontrados
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByNombre")
    public ResponseEntity<List<Pabellon>> findByNombre(@RequestParam String nombre) {
        List<Pabellon> pabellones = pabellonDAO
                .findByNombre(nombre);
        return new ResponseEntity<>(pabellones, HttpStatus.OK);
    }

    /**
     * EndPoint para registrar un pabellón.
     *
     * @param pabellon request object con los datos del pabellón a registrar
     * @return response entity con los datos del pabellón registrado y el status de la solicitud.
     * @author Manuel Rojas 12-16-2021
     */
    @PostMapping
    public ResponseEntity<Pabellon> save(@Valid @RequestBody Pabellon pabellon) {
        Pabellon pabellonSaved = pabellonDAO.guardar(pabellon);
        return new ResponseEntity<>(pabellonSaved, HttpStatus.CREATED);
    }

    /**
     * EndPoint para eliminar un pabellón
     *
     * @param id     Número de identificación del pabellón
     * @return response entity con el status de la solicitud.
     * @author Manuel Rojas 12-16-2021
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        pabellonDAO.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
