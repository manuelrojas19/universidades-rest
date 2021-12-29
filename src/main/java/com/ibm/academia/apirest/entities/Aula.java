package com.ibm.academia.apirest.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.academia.apirest.enums.Pizarron;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
//@Table(name = "aulas", schema = "universidad")
@Table(name = "aulas")
public class Aula implements Serializable 
{
	@Id
	@SequenceGenerator(name = "aula_id_seq",
			initialValue = 10,
			allocationSize = 100)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="aula_id_seq")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "numero_aula", nullable = false)
	private Integer numeroAula;
	
	@Column(name = "medidas")
	private String medidas;
	
	@Column(name = "cantidad_pupitres")
	private Integer cantidadPupitres;
	
	@Column(name = "tipo_pizarron")
	@Enumerated(EnumType.STRING)
	private Pizarron pizarron;
	
	@Column(name = "fecha_alta")
	private Date fechaAlta;
	
	@Column(name = "fecha_modificacion")
	private Date fechaModificacion;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Pabellon pabellon;
	
	public Aula(Integer id, Integer numeroAula, String medidas, Integer cantidadPupitres, Pizarron pizarron) 
	{
		this.id = id;
		this.numeroAula = numeroAula;
		this.medidas = medidas;
		this.cantidadPupitres = cantidadPupitres;
		this.pizarron = pizarron;
	}
	
	@Override
	public int hashCode() 
	{
		return Objects.hash(id, numeroAula);
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
		Aula other = (Aula) obj;
		return Objects.equals(id, other.id) && Objects.equals(numeroAula, other.numeroAula);
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


	private static final long serialVersionUID = -2327342842041992057L;
}