package com.ibm.academia.apirest.controllers;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.services.AulaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/aulas")
public class AulaController {

    @Autowired
    private AulaDAO aulaDAO;

    /**
     * EndPoint que retorna una lista de todas las aulas registradas
     *
     * @return response entity con la lista de todos las aulas registradas
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping
    public ResponseEntity<List<Aula>> findAll() {
        List<Aula> aulas = aulaDAO.buscarTodos();
        return new ResponseEntity<>(aulas, HttpStatus.OK);
    }

    /**
     * /**
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

    /**
     * EndPoint que retorna una lista de aulas basándonos en el pizarrón asociado
     *
     * @param pizarron RequestParam con el pizarron asociado a las aulas
     * @return Response Entity con la lista de Aulas
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByPizzaron")
    public ResponseEntity<List<Aula>> findByPizzaron(@RequestParam Pizarron pizarron) {
        List<Aula> aulas = aulaDAO.findByPizarron(pizarron);
        return new ResponseEntity<>(aulas, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna una lista de aulas según el nombre del pabellón
     *
     * @param nombrePabellon String nombre del pabellón asociado
     * @return Response Entity con la lista de Aulas
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByNombrePabellon")
    public ResponseEntity<List<Aula>> findByNombrePabellon(@RequestParam String nombrePabellon) {
        List<Aula> aulas = aulaDAO.findByNombrePabellon(nombrePabellon);
        return new ResponseEntity<>(aulas, HttpStatus.OK);
    }

    /**
     * EndPoint que retorna un Aula según su numero de aula
     *
     * @param numeroAula Integer con el número de aula
     * @return Response Entity con la lista de Aulas
     * @author Manuel Rojas 12-16-2021
     */
    @GetMapping("/findByNumeroAula")
    public ResponseEntity<Aula> findByNumeroAula(@RequestParam Integer numeroAula) {
        Aula aula = aulaDAO.findByNumeroAula(numeroAula);
        return new ResponseEntity<>(aula, HttpStatus.OK);
    }

    /**
     * EndPoint para registrar un Aula.
     *
     * @param aula RequestObject con el Aula a registrar
     * @return Response Entity con el aula registrada y status de la solicitud
     * @author Manuel Rojas 12-16-2021
     */
    @PostMapping
    public ResponseEntity<Aula> save(@Valid @RequestBody Aula aula) {
        Aula aulaSaved = aulaDAO.guardar(aula);
        return new ResponseEntity<>(aulaSaved, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Aula> update(@PathVariable Integer id, @Valid @RequestBody Aula aula) {
//        Aula aulaUpdated = ((AulaDAO) aulaDAO)
//    }

    /**
     * EndPoint para eliminar un Aula.
     *
     * @param id Integer con el id del aula a eliminar
     * @return Response Entity con el status de la solicitud
     * @author Manuel Rojas 12-16-2021
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        aulaDAO.eliminarPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
