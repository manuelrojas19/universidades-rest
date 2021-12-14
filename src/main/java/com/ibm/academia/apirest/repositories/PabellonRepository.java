package com.ibm.academia.apirest.repositories;

import com.ibm.academia.apirest.entities.Pabellon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {
    Iterable<Pabellon> findPabellonsByDireccion_Localidad(String localidad);
    Iterable<Pabellon> findPabellonsByNombre(String nombre);
}
