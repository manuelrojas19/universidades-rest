package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("alumnoDao")
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {
    @Autowired
    private CarreraDAO carreraDAO;

    @Autowired
    public AlumnoDAOImpl(@Qualifier("repositorioAlumnos") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> buscarPorNombreCarrera(String nombre) {
        List<Persona> personas = (List<Persona>) ((AlumnoRepository) repository).buscarAlumnoPorNombreCarrera(nombre);
        if (personas.isEmpty()) {
            throw new NotFoundException("No se encontraron alumnos");
        }
        return personas;
    }

    @Override
    @Transactional
    public Persona guardar(Integer idCarrera, Persona persona) {
        Alumno alumno = (Alumno) persona;
        alumno.setCarrera(carreraDAO.buscarPorId(idCarrera));
        return repository.save(alumno);
    }

    @Override
    @Transactional
    public Persona actualizar(Integer id, Persona persona) {
        Alumno alumnoToUpate = (Alumno) this.buscarPorId(id);
        Alumno alumno = (Alumno) persona;

        alumnoToUpate.setNombre(alumno.getNombre());
        alumnoToUpate.setApellido(alumno.getApellido());
        alumnoToUpate.setCarrera(alumno.getCarrera());
        alumnoToUpate.setDireccion(alumno.getDireccion());
        alumnoToUpate.setDni(alumno.getDni());

        return repository.save(alumnoToUpate);
    }


}