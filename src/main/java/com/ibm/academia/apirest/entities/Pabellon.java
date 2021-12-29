package com.ibm.academia.apirest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
//@Table(name = "pabellones", schema = "universidad")
@Table(name = "pabellones")
public class Pabellon implements Serializable
{
	@Id
	@SequenceGenerator(name = "pabellon_id_seq",
			initialValue = 10,
			allocationSize = 100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pabellon_id_seq")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "metros_cuadrados")
	private Double metrosCuadrados;
	
	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;
	
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
	
	@OneToMany(mappedBy = "pabellon", fetch = FetchType.LAZY,  orphanRemoval=true, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Aula> aulas;

	
	public Pabellon(Integer id, Double metrosCuadrados, String nombre, Direccion direccion) 
	{
		this.id = id;
		this.metrosCuadrados = metrosCuadrados;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(id, nombre);
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pabellon other = (Pabellon) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
	}
	
	@PrePersist
	private void antesPersistir()
	{
		this.fechaAlta = new Date();
	}
	
	@PreUpdate
	private void antesActualizar()
	{
		this.fechaModificacion = new Date();
	}

	private static final long serialVersionUID = 6453675000837381309L;

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" +
				"id = " + id + ", " +
				"metrosCuadrados = " + metrosCuadrados + ", " +
				"nombre = " + nombre + ", " +
				"fechaAlta = " + fechaAlta + ", " +
				"fechaModificacion = " + fechaModificacion + ", " +
				"direccion = " + direccion + ")";
	}
}