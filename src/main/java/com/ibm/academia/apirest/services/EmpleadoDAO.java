package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Empleado;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;

import java.util.List;

public interface EmpleadoDAO extends PersonaDAO {
    List<Persona> findByTipoEmpleado(TipoEmpleado tipoEmpleado);
    Empleado actualizar(Integer id, Empleado empleado);
}
