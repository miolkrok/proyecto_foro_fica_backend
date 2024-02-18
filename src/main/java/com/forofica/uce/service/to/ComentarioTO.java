package com.forofica.uce.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class ComentarioTO extends RepresentationModel<ComentarioTO> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String titulo;
	private LocalDateTime fecha;
	private String contenido;
	private EstudianteTO estudiante;
	private ForoTO foro;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fechaPublicacion) {
		this.fecha = fechaPublicacion;
	}

	public ForoTO getForo() {
		return foro;
	}
	public void setForo(ForoTO foro) {
		this.foro = foro;
	}
	public EstudianteTO getEstudianteTO() {
		return estudiante;
	}
	public void setEstudianteTO(EstudianteTO estudiante) {
		this.estudiante = estudiante;
	}

}