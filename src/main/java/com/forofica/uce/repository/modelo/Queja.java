package com.forofica.uce.repository.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "queja")
public class Queja {

	@Id
	@Column(name = "quej_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_queja")
	@SequenceGenerator(name = "seq_queja", sequenceName = "seq_queja", allocationSize = 1)
	private Integer id;

	@Column(name = "quej_descripcion")
	private String descripcion;

	@Column(name = "quej_fecha_publicacion")
	private LocalDateTime fechaPublicacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "quej_id_estu")
	private Estudiante estudiante;

	// Getters y Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

}
