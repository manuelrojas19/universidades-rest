package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("repositorioProfesores")
public interface ProfesorRepository extends PersonaRepository {
    @Query("select p from Profesor p")
    Iterable<Persona> findAll();

    @Query("select p from Profesor p where p.id = ?1")
    Optional<Persona> findById(Integer id);

    @Query("SELECT p FROM Profesor p join fetch p.carreras c where c.nombre = ?1")
    Iterable<Persona> findProfesoresByCarrera(String carrera);
}