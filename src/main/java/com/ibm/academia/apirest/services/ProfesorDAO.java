package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;

import java.util.List;

public interface ProfesorDAO extends PersonaDAO {
    List<Persona> buscarProfesorPorCarrera(String carrera);
}
