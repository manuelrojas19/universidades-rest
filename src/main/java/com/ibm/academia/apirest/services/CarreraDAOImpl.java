package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.repositories.CarreraRepository;

@Service
public class CarreraDAOImpl extends GenericoDAOImpl<Carrera, CarreraRepository> implements CarreraDAO 
{
	@Autowired
	public CarreraDAOImpl(CarreraRepository repository) 
	{
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre) 
	{
		return repository.findCarrerasByNombreContains(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre) 
	{
		
		return repository.findCarrerasByNombreContainsIgnoreCase(nombre);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios) 
	{
		return repository.findCarrerasByCantidadAniosAfter(cantidadAnios);
	}

	@Override
	public Iterable<Carrera> findCarrerasByProfesorNombreYApellido(String nombre, String apellido) {
		return repository.buscarCarreraPorProfesorNombreYApellido(nombre, apellido);
	}

	@Override
	public Carrera updateCarreraById(Integer id, Carrera carrera) {
		Carrera carreraToUpdate = this.buscarPorId(id);
		carreraToUpdate.setId(carrera.getId());
		carreraToUpdate.setNombre(carrera.getNombre());
		carreraToUpdate.setCantidadAnios(carrera.getCantidadAnios());
		carreraToUpdate.setCantidadMaterias(carrera.getCantidadMaterias());
		return repository.save(carreraToUpdate);
	}
}