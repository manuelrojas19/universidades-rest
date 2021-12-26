package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;

import java.util.List;

public interface ProfesorDAO extends PersonaDAO {
    List<Persona> buscarProfesorPorCarrera(String carrera);
    Profesor actualizar(Integer id, Profesor profesor);
}
