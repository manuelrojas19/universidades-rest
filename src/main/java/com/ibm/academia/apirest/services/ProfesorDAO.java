package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;

public interface ProfesorDAO extends PersonaDAO {
    Iterable<Persona> buscarProfesorPorCarrera(String carrera);
}
