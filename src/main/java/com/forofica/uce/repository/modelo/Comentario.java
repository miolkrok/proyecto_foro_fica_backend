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
@Table(name ="comentario")
public class Comentario {
	
	@Id
	@Column(name ="come_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comentario")
	@SequenceGenerator(name = "seq_comentario", allocationSize = 1, sequenceName = "seq_comentario")
	private Integer id;
	
	@Column(name ="come_titulo")
	private String titulo;
	
	@Column(name = "come_fecha")
	private LocalDateTime fechaPublicacion;
	 
	@Column(name = "come_contenido")
	private String contenido;
	
	@JoinColumn(name = "come_id_estu")
	@ManyToOne(fetch = FetchType.LAZY)
	private Estudiante estudiante;
	
	@JoinColumn(name = "come_id_foro")
	@ManyToOne(fetch = FetchType.LAZY)
	private Foro foro;

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

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Foro getForo() {
		return foro;
	}

	public void setForo(Foro foro) {
		this.foro = foro;
	}

	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

}