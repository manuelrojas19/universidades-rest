package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;

public interface AulaDAO extends GenericoDAO<Aula> {
    Iterable<Aula> findAulaByPizarron(Pizarron pizarron);
    Iterable<Aula> findAulaByNombrePabellon(String pabellon);
    Aula findByNumeroAula(Integer numAula);

}
