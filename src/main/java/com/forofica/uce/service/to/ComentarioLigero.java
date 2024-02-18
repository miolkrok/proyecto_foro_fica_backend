package com.forofica.uce.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ComentarioLigero implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private LocalDateTime fecha;
	private String contenido;
	private Integer estudianteId;
	private Integer foroId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Integer getEstudianteId() {
		return estudianteId;
	}
	public void setEstudianteId(Integer estudianteId) {
		this.estudianteId = estudianteId;
	}
	public Integer getForoId() {
		return foroId;
	}
	public void setForoId(Integer foroId) {
		this.foroId = foroId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
