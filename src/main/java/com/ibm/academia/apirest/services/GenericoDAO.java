package com.ibm.academia.apirest.services;

import java.util.List;
import java.util.Optional;

public interface GenericoDAO<E> 
{
	public E buscarPorId(Integer id);
	public E guardar(E entidad);
	public List<E> buscarTodos();
	public void eliminarPorId(Integer id);
}