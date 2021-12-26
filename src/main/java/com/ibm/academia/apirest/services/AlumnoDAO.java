package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;

import java.util.List;

public interface AlumnoDAO extends PersonaDAO {
    List<Persona> buscarPorNombreCarrera(String nombre);
    Persona guardar(Integer idCarrera, Persona persona);
    Persona actualizar(Integer id, Persona persona);
}