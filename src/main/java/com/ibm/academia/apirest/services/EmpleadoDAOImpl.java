package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Empleado;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("empleadoDao")
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {

    private static final String NOT_FOUND_ERROR_MSG = "No se han encontrado empleados";

    @Autowired
    public EmpleadoDAOImpl(@Qualifier("empleadoRepository") PersonaRepository repository) {
        super(repository);
    }

    @Override
    public List<Persona> findByTipoEmpleado(TipoEmpleado tipoEmpleado) {
        List<Persona> empleados = (List<Persona>) ((EmpleadoRepository) repository)
                .findEmpleadoByTipoEmpleado(tipoEmpleado);
        if (empleados.isEmpty())
            throw new NotFoundException(NOT_FOUND_ERROR_MSG);
        return empleados;
    }

    @Override
    public Empleado actualizar(Integer id, Empleado empleado) {
        Empleado empleadoToUpdate = (Empleado) this.buscarPorId(id);
        empleadoToUpdate.setTipoEmpleado(empleado.getTipoEmpleado());
        empleadoToUpdate.setSueldo(empleado.getSueldo());
        empleadoToUpdate.setNombre(empleado.getNombre());
        empleadoToUpdate.setApellido(empleado.getApellido());
        return repository.save(empleadoToUpdate);
    }
}
