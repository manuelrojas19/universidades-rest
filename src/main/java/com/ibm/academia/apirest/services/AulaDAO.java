package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;

import java.util.List;

public interface AulaDAO extends GenericDAO<Aula> {
    List<Aula> findByPizarron(Pizarron pizarron);
    List<Aula> findByNombrePabellon(String pabellon);
    Aula findByNumeroAula(Integer numAula);
    Aula actualizar(Integer id, Aula aula);

}
