package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.apirest.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class GenericoDAOImpl <E, R extends CrudRepository<E, Integer>> implements GenericoDAO<E>
{
	protected final R repository;
	
	public GenericoDAOImpl(R repository)
	{
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public E buscarPorId(Integer id) {
		return repository.findById(id).orElseThrow(()
				-> new NotFoundException("El recurso no fue encontrado"));
	}

	@Override
	@Transactional
	public E guardar(E entidad) 
	{
		return repository.save(entidad);
	}


	@Override
	@Transactional(readOnly = true)
	public Iterable<E> buscarTodos() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Integer id) 
	{
		repository.deleteById(id);
	}
}