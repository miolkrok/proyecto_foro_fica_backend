package com.forofica.uce.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class QuejaTO extends RepresentationModel<QuejaTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String descripcion;

	private LocalDateTime fechaPublicacion;

	private EstudianteTO estudiante;

	// SET y GET
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

	public EstudianteTO getEstudianteTO() {
		return estudiante;
	}

	public void setEstudianteTO(EstudianteTO estudiante) {
		this.estudiante = estudiante;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
