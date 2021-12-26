package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.entities.Persona;

import java.util.Optional;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends PersonaRepository {
    @Query("select a from Alumno a")
    Iterable<Persona> findAll();

    @Query("select a from Alumno a where a.id = ?1")
    Optional<Persona> findById(Integer id);
    //@Query("select a from Alumno a where a.carrera.nombre = ?1")
    @Query("select a from Alumno a join fetch a.carrera c where c.nombre = ?1")
    Iterable<Persona> buscarAlumnoPorNombreCarrera(String nombre);

}