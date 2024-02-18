package com.forofica.uce.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

public class ForoTO extends RepresentationModel<ForoTO> implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String titulo;

	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime fecha;

	private String texto;
	
	private EstudianteTO estudiante;
	
	private List<ComentarioTO> comentarios;

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

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EstudianteTO getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(EstudianteTO estudiante) {
		this.estudiante = estudiante;
	}

	public List<ComentarioTO> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioTO> comentarios) {
		this.comentarios = comentarios;
	}
	
}