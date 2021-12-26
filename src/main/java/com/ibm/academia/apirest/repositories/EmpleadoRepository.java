package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends PersonaRepository {
    @Query("SELECT e FROM Empleado e")
    Iterable<Persona> findAll();

    @Query("SELECT e FROM Empleado e WHERE e.id = ?1")
    Optional<Persona> findById(Integer id);

    @Query("SELECT e FROM  Empleado e WHERE e.tipoEmpleado = ?1")
    Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}