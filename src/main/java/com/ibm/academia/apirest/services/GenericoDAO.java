package com.ibm.academia.apirest.services;

import java.util.List;

public interface GenericoDAO<E> 
{
	public E buscarPorId(Integer id);
	public E guardar(E entidad);
	public Iterable<E> buscarTodos();
	public void eliminarPorId(Integer id);
}