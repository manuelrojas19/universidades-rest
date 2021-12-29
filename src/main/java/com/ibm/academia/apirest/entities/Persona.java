package com.ibm.academia.apirest.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
//@Table(name = "personas", schema = "universidad")
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona implements Serializable {
    @Id
    @SequenceGenerator(name = "persona_id_seq",
            initialValue = 10,
            allocationSize = 100)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="persona_id_seq")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 60)
    private String apellido;

    @Column(name = "dni", nullable = false, unique = true, length = 10)
    private String dni;

    @Column(name = "fecha_alta")
    private Date fechaAlta;

    @Column(name = "fecha_modificacion")
    private Date fechaModificacion;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "codigoPostal", column = @Column(name = "codigo_postal")),
            @AttributeOverride(name = "departamento", column = @Column(name = "departamento"))
    })
    private Direccion direccion;

    public Persona(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
    }


    @Override
    public int hashCode() {
        return Objects.hash(dni, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Persona other = (Persona) obj;
        return Objects.equals(dni, other.dni) && Objects.equals(id, other.id);
    }

    @PrePersist
    private void antesPersistir() {
        this.fechaAlta = new Date();
        this.fechaModificacion = new Date();
    }

    @PreUpdate
    private void antesActualizar() {
        this.fechaModificacion = new Date();
    }

    private static final long serialVersionUID = 7435045832876635940L;
}