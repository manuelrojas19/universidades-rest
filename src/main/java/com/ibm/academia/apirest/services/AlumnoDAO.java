package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Persona;

import java.util.List;

public interface AlumnoDAO extends PersonaDAO
{
	public List<Persona> buscarAlumnoPorNombreCarrera(String nombre);
	public Persona actualizarAlumno(Integer id, Persona persona);
}