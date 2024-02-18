package com.forofica.uce.repository.modelo;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "foro")
public class Foro {
	@Id
	@Column(name ="foro_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_foro")
	@SequenceGenerator(name = "seq_foro", allocationSize = 1, sequenceName = "seq_foro")
	private Integer id;
	
	@Column(name ="foro_titulo")
	private String titulo;
	
	@Column(name = "foro_fecha")
	private LocalDateTime fecha;
	 
	@Column(name = "foro_texto")
	private String texto;
	
	@JoinColumn(name = "foro_id_estu")
	@ManyToOne(fetch = FetchType.LAZY)
	private Estudiante estudiante;
	
	@OneToMany(mappedBy ="foro", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comentario> comentarios;

	
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

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
}